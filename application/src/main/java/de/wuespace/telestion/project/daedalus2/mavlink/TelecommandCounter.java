package de.wuespace.telestion.project.daedalus2.mavlink;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.api.verticle.trait.WithSharedData;
import de.wuespace.telestion.project.daedalus2.mavlink.message.TCSent;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.shareddata.LocalMap;

@SuppressWarnings("unused")
public class TelecommandCounter extends TelestionVerticle<TelecommandCounter.Configuration>
		implements WithEventBus, WithSharedData {
	public record Configuration(
			@JsonProperty String inAddress
	) implements TelestionConfiguration {
	}

	@Override
	public void onStart() {
		register(getConfig().inAddress(), this::handle, TCSent.class);
	}

	private void handle(TCSent tcSent, Message<Object> message) {
		final long receiveTime = System.currentTimeMillis();
		var target = tcSent.target();
		var sendTime = message.headers().get("send-time");
		var map = getLocalMap();
		logger.debug("Received telecommand from target {}", target);

		// get, increment, put
		int value = map.getOrDefault(target, 0);
		value = (value + 1) % 256; // jump back to 0 at 256
		map.put(target, value);

		// pack and time-tag new value for Redis
		var options = new DeliveryOptions()
				.addHeader("receive-time", Json.encode(receiveTime))
				.addHeader("time", sendTime != null ? sendTime : Json.encode(receiveTime));
		publish(target + "/tc-counter", value, options);
	}

	private LocalMap<String, Integer> getLocalMap() {
		return localMap(LOCAL_MAP_NAME);
	}

	private static final String LOCAL_MAP_NAME = "tc-counter-shared";
}
