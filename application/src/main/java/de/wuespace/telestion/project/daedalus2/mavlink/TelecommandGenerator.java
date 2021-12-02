package de.wuespace.telestion.project.daedalus2.mavlink;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.project.daedalus2.mavlink.internal.Telecommand;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelecommandGenerator extends AbstractVerticle {
	public static final String tcCommand =
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ut hendrerit nisi. Quisque dictum in arcu ac rhoncus. In molestie.";

	public record Configuration(
			@JsonProperty String outAddress,
			@JsonProperty String target,
			@JsonProperty int numberOfTCs,
			@JsonProperty int interval
	) {
		public Configuration() {
			this(null, null, 100, 10000);
		}
	}

	@Override
	public void start() {
		config = Config.get(config, new Configuration(), config(), Configuration.class);
		// setup interval
		vertx.setPeriodic(config.interval, this::sendMessages);
	}

	public void sendMessages(Long intervalId) {
		for (int i = 0; i < config.numberOfTCs; i++) {
			vertx.eventBus().publish(config.outAddress, new Telecommand(config.target, tcCommand).json());
			logger.info("Sent TC no. {}", i);
		}
	}

	private Configuration config;

	private static final Logger logger = LoggerFactory.getLogger(TelecommandGenerator.class);
}
