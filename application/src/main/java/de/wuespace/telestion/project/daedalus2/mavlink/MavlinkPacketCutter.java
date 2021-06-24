package de.wuespace.telestion.project.daedalus2.mavlink;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.extension.mavlink.message.Mavlink1Information;
import de.wuespace.telestion.extension.mavlink.message.Mavlink2Information;
import de.wuespace.telestion.extension.mavlink.message.internal.ValidatedMavlinkPacket;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MavlinkPacketCutter extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) {
		config = Config.get(config, config(), Configuration.class);

		vertx.eventBus().consumer(config.inAddress(), raw -> JsonMessage.on(ValidatedMavlinkPacket.class, raw, msg -> {
			var packetInfo = msg.info();

			var compId = packetInfo instanceof Mavlink1Information ? ((Mavlink1Information) packetInfo).compId()
					: packetInfo instanceof Mavlink2Information ? ((Mavlink2Information) packetInfo).compId() : -1;

			var addr = config.compIdParserMapping().get(compId);

			if (addr == null) {
				logger.info("Received packet with unknown component ID");
				return;
			}

			vertx.eventBus().publish(addr, msg);
		}));

		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) {
		stopPromise.complete();
	}

	public record Configuration(
			@JsonProperty
			String inAddress,
			@JsonProperty
			Map<Integer, String> compIdParserMapping
	) implements JsonMessage {
		private Configuration() {
			this(null, null);
		}
	}

	public MavlinkPacketCutter() {
		this(null);
	}

	public MavlinkPacketCutter(Configuration configuration) {
		this.config = configuration;
	}

	private Configuration config;

	private static final Logger logger = LoggerFactory.getLogger(MavlinkPacketCutter.class);
}
