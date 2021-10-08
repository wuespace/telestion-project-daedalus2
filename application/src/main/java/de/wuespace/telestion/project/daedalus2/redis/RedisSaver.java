package de.wuespace.telestion.project.daedalus2.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisBaseConfiguration;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.redis.client.Command;
import io.vertx.redis.client.RedisAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Saves data into both key-value-based storage ("latest/") and time-series data for numbers ("ts/").
 * <p>
 * Uses requestLatestAddress as prefix and "/" as separator for Redis keys. Objects get saved both as a whole and
 * each property
 * recursively, with the key being appended to the object with "/" in between.
 *
 * @author Pablo Klaschka
 */
public class RedisSaver extends RedisVerticle<RedisSaver.Configuration> {
	private final static Logger logger = LoggerFactory.getLogger(RedisSaver.class);

	public RedisSaver(Configuration config) {
		super(config);
	}

	public RedisSaver() {
		this(null);
	}

	public static void main(String[] args) {
		var vertx = Vertx.vertx();
		vertx.deployVerticle(new RedisSaver(new Configuration(
				"redis://localhost:6379",
				10,
				List.of("systemT")
		)));
	}

	@Override
	protected Class<Configuration> getConfigurationClass() {
		return Configuration.class;
	}

	@Override
	protected void onRedisConnectionEstablished(Promise<Void> startPromise) {
		var eb = vertx.eventBus();

		config.inAddresses().forEach(inAddress ->
				eb.consumer(inAddress, message -> {
					logger.debug(message.body().toString() + message.headers().toString());

					if (this.redisApi == null)
						logger.warn("Unable to save message into Redis DB because it's currently disconnected.");
					else {
						logger.debug("Saving message to Redis DB");
						redisApi.set(List.of("latest/" + inAddress, message.body().toString()));
						try {
							var obj = (new ObjectMapper()).readTree(message.body().toString());
							logger.debug(obj.fieldNames().toString());
							this.saveNewDataset(redisApi, inAddress, obj, message.headers());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				})
		);

		startPromise.complete();
	}

	/**
	 * Saves a new dataset (recursively), including all "sub-properties" of Json Objects
	 *  @param api    the Redis API reference
	 * @param prefix the prefix under which the node should get saved
	 * @param node   the node that gets saved
	 * @param headers the message headers, including <code>"time"</code> and <code>"receive-time"</code> information
	 */
	private void saveNewDataset(RedisAPI api, String prefix, JsonNode node, MultiMap headers) {
		if (node.isObject()) {
			api.set(List.of("latest/" + prefix, node.toString()));
			node.fields().forEachRemaining(
					stringJsonNodeEntry -> this.saveNewDataset(
							api,
							prefix + "/" + stringJsonNodeEntry.getKey(),
							stringJsonNodeEntry.getValue(),
							headers)
			);
		} else if (node.isNumber()) {
			var value = node.toString();
			api.set(List.of("latest/" + prefix, value));
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
			api.set(List.of("latest/" + prefix, node.toString()));
		}

		api.set(List.of("latest-receive-time/" + prefix, headers.get("receive-time")));
		api.set(List.of("latest-time/" + prefix, headers.get("time")));
	}

	public record Configuration(
			@JsonProperty String connectionString,
			@JsonProperty int reconnectAttempts,
			@JsonProperty List<String> inAddresses
	) implements RedisBaseConfiguration {
		public Configuration() {
			this("redis://redis", 10, List.of());
		}
	}
}
