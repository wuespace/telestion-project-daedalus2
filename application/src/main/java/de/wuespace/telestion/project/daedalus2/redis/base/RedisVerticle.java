package de.wuespace.telestion.project.daedalus2.redis.base;

import de.wuespace.telestion.api.verticle.TelestionVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.RedisConnection;

/**
 * A base class for writing verticles that interact with the Redis DB.
 * <p>
 * Use <code>onRedisConnectionEstablished</code> to set up all eventbus connections that already require a Redis
 * connection. Interact with the Redis DB via <code>redisApi</code>. Please note that this might be <code>null</code>
 * if the connection got lost (the verticle will automatically try to reconnect, as per the
 * {@link RedisBaseConfiguration} options).
 *
 * @author Pablo Klaschka, Ludwig Richter
 */
public abstract class RedisVerticle<T extends RedisBaseConfiguration> extends TelestionVerticle<T> {
	protected RedisAPI redisApi;

	@Override
	public void onStart(Promise<Void> startPromise) {
		createRedisClient(getConfig().connectionString()).onSuccess(conn -> {
			logger.info("Successfully connected to Redis");
			this.onRedisConnectionEstablished(startPromise);
		}).onFailure(event -> {
			logger.error("Couldn't connect to Redis DB", event);
			startPromise.fail(event);
		});
	}

	/**
	 * Gets called after the Redis connection was established. Use this to set up all event bus interactions
	 *
	 * @param startPromise the promise for a start. Call <code>startPromise.complete()</code> once your setup is done.
	 */
	protected abstract void onRedisConnectionEstablished(Promise<Void> startPromise);

	private Future<RedisConnection> createRedisClient(String connectionString) {
		Promise<RedisConnection> promise = Promise.promise();

		Redis.createClient(vertx, connectionString)
				.connect()
				.onSuccess(conn -> {
					this.redisApi = RedisAPI.api(conn);

					// make sure the client is reconnected on error
					conn.exceptionHandler(e -> {
						this.redisApi = null;
						logger.warn("An error occurred in RedisVerticle " + getClass().toString(), e);
						// attempt to reconnect,
						// if there is an unrecoverable error
						attemptReconnect(0, connectionString);
					});

					// allow further processing
					promise.complete(conn);
				})
				.onFailure(promise::fail);

		return promise.future();
	}

	@SuppressWarnings("BooleanMethodIsAlwaysInverted")
	protected boolean isConnected() {
		return this.redisApi != null;
	}

	private void attemptReconnect(int retry, String connectionString) {
		logger.debug("attemptReconnect {}", retry);
		if (retry > getConfig().reconnectAttempts()) {
			// we should stop now, as there's nothing we can do.
			logger.error("Unable to reconnect to Redis DB. Restart Telestion to try again.");
		} else {
			// retry with backoff up to 10240 ms
			long backoff = (long) (Math.pow(2, retry) * 10);

			vertx.setTimer(backoff, timer -> {
				logger.debug("Timer reconnect");
				createRedisClient(connectionString)
						.onFailure(t -> attemptReconnect(retry + 1, connectionString));
			});
		}
	}
}
