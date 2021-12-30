package de.wuespace.telestion.project.daedalus2.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisBaseConfiguration;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.redis.client.Command;
import io.vertx.redis.client.ResponseType;
import io.vertx.redis.client.impl.types.MultiType;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles requests for both "latest/" (key-value-based) and "ts/" (time series) data.
 *
 * @author Pablo Klaschka, Ludwig Richter
 */
@SuppressWarnings("unused")
public class RedisRequestHandler extends RedisVerticle<RedisRequestHandler.Configuration> implements WithEventBus {

	public record Configuration(
			@JsonProperty String connectionString,
			@JsonProperty int reconnectAttempts,
			@JsonProperty String requestLatestAddress,
			@JsonProperty String requestTimeSeriesAddress,
			@JsonProperty String requestKeysAddress
	) implements RedisBaseConfiguration {
		public Configuration() {
			this("redis://redis", 10, null, null, null);
		}
	}

	@Override
	protected void onRedisConnectionEstablished(Promise<Void> startPromise) {
		setDefaultConfig(new Configuration());
		register(getConfig().requestLatestAddress(), this::handleLatestRequest, RedisLatestRequest.class);
		register(getConfig().requestKeysAddress(), this::handleKeysRequest);
		register(getConfig().requestTimeSeriesAddress(), this::handleTimeSeriesRequest, RedisTimeSeriesRequest.class);
	}

	private void handleLatestRequest(RedisLatestRequest request, Message<Object> message) {
		if (!isConnected()) {
			message.fail(3, "Not connected to Redis");
			return;
		}

		redisApi.mget(request.fields())
				.onSuccess(result -> message.reply(Json.decodeValue(result.toString())));
	}

	private void handleKeysRequest(Message<Object> message) {
		if (!isConnected()) {
			message.fail(3, "Not connected to Redis");
			return;
		}

		var body = message.body();

		if (body instanceof String query) {
			logger.info(query);
			redisApi.keys(query)
					.onSuccess(rawResult -> {
						var result = new JsonArray();

						if (rawResult.type() == ResponseType.MULTI) {
							rawResult.stream().forEach(value -> result.add(value.toString()));
						}

						message.reply(result);
					});
		} else {
			message.fail(1, "Body must be a string");
		}
	}

	private void handleTimeSeriesRequest(RedisTimeSeriesRequest request, Message<Object> message) {
		if (!isConnected()) {
			message.fail(3, "Not connected to Redis");
			return;
		}

		@SuppressWarnings("rawtypes") List<Future> results =
				request.fields().stream()
						.map(this::fetchTSAggregation)
						.collect(Collectors.toList());

		CompositeFuture.all(results).onSuccess(allResults ->
				message.reply(new JsonArray(allResults.list()))
		);
	}

	/**
	 * Fetches the aggregation data (time series) for a single field based on an {@link RedisTimeSeriesSpecification}
	 *
	 * @param spec the time series specification
	 * @return a {@link Future} that completes with the resulting {@link JsonArray}
	 */
	private Future<JsonArray> fetchTSAggregation(RedisTimeSeriesSpecification spec) {
		if (spec.aggregations().isEmpty()) {
			return Future.failedFuture("Invalid spec. Must have at least one aggregation specified.");
		}


		//region Run Redis queries for all selected aggregations
		//noinspection rawtypes
		var allAggregationRequests = spec.aggregations().stream().map(aggregation ->
				(Future) redisApi.send(
						Command.create("TS.RANGE"),
						spec.key(),
						spec.from(),
						spec.to(),
						"AGGREGATION",
						aggregation,
						"" + spec.bucketSize()
				)
		).toList();

		Future<List<MultiType>> allQueries = CompositeFuture.all(allAggregationRequests).map(CompositeFuture::list);
		//endregion

		return allQueries.map(list -> {
					// timestamp => ( aggregationName => value )
					var results = new HashMap<Long, HashMap<String, Double>>();

					// Merge aggregation data
					for (int i = 0; i < spec.aggregations().size(); i++) {
						var aggregation = spec.aggregations().get(i);

						for (var point : list.get(i)) {
							if (i == 0) {
								// first aggregation of point => create HashMap
								results.put(point.get(0).toLong(), new HashMap<>(spec.aggregations().size()));
							}
							results.get(point.get(0).toLong()).put(aggregation, point.get(1).toDouble());
						}
					}

					// convert everything to an array
					var resultArray = new JsonArray();

					results.forEach((aLong, stringDoubleHashMap) ->
							resultArray.add(new JsonArray().add(aLong).add(stringDoubleHashMap))
					);

					return resultArray;
				}

		);
	}
}
