package de.wuespace.telestion.project.daedalus2.mavlink;

import com.MAVLink.daedalus.msg_con_cmd;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.mavlink.internal.RawTelecommand;
import de.wuespace.telestion.project.daedalus2.mavlink.internal.Telecommand;
import de.wuespace.telestion.services.connection.rework.RawMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@SuppressWarnings("unused")
public class TelecommandSender extends AbstractVerticle {
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		config = Config.get(config, config(), Configuration.class);

		// From here on there will be no more changes to the config

		vertx.eventBus().consumer(config.inAddress(),
				raw -> {
					JsonMessage.on(Telecommand.class, raw, tc ->
						// convert String to ASCII byte code
						buildMessage(tc.target(), tc.msg().getBytes(StandardCharsets.ISO_8859_1))
					);
					JsonMessage.on(RawTelecommand.class, raw, rtc ->
						// pass through raw data from telecommand
						buildMessage(rtc.target(), rtc.rawData())
					);
				});

		super.start(startPromise);
	}

	public record Configuration(
			@JsonProperty
			String inAddress,
			@JsonProperty
			String outAddress,
			@JsonProperty
			Map<String, Integer> compIdAliasMapping,
			@JsonProperty
			int sysId
	) implements JsonMessage {
		@SuppressWarnings("unused")
		public Configuration() {
			this(null, null, null, 0);
		}
	}

	public TelecommandSender() {
		this(null);
	}

	public TelecommandSender(Configuration config) {
		this.config = config;
	}

	private void buildMessage(String target, byte[] payload) {
		msg_con_cmd tc = new msg_con_cmd(payload, config.sysId(), config.compIdAliasMapping().get(target), true);
		var bytes = tc.pack().encodePacket();
		logger.debug("Sending TC {} to compId {}: {}", payload, config.compIdAliasMapping().get(target), bytes);

		vertx.eventBus().publish(
				config.outAddress(),
				new RawMessage(bytes).json()
		);
	}

	private Configuration config;

	private static final Logger logger = LoggerFactory.getLogger(TelecommandSender.class);
}
