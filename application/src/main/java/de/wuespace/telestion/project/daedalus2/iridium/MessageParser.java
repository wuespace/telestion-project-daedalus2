package de.wuespace.telestion.project.daedalus2.iridium;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.daedalus2.iridium.message.IridiumMessage;
import de.wuespace.telestion.services.connection.rework.ConnectionData;

import java.io.IOException;

@SuppressWarnings("unused")
public class MessageParser extends TelestionVerticle<MessageParser.Configuration> implements WithEventBus {

	/**
	 * Configuration for {@link MessageParser} verticle.
	 *
	 * @param inAddress  the address to listen on new raw tcp messages
	 * @param outAddress the address to publish new valid Iridium messages
	 */
	public record Configuration(@JsonProperty String inAddress,
								@JsonProperty String outAddress) implements TelestionConfiguration {
	}

	public MessageParser() {
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public void onStart() {
		register(getConfig().inAddress(), this::handle, ConnectionData.class);
	}

	private final ObjectMapper objectMapper;

	private void handle(ConnectionData data) {
		try {
			// the byte array contains the json-encoded message -> transform it to string
			// next convert the string to a valid Java Object with Jackson
			// next convert the object to our message types and send it out
			var message = new IridiumMessage(objectMapper.readValue(data.rawData(), new TypeReference<>() {
			}));
			publish(getConfig().outAddress(), message);
		} catch (IOException e) {
			logger.error("Cannot parse raw data", e);
		}
	}
}
