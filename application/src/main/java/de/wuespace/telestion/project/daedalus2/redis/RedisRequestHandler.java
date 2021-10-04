package de.wuespace.telestion.project.daedalus2.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisBaseConfiguration;
import de.wuespace.telestion.project.daedalus2.redis.base.RedisVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.redis.client.Command;
import io.vertx.redis.client.impl.types.MultiType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles requests for both "latest/" (key-value-based) and "ts/" (time series) data.
 *
 * @author Pablo Klaschka
 */
public class RedisRequestHandler extends RedisVerticle<RedisRequestHandler.Configuration> {
	private final static Logger logger = LoggerFactory.getLogger(RedisRequestHandler.class);

	public RedisRequestHandler(Configuration config) {
		super(config);
	}

	public static void main(String[] args) {
		var vertx = Vertx.vertx();
		vertx.deployVerticle(new RedisRequestHandler(new Configuration(
				"redis://localhost:6379",
				10,
				"request-latest",
				"request-time-series"
		)));

		vertx.setPeriodic(Duration.ofSeconds(2).toMillis(), handler -> {
			logger.debug("Requesting ['latest/systemT/batTemp', 'n/a', 'latest/systemT']...");
			vertx.eventBus().request("request-latest", new RedisLatestRequest(new String[]{"latest/systemT/batTemp",
					"n/a", "latest/systemT"}).json())
					.onSuccess(res -> logger.debug(new JsonArray(res.body().toString()).encode()));
		});

		vertx.setPeriodic(Duration.ofSeconds(5).toMillis(), handler -> {
			logger.debug("Requesting time series data...");
			vertx.eventBus().request(
					"request-time-series", new RedisTimeSeriesRequest(
							new RedisTimeSeriesSpecification[]{
									new RedisTimeSeriesSpecification("ts/systemT/timeLocal", "-", "+", 50000,
											new String[]{"avg", "min", "max", "count", "last", "first", "var.s"}),
									new RedisTimeSeriesSpecification("ts/systemT/batTemp", "-", "+", 50000,
											new String[]{"avg", "min", "max", "count"}),
									new RedisTimeSeriesSpecification("ts/systemT/batTemp", "-", "+", 50000,
											new String[]{"avg", "min", "max", "count"}),
									new RedisTimeSeriesSpecification("ts/systemT/batTemp", "-", "+", 50000,
											new String[]{"avg", "min", "max", "count"}),
									new RedisTimeSeriesSpecification("ts/systemT/batTemp", "-", "+", 50000,
											new String[]{"avg", "min", "max", "count"}),
							}
					).json()
			)
					.onSuccess(res -> logger.debug(res.body().toString()));
		});
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
						redisApi.mget(List.of(
								redisLatestRequest.fields()))
								.onSuccess(result -> message
										.reply(result.toString()));
					}
				}));

		eb.consumer(config.requestTimeSeriesAddress(), message -> JsonMessage.on(RedisTimeSeriesRequest.class, message
				, request -> {
					if (this.redisApi == null) {
						message.fail(3, "Not connected to Redis");
					} else {
						var results = Arrays.stream(request.fields()).map(this::fetchTSAggregation);

						CompositeFuture.all(results.collect(Collectors.toList())).onSuccess(allResults ->
								message.reply(new JsonArray(allResults.list()).toString())
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
		if (spec.aggregations().length < 1) {
			return Future.failedFuture("Invalid spec. Must have at least one aggregation specified.");
		}


		//region Run Redis queries for all selected aggregations
		//noinspection rawtypes
		var allAggregationRequests = Arrays.stream(spec.aggregations()).map(aggregation ->
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
					for (int i = 0; i < spec.aggregations().length; i++) {
						var aggregation = spec.aggregations()[i];

						for (var point : list.get(i)) {
							if (i == 0) {
								// first aggregation of point => create HashMap
								results.put(point.get(0).toLong(), new HashMap<>(3));
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
			@JsonProperty String requestTimeSeriesAddress
	) implements RedisBaseConfiguration {
		public Configuration() {
			this("redis://redis", 10, null, null);
		}
	}
}
