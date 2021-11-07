package de.wuespace.telestion.project.daedalus2.iridium;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.iridium.message.IEHeader;
import de.wuespace.telestion.project.daedalus2.iridium.message.IELocation;
import de.wuespace.telestion.project.daedalus2.iridium.message.IEPayload;
import de.wuespace.telestion.project.daedalus2.iridium.message.IridiumMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MessageMapper extends AbstractVerticle {
	private record Configuration(@JsonProperty String inAddress, @JsonProperty Map<String, String> imeiMapping) {
		private Configuration() {
			this(null, new HashMap<>());
		}
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		this.config = Config.get(this.config, new Configuration(), config(), Configuration.class);
		vertx.eventBus().consumer(config.inAddress(), raw -> JsonMessage.on(IridiumMessage.class, raw, this::handleMessage));
		startPromise.complete();
	}

	public MessageMapper(Configuration forcedConfig) {
		this.config = forcedConfig;
	}

	public MessageMapper() {
		this(null);
	}

	private final Logger logger = LoggerFactory.getLogger(MessageMapper.class);
	private Configuration config;

	private void handleMessage(IridiumMessage msg) {
		IEHeader header = null;
		IEPayload payload = null;
		IELocation location = null;

		for (var elem : msg.elements()) {
			if (elem instanceof IEHeader) {
				header = (IEHeader) elem;
			} else if (elem instanceof IEPayload) {
				payload = (IEPayload) elem;
			} else if (elem instanceof IELocation) {
				location = (IELocation) elem;
			} else {
				logger.warn("Unknown information element type. Ignoring");
			}
		}

		if (header == null) {
			logger.warn("Header not found on current message. " +
					"Cannot assign mapped message to current device");
		}

		if (payload == null) {
			logger.info("Message with no payload received. Continuing as normal");
		}

		if (location == null) {
			logger.info("Iridium location information missing. Continuing as normal");
		}

		var mapped = header != null
				? config.imeiMapping().getOrDefault(header.imei(), "unknown")
				: "unknown";
		vertx.eventBus().publish(mapped + "/iridium", new MappedMessage(header, payload, location).json());
	}
}
