package de.wuespace.telestion.project.daedalus2.iridium;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.daedalus2.iridium.message.IEHeader;
import de.wuespace.telestion.project.daedalus2.iridium.message.IELocation;
import de.wuespace.telestion.project.daedalus2.iridium.message.IEPayload;
import de.wuespace.telestion.project.daedalus2.iridium.message.IridiumMessage;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class MessageMapper extends TelestionVerticle<MessageMapper.Configuration> implements WithEventBus {
	public record Configuration(
			@JsonProperty String inAddress,
			@JsonProperty Map<String, String> imeiMapping
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, new HashMap<>());
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		register(getConfig().inAddress(), this::handle, IridiumMessage.class);
	}

	private void handle(IridiumMessage message) {
		final long receivedTime = System.currentTimeMillis();
		IEHeader header = null;
		IEPayload payload = null;
		IELocation location = null;

		for (var elem : message.elements()) {
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
				? getConfig().imeiMapping().getOrDefault(header.imei(), "unknown")
				: "unknown";
		var options = new DeliveryOptions()
				.addHeader("receive-time", Json.encode(receivedTime))
				// from seconds to milliseconds
				.addHeader("time", Json.encode(header != null ? header.time() * 1000 : receivedTime));
		publish(mapped + "/iridium", new MappedMessage(header, payload, location), options);
	}
}
