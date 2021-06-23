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

public class MavlinkPacketCutter extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) {
		config = Config.get(config, config(), Configuration.class);

		vertx.eventBus().consumer(config.inAddress(), raw -> JsonMessage.on(ValidatedMavlinkPacket.class, raw, msg -> {
			var seedAddresses = config.seedAddresses();
			var packetInfo = msg.info();

			var compId = packetInfo instanceof Mavlink1Information ? ((Mavlink1Information) packetInfo).compId()
					: packetInfo instanceof Mavlink2Information ? ((Mavlink2Information) packetInfo).compId() : -1;

			for (int i = 0; i < seedAddresses.length; i++) {
				if (seedAddresses[i] == compId) {
					vertx.eventBus().publish(config.parserAddresses()[i], msg);
					break;
				}
			}

			logger.info("Received packet with unknown component ID");
		}));

		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) {
		stopPromise.complete();
	}

	// The index of the seed address corresponds to the address at that specific index
	public record Configuration(
			@JsonProperty
			String inAddress,
			@JsonProperty
			String[] parserAddresses,
			@JsonProperty
			int[] seedAddresses
	) implements JsonMessage {
		private Configuration() {
			this(null, null, null);
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
