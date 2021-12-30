package de.wuespace.telestion.project.daedalus2.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisBaseConfiguration;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.redis.client.Command;
import io.vertx.redis.client.RedisAPI;

import java.util.List;

/**
 * Saves data into both key-value-based storage ("latest/") and time-series data for numbers ("ts/").
 * <p>
 * Uses requestLatestAddress as prefix and "/" as separator for Redis keys. Objects get saved both as a whole and
 * each property
 * recursively, with the key being appended to the object with "/" in between.
 *
 * @author Pablo Klaschka, Ludwig Richter
 */
@SuppressWarnings("unused")
public class RedisSaver extends RedisVerticle<RedisSaver.Configuration> implements WithEventBus {

	public record Configuration(
			@JsonProperty String connectionString,
			@JsonProperty int reconnectAttempts,
			@JsonProperty List<String> inAddresses
	) implements RedisBaseConfiguration {
		public Configuration() {
			this("redis://redis", 10, List.of());
		}
	}

	public static final String LATEST_PREFIX = "latest/";
	public static final String LATEST_RECEIVE_TIME_PREFIX = "latest-receive-time/";
	public static final String LATEST_TIME_PREFIX = "latest-time/";

	public static void main(String[] args) {
		var vertx = Vertx.vertx();

		// TODO: Improve usage with ListDeployer (when released in core)
		var configuration = new Configuration("redis://localhost:6379", 10, List.of("systemT"));
		vertx.deployVerticle(RedisSaver.class, new DeploymentOptions().setConfig(configuration.json()));
	}

	@Override
	protected void onRedisConnectionEstablished(Promise<Void> startPromise) {
		setDefaultConfig(new Configuration());
		getConfig().inAddresses().forEach(address -> register(address, message -> handle(message, address)));
		startPromise.complete();
	}

	private void handle(Message<Object> message, String address) {
		if (!isConnected()) {
			logger.warn("Unable to save message into Redis DB because it's currently disconnected.");
			return;
		}

		logger.debug("Saving message to Redis DB");
		redisApi.set(List.of(LATEST_PREFIX + address, message.body().toString()));
		try {
			var obj = (new ObjectMapper()).readTree(message.body().toString());
			logger.debug(obj.fieldNames().toString());
			this.saveNewDataset(redisApi, address, obj, message.headers());
		} catch (Exception e) {
			logger.error("Cannot save new dataset: {}", message.body(), e.getCause());
		}
	}

	/**
	 * Saves a new dataset (recursively), including all "sub-properties" of Json Objects
	 *
	 * @param api     the Redis API reference
	 * @param prefix  the prefix under which the node should get saved
	 * @param node    the node that gets saved
	 * @param headers the message headers, including <code>"time"</code> and <code>"receive-time"</code> information
	 */
	private void saveNewDataset(RedisAPI api, String prefix, JsonNode node, MultiMap headers) {
		if (node.isObject()) {
			api.set(List.of(LATEST_PREFIX + prefix, node.toString()));
			node.fields().forEachRemaining(
					stringJsonNodeEntry -> this.saveNewDataset(
							api,
							prefix + "/" + stringJsonNodeEntry.getKey(),
							stringJsonNodeEntry.getValue(),
							headers)
			);
		} else if (node.isNumber()) {
			var value = node.toString();
			api.set(List.of(LATEST_PREFIX + prefix, value));
			api.send(
					Command.create("TS.CREATE"),
					"ts/" + prefix,
					"DUPLICATE_POLICY",
					"LAST"
			).onComplete(event ->
					api.send(
							Command.create("TS.ADD"),
							"ts/" + prefix, System.currentTimeMillis() + "",
							value
					)
			);
		} else {
			api.set(List.of(LATEST_PREFIX + prefix, node.toString()));
		}

		api.set(List.of(LATEST_RECEIVE_TIME_PREFIX + prefix, headers.get("receive-time")));
		api.set(List.of(LATEST_TIME_PREFIX + prefix, headers.get("time")));
	}
}
