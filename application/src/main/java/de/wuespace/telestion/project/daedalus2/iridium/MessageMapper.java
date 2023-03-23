package de.wuespace.telestion.project.daedalus2.iridium;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.api.verticle.trait.WithSharedData;
import de.wuespace.telestion.project.daedalus2.iridium.message.*;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
public class MessageMapper extends TelestionVerticle<MessageMapper.Configuration>
		implements WithEventBus, WithSharedData {

	public static final String UNKNOWN_TARGET_NAME = "unknown";

	public record MappedMessage(
			@JsonProperty IEHeader header,
			@JsonProperty IEPayload payload,
			@JsonProperty IELocation location
	) implements JsonMessage {
		public static MappedMessage from(IridiumMessage message) {
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
				return null;
			}

			if (payload == null) {
				logger.info("Message with no payload received. Continuing as normal");
			}

			if (location == null) {
				logger.info("Iridium location information missing. Continuing as normal");
			}

			return new MappedMessage(header, payload, location);
		}
	}

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
		// extract required information
		final long time = System.currentTimeMillis();
		final var mappedMessage = MappedMessage.from(message);
		final var targetName = getTargetName(mappedMessage);
		final long receiveTime = getReceiveTime(mappedMessage, time);

		if (Objects.isNull(mappedMessage)) {
			incrementInvalid(targetName);
			return;
		}

		var options = new DeliveryOptions()
				.addHeader("receive-time", Json.encode(receiveTime))
				.addHeader("time", Json.encode(time));

		var redisObj = new JsonObject();

		// add iridium infos
		var iridiumObj = new JsonObject();
		if (Objects.nonNull(mappedMessage.header())) {
			iridiumObj.mergeIn(mappedMessage.header().json());
		}
		if (Objects.nonNull(mappedMessage.location())) {
			iridiumObj.mergeIn(mappedMessage.location().json());
		}
		redisObj.put("iridium", iridiumObj);

		// add payload infos
		if (Objects.nonNull(mappedMessage.payload())) {
			var payload = mappedMessage.payload().payload();

			if (payload instanceof ValidDaedalus2Payload validPayload) {
				incrementValid(targetName);
				var payloadObj = new JsonObject()
						.put("gps_fix_avail", validPayload.gps_fix_avail())
						.put("seed_state", validPayload.seed_state());

				var positionObj = new JsonObject()
						.put("latitude", validPayload.latitude())
						.put("longitude", validPayload.longitude())
						.put("altitude", validPayload.altitude());
				payloadObj.put(validPayload.is_valid_fix() ? "valid" : "latest", positionObj);

				redisObj.put("payload", payloadObj);
			} else {
				incrementInvalid(targetName);
			}
		}

		// add statistics
		var statsObj = new JsonObject()
				.put("valid", getValid(targetName))
				.put("invalid", getInvalid(targetName));
		redisObj.put("stats", statsObj);

		publish(targetName + "/iridium", redisObj, options);
		publishUnknownStats(receiveTime, time);
	}

	private void publishUnknownStats(long receiveTime, long time) {
		var targetName = getTargetName(null);
		var redisObj = new JsonObject();

		var options = new DeliveryOptions()
				.addHeader("receive-time", Json.encode(receiveTime))
				.addHeader("time", Json.encode(time));

		// add statistics
		var statsObj = new JsonObject()
				.put("valid", getValid(targetName))
				.put("invalid", getInvalid(targetName));
		redisObj.put("stats", statsObj);

		publish(targetName + "/iridium", redisObj, options);
	}

	/**
	 * Resolves a target name from a given IMEI number.
	 * Returns <code>"unknown"</code> when no IMEI number is found for a target.
	 *
	 * @param message the mapped message
	 * @return the target name
	 */
	private String getTargetName(MappedMessage message) {
		if (Objects.isNull(message)) {
			return UNKNOWN_TARGET_NAME;
		}

		return Optional.of(message)
				.map(MappedMessage::header)
				.map(header -> getConfig().imeiMapping().get(header.imei())).
				orElse(UNKNOWN_TARGET_NAME);
	}

	private long getReceiveTime(MappedMessage message, long time) {
		// use header time from Iridium or else current system time
		return Optional
				.of(message)
				.map(MappedMessage::header)
				.map(header -> header.time() * 1000L)
				.orElse(time);
	}

	private void incrementValid(String targetName) {
		incrementKey(targetName + "-valid");
	}

	private void incrementInvalid(String targetName) {
		incrementKey(targetName + "-invalid");
	}

	private int getValid(String targetName) {
		return getKey(targetName + "-valid");
	}

	private int getInvalid(String targetName) {
		return getKey(targetName + "-invalid");
	}

	private void incrementKey(String key) {
		Map<String, Integer> map = localMap(MessageMapper.class.getName());
		var currentValue = map.getOrDefault(key, 0);
		map.put(key, currentValue + 1);
	}

	private int getKey(String key) {
		Map<String, Integer> map = localMap(MessageMapper.class.getName());
		return map.getOrDefault(key, 0);
	}
}
