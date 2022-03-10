package de.wuespace.telestion.project.daedalus2.mavlink;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.api.verticle.trait.WithSharedData;
import de.wuespace.telestion.project.daedalus2.mavlink.message.TCReset;
import de.wuespace.telestion.project.daedalus2.mavlink.message.TCSent;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.shareddata.LocalMap;

import java.util.Objects;

@SuppressWarnings("unused")
public class TelecommandCounter extends TelestionVerticle<TelecommandCounter.Configuration>
		implements WithEventBus, WithSharedData {
	public record Configuration(
			@JsonProperty String inAddress,
			@JsonProperty String resetAddress
	) implements TelestionConfiguration {
	}

	@Override
	public void onStart() {
		register(getConfig().inAddress(), this::handle, TCSent.class);
		register(getConfig().resetAddress(), this::handleReset, TCReset.class);
	}

	private void handle(TCSent tcSent, Message<Object> message) {
		var target = tcSent.target();
		logger.debug("Received telecommand from target {}", target);

		// get, increment, put
		int value = getValue(target);
		value = (value + 1) % 256; // jump back to 0 at 256
		setValue(target, value, message.headers().get("send-time"));
	}

	private void handleReset(TCReset tcReset, Message<Object> message) {
		setValue(tcReset.target(), 0, message.headers().get("send-time"));
	}

	private void setValue(String target, int value, String sendTime) {
		var receiveTime = System.currentTimeMillis();
		defaultLocalMap().put(target, value);

		var options = new DeliveryOptions()
				.addHeader("receive-time", Json.encode(receiveTime))
				.addHeader("time", Objects.isNull(sendTime) ? Json.encode(receiveTime) : sendTime);
		publish(target + "/tc-counter", value, options);
	}

	private int getValue(String target) {
		return defaultLocalMap().getOrDefault(target, 0);
	}

	private LocalMap<String, Integer> defaultLocalMap() {
		return localMap(LOCAL_MAP_NAME);
	}

	private static final String LOCAL_MAP_NAME = "tc-counter-shared";
}
