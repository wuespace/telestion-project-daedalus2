package de.wuespace.telestion.project.daedalus2.mongodb;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.services.message.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Listener that collects all incoming data configured in listeningAddresses and redirects them to be saved to the
 * MongoDatabaseService.
 */
@SuppressWarnings("unused")
public class DataListener extends TelestionVerticle<DataListener.Configuration> implements WithEventBus {

	public record Configuration(
			@JsonProperty List<String> listeningAddresses
	) implements TelestionConfiguration {
		private Configuration() {
			this(new ArrayList<>());
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());

		// pass-through received data to MongoDatabaseSaver
		getConfig().listeningAddresses().forEach(
				address -> register(address, message -> publish(SAVE_ADDRESS, message.body())));
	}

	/**
	 * Mongo Database Service save address
	 */
	private final String SAVE_ADDRESS = Address.incoming(MongoDatabaseSaver.class, "save");
}
