package de.wuespace.telestion.project.daedalus2.iridium;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.iridium.message.IridiumMessage;
import de.wuespace.telestion.services.connection.rework.ConnectionData;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@SuppressWarnings("unused")
public class MessageParser extends AbstractVerticle {
	private final Logger logger = LoggerFactory.getLogger(MessageParser.class);
	private final Configuration forcedConfig;
	private final ObjectMapper objectMapper;
	private Configuration config;

	/**
	 * This constructor supplies default options.
	 *
	 * @param inAddress  the address to listen on new raw tcp messages
	 * @param outAddress the address to publish new valid Iridium messages
	 */
	public MessageParser(String inAddress, String outAddress) {
		this(new Configuration(inAddress, outAddress));
	}

	/**
	 * If this constructor is used at all, settings have to be specified in the config file.
	 */
	public MessageParser() {
		this(null);
	}

	private MessageParser(Configuration forcedConfig) {
		this.forcedConfig = forcedConfig;
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public void start(Promise<Void> startPromise) {
		config = Config.get(forcedConfig, new Configuration(), config(), Configuration.class);
		// register to in address
		vertx.eventBus()
				.consumer(config.inAddress(),
						raw -> JsonMessage.on(ConnectionData.class, raw, data -> parseMessage(data.rawData())));
		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) {
		stopPromise.complete();
	}

	public Configuration getConfig() {
		return config;
	}

	private void parseMessage(byte[] rawData) {
		try {
			// the byte array contains the json-encoded message -> transform it to string
			// next convert the string to a valid Java Object with Jackson
			// next convert the object to our message types and send it out
			var message = new IridiumMessage(objectMapper.readValue(rawData, new TypeReference<>() {
			}));
			vertx.eventBus().send(config.outAddress(), message.json());
		} catch (IOException e) {
			logger.error("Cannot parse raw data", e);
		}
	}

	/**
	 * Configuration for {@link MessageParser} verticle.
	 *
	 * @param inAddress  the address to listen on new raw tcp messages
	 * @param outAddress the address to publish new valid Iridium messages
	 */
	private static record Configuration(@JsonProperty String inAddress,
										@JsonProperty String outAddress) {
		Configuration() {
			this(null, null);
		}
	}
}
