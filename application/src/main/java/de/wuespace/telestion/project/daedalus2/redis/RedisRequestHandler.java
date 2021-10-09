package de.wuespace.telestion.project.daedalus2.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisBaseConfiguration;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisVerticle;
import de.wuespace.telestion.services.connection.EventbusTcpBridge;
import de.wuespace.telestion.services.monitoring.MessageLogger;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.redis.client.Command;
import io.vertx.redis.client.ResponseType;
import io.vertx.redis.client.impl.types.MultiType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles requests for both "latest/" (key-value-based) and "ts/" (time series) data.
 *
 * @author Pablo Klaschka
 */
@SuppressWarnings("unused")
public class RedisRequestHandler extends RedisVerticle<RedisRequestHandler.Configuration> {
	private static final Logger logger = LoggerFactory.getLogger(RedisRequestHandler.class);

	public RedisRequestHandler(Configuration config) {
		super(config);
	}

	public RedisRequestHandler() {
		this(null);
	}

	@Override
	protected Class<Configuration> getConfigurationClass() {
		return Configuration.class;
	}

	@Override
	protected void onRedisConnectionEstablished(Promise<Void> startPromise) {
		var eb = getVertx().eventBus();

		eb.consumer(config.requestLatestAddress(), message -> JsonMessage.on(RedisLatestRequest.class, message,
				redisLatestRequest -> {
					if (this.redisApi == null) {
						message.fail(3,
								"Not connected to " +
										"Redis");
					} else {
						redisApi.mget(redisLatestRequest.fields())
								.onSuccess(result -> message
										.reply(Json.decodeValue(result.toString())));
					}
				}));

		eb.consumer(config.requestKeysAddress(), message -> {
			var body = message.body();

			if (body instanceof String query) {
				logger.info(query);
				redisApi.keys(query)
						.onSuccess(rawResult -> {
							var result = new JsonArray();

							if (rawResult.type() == ResponseType.MULTI) {
								rawResult.stream().forEach(value -> {
									result.add(value.toString());
								});

							}

							message.reply(result);
						});
			} else {
				message.fail(1, "Body must be a string");
			}
		});

		eb.consumer(config.requestTimeSeriesAddress(), message -> JsonMessage.on(RedisTimeSeriesRequest.class, message
				, request -> {
					if (this.redisApi == null) {
						message.fail(3, "Not connected to Redis");
					} else {
						@SuppressWarnings("rawtypes") List<Future> results =
								request.fields().stream()
										.map(this::fetchTSAggregation)
										.collect(Collectors.toList());

						CompositeFuture.all(results).onSuccess(allResults ->
								message.reply(new JsonArray(allResults.list()))
						);
					}
				}));
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
}
