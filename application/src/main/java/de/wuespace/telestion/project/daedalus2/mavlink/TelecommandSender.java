package de.wuespace.telestion.project.daedalus2.mavlink;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.extension.mavlink.annotation.MavInfo;
import de.wuespace.telestion.extension.mavlink.security.X25Checksum;
import de.wuespace.telestion.project.daedalus2.mavlink.internal.Telecommand;
import de.wuespace.telestion.services.connection.rework.ConnectionData;
import de.wuespace.telestion.services.connection.rework.ConnectionDetails;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TelecommandSender extends AbstractVerticle {
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		config = Config.get(config, config(), Configuration.class);

		// From here on there will be no more changes to the config
		config.compIdAliasMapping().values()
				.forEach(s -> packetCount.put(s, new AtomicInteger(0)));

		if (!JsonMessage.on(ConnectionDetails.class,
				JsonObject.mapFrom(config.conDetails()), handle -> details = handle)) {
			startPromise.fail("ConDetails for the TCs could not be interpreted");
		}

		vertx.eventBus().consumer(config.inAddress(),
				raw -> JsonMessage.on(Telecommand.class, raw, this::buildMessage));

		super.start(startPromise);
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		packetCount.clear();

		super.stop(stopPromise);
	}

	public record Configuration(
			@JsonProperty
			String inAddress,
			@JsonProperty
			String outAddress,
			@JsonProperty
			Map<String, Integer> compIdAliasMapping,
			@JsonProperty
			int sysId,
			@JsonProperty
			String conDetails
	) implements JsonMessage {
		@SuppressWarnings("unused")
		public Configuration() {
			this(null, null, null, 0, null);
		}
	}

	public TelecommandSender() {
		this(null);
	}

	public TelecommandSender(Configuration config) {
		this.config = config;

		this.packetCount = new HashMap<>();
	}

	private void buildMessage(Telecommand tc) {
		var magicByte = 0xFD;	// Mavlink v2

		var incompatFlags = 0x0;
		var compatFlags = 0x0;

		var sysId = config.sysId();
		int compId = config.compIdAliasMapping().get(tc.target());
		var seq = packetCount.get(compId).getAndIncrement();

		var msgId = Telecommand.class.getAnnotation(MavInfo.class).id();

		var payload = tc.msg().getBytes(StandardCharsets.ISO_8859_1);
		var len = payload.length;

		var buffer = ByteBuffer.allocate(9 + len);
		buffer.put((byte) len)
				.put((byte) incompatFlags)
				.put((byte) compatFlags)
				.put((byte) seq)
				.put((byte) sysId)
				.put((byte) compId)
				.put((byte) msgId)
				.put((byte) (msgId >> 8))
				.put((byte) (msgId >> 16));
		buffer.put(payload);

		var checksum = X25Checksum.calculate(buffer.array());

		logger.debug("Sending Telecommand");

		var finalBuffer = ByteBuffer.allocate(12 + len);

		finalBuffer.put((byte) magicByte);
		finalBuffer.put(buffer.array());
		finalBuffer.put((byte) checksum).put((byte) (checksum >> 8));

		vertx.eventBus().publish(config.outAddress(), new ConnectionData(finalBuffer.array(), details).json());
	}

	private Configuration config;
	private final HashMap<Integer, AtomicInteger> packetCount;
	private ConnectionDetails details;

	private final static Logger logger = LoggerFactory.getLogger(TelecommandSender.class);
}
