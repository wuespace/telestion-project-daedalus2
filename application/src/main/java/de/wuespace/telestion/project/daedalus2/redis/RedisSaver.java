package de.wuespace.telestion.project.daedalus2.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wuespace.telestion.project.daedalus2.messages.SystemT;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisBaseConfiguration;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.redis.client.Command;
import io.vertx.redis.client.RedisAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Saves data into both key-value-based storage ("latest/") and time-series data for numbers ("ts/").
 * <p>
 * Uses requestLatestAddress as prefix and "/" as separator for Redis keys. Objects get saved both as a whole and each property
 * recursively, with the key being appended to the object with "/" in between.
 *
 * @author Pablo Klaschka
 */
public class RedisSaver extends RedisVerticle<RedisSaver.Configuration> {
	public RedisSaver(Configuration config) {
		super(config);
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		super.start(startPromise);
	}

	@Override
	protected Class<Configuration> getConfigurationClass() {
		return Configuration.class;
	}

	@Override
	protected void onRedisConnectionEstablished(Promise<Void> startPromise) {
		var eb = vertx.eventBus();

		eb.consumer(config.inAddress(), message -> {
			logger.debug(message.body().toString());

			if (this.redisApi == null)
				logger.warn("Unable to save message into Redis DB because it's currently disconnected.");
			else {
				logger.debug("Saving message to Redis DB");
				redisApi.set(List.of("latest/" + config.inAddress(), message.body().toString()));
				try {
					var obj = (new ObjectMapper()).readTree(message.body().toString());
					logger.debug(obj.fieldNames().toString());
					this.saveNewDataset(redisApi, config.inAddress(), obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		startPromise.complete();
	}

	/**
	 * Saves a new dataset (recursively), including all "sub-properties" of Json Objects
	 *
	 * @param api    the Redis API reference
	 * @param prefix the prefix under which the node should get saved
	 * @param node   the node that gets saved
	 */
	private void saveNewDataset(RedisAPI api, String prefix, JsonNode node) {
//		logger.debug("saveNewDataset %s %s".formatted(prefix, node));
		if (node.isObject()) {
			api.set(List.of("latest/" + prefix, node.toString()));
			node.fields().forEachRemaining(
					stringJsonNodeEntry -> this.saveNewDataset(
							api,
							prefix + "/" + stringJsonNodeEntry.getKey(),
							stringJsonNodeEntry.getValue()
					)
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
	}

	public record Configuration(
			@JsonProperty String connectionString,
			@JsonProperty int reconnectAttempts,
			@JsonProperty String inAddress
	) implements RedisBaseConfiguration {
		public Configuration() {
			this(null, 10, null);
		}
	}

	public static void main(String[] args) {
		var vertx = Vertx.vertx();
		vertx.deployVerticle(new RedisSaver(new Configuration(
				"redis://localhost:6379",
				10,
				"systemT"
		)));

		vertx.setPeriodic(Duration.ofSeconds(2).toMillis(), handler -> vertx.eventBus().publish("systemT", new SystemT().json()));
	}

	private final static Logger logger = LoggerFactory.getLogger(RedisSaver.class);
}
