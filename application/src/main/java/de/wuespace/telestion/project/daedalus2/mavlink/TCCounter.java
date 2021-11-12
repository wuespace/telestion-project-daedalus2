package de.wuespace.telestion.project.daedalus2.mavlink;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.mavlink.message.TCSent;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.shareddata.LocalMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCCounter extends AbstractVerticle {
	public record Configuration(@JsonProperty String inAddress) implements JsonMessage {
		public Configuration() {
			this(null);
		}
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		config = Config.get(config, new Configuration(), config(), Configuration.class);

		vertx.eventBus().consumer(config.inAddress(),
				raw -> JsonMessage.on(TCSent.class, raw,
						tc -> incrementAndPublish(tc.target(), raw.headers().get("send-time"))));

		startPromise.complete();
	}

	private Configuration config;

	private void incrementAndPublish(String target, String sendTime) {
		logger.debug("Received telecommand from target {}", target);
		final long receiveTime = System.currentTimeMillis();
		LocalMap<String, Integer> map = vertx.sharedData().getLocalMap(LOCAL_MAP_NAME);

		// get, increment, put
		int value = map.getOrDefault(target, 0);
		value = (value + 1) % 256; // jump back to 0 at 256
		map.put(target, value);

		// pack and time-tag new value for Redis
		var options = new DeliveryOptions()
				.addHeader("receive-time", Json.encode(receiveTime))
				.addHeader("time", sendTime != null ? sendTime : Json.encode(receiveTime));
		vertx.eventBus().publish(target + "/tc-counter", value, options);
	}

	private static final Logger logger = LoggerFactory.getLogger(TCCounter.class);
	private static final String LOCAL_MAP_NAME = "tc-counter-shared";
}
