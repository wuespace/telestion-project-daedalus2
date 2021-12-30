package de.wuespace.telestion.project.daedalus2.connection;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.services.connection.rework.SenderData;

import java.util.Arrays;

/**
 * Listens for {@link SenderData} messages and logs them byte-wise.
 *
 * @author Ludwig Richter, Pablo Klaschka
 */
@SuppressWarnings("unused")
public class SerialPrinter extends TelestionVerticle<SerialPrinter.Configuration> implements WithEventBus {

	/**
	 * Configuration for the {@link SerialPrinter} verticle.
	 *
	 * @param inAddress the address the verticle listens to
	 */
	public record Configuration(
			@JsonProperty String inAddress
	) implements TelestionConfiguration {
		public Configuration() {
			this(null);
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		register(getConfig().inAddress(), this::log, SenderData.class);
	}

	private void log(SenderData data) {
		String rawData = Arrays.toString(data.rawData());
		logger.debug("Will send: {}", rawData);
	}
}
