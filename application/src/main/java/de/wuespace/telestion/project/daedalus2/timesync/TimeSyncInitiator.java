package de.wuespace.telestion.project.daedalus2.timesync;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.daedalus2.mavlink.message.Telecommand;
import io.vertx.core.eventbus.Message;

import java.text.MessageFormat;
import java.time.Duration;

/**
 * A verticle handling time sync TCs.
 *
 * @author Pablo Klaschka, Ludwig Richter
 */
@SuppressWarnings("unused")
public class TimeSyncInitiator extends TelestionVerticle<TimeSyncInitiator.Configuration> implements WithEventBus {

	/**
	 * The offset that needs to be subtracted from the UNIX timestamp to get a D2 timestamp
	 *
	 * @see
	 * <a href="https://gitlab2.informatik.uni-wuerzburg.de/wuespace/daedalus2/software/schwarzes-brett/-/wikis/TimeModel">Time model documentation</a>
	 */
	public static final long D2_TIME_OFFSET_MS = Duration.ofSeconds(1420070400).toMillis();

	public record Configuration(
			@JsonProperty String inAddress,
			@JsonProperty String outAddress
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, null);
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		register(getConfig().inAddress(), this::handleRequest, TimeSyncRequest.class);
	}

	private void handleRequest(TimeSyncRequest request, Message<Object> message) {
		var time = System.currentTimeMillis() - D2_TIME_OFFSET_MS;

		try {
			String command = MessageFormat.format("{0} {1}", request.command(), Long.toUnsignedString(time / 1000L));
			publish(getConfig().outAddress(), new Telecommand(request.target(), command));
			message.reply(command);
		} catch (Exception e) {
			message.fail(500, e.getMessage());
		}
	}
}
