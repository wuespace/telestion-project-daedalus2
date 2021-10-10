package de.wuespace.telestion.project.daedalus2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.mavlink.internal.Telecommand;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.time.Duration;

/**
 * A verticle handling time sync TCs
 */
@SuppressWarnings("unused")
public class TimeSync extends AbstractVerticle {
	/**
	 * The offset that needs to be subtracted from the UNIX timestamp to get a D2 timestamp
	 *
	 * @see
	 * <a href="https://gitlab2.informatik.uni-wuerzburg.de/wuespace/daedalus2/software/schwarzes-brett/-/wikis/TimeModel">Time model documentation</a>
	 */
	public static final long D2_TIME_OFFSET_MS = Duration.ofSeconds(1420070400).toMillis();
	private static final Logger logger = LoggerFactory.getLogger(TimeSync.class);
	private Configuration config;

	public TimeSync() {
		this(null);
	}

	public TimeSync(Configuration config) {
		this.config = config;
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		this.config = Config.get(config, config(), Configuration.class);

		var eb = getVertx().eventBus();

		eb.consumer(config.inAddress(), raw -> JsonMessage.on(TimeSyncRequest.class, raw, message -> {
			try {
				var time = System.currentTimeMillis() - D2_TIME_OFFSET_MS;

				String command = MessageFormat.format("{0} {1}", message.command(), Long.toUnsignedString(time));
				eb.publish(config.outAddress(), new Telecommand(
						message.target(),
						command
				).json());

				raw.reply(command);
			} catch (Exception e) {
				raw.fail(500, e.getMessage());
			}
		}));
	}

	public record Configuration(
			@JsonProperty String inAddress,
			@JsonProperty String outAddress
	) implements JsonMessage {
		public Configuration() {
			this(null, null);
		}
	}
}
