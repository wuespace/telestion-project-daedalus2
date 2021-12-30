package de.wuespace.telestion.project.daedalus2.mavlink;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.daedalus2.mavlink.message.Telecommand;

@SuppressWarnings("unused")
public class TelecommandGenerator extends TelestionVerticle<TelecommandGenerator.Configuration> implements WithEventBus {
	@SuppressWarnings("SpellCheckingInspection")
	public static final String tcCommand =
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ut hendrerit nisi. Quisque dictum in arcu ac rhoncus. In molestie.";

	public record Configuration(
			@JsonProperty String outAddress,
			@JsonProperty String target,
			@JsonProperty int numberOfTCs,
			@JsonProperty int interval
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, null, 100, 10000);
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		vertx.setPeriodic(getConfig().interval(), this::sendMessages);
	}

	public void sendMessages(Long intervalId) {
		for (int i = 0; i < getConfig().numberOfTCs(); i++) {
			publish(getConfig().outAddress(), new Telecommand(getConfig().target(), tcCommand));
			logger.info("Sent TC no. {}", i);
		}
	}
}
