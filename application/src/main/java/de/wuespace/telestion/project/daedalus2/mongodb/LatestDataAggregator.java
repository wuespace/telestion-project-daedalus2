package de.wuespace.telestion.project.daedalus2.mongodb;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.extension.mongodb.DbRequest;
import de.wuespace.telestion.extension.mongodb.MongoDatabaseService;
import de.wuespace.telestion.services.message.Address;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class LatestDataAggregator extends AbstractVerticle {
	public static record Configuration(
			@JsonProperty String collection,
			@JsonProperty String field,
			@JsonProperty int rate,
			@JsonProperty String outAddress
	) {
		public Configuration() {
			this(null, null, 0, null);
		}
	}

	@Override
	public void start(Promise<Void> startPromise) {
		config = Config.get(forcedConfig, new Configuration(), config(), Configuration.class);
		var delay = getRateInMillis(config.rate());
		logger.info("Request delay: {} ms, for: {}::{}, out on: {}",
				delay, config.collection(), config.field(), config.outAddress());

		request = getDbRequest();
		timerId = vertx.setPeriodic(delay, this::databaseRequest);
		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) {
		vertx.cancelTimer(timerId);
		stopPromise.complete();
	}

	public LatestDataAggregator(String collection, String field, int rate, String outAddress) {
		this.forcedConfig = new Configuration(collection, field, rate, outAddress);
	}

	public LatestDataAggregator() {
		this.forcedConfig = null;
	}

	/**
	 * Returns the current configuration for this verticle.
	 * If the verticle is not started yet, the configuration is {@code null}.
	 * @return the current configuration for this verticle
	 */
	public Configuration getConfig() {
		return config;
	}

	/**
	 * The forced configuration from constructor.
	 * It will override the configuration of the verticle once it is started.
	 */
	private final Configuration forcedConfig;

	/**
	 * The configuration for this verticle.
	 * If the verticle is not started yet, it is {@code null}.
	 */
	private Configuration config;

	/**
	 * The database request to get the latest data from the MongoDB database verticle.
	 */
	private DbRequest request;

	/**
	 * The timer id from the constructed vert.x periodic timer once the verticle is started.
	 */
	private Long timerId;

	private final Logger logger = LoggerFactory.getLogger(LatestDataAggregator.class);

	/**
	 * Requests the latest data from the database and publishes it on the eventbus.
	 * @param timerId - the id of the periodic timer
	 */
	private void databaseRequest(Long timerId) {
		logger.debug("Requesting latest data...");

		vertx.eventBus().request(findAddress, request.json(), (Handler<AsyncResult<Message<JsonObject>>>) reply -> {
			if (reply.failed()) {
				logger.error("Cannot request latest data:", reply.cause());
				return;
			}

			var body = reply.result().body();
			if (body != null) {
				logger.debug("Received: {}", body);

				var results = body.getJsonArray("result");
				if (results.size() > 0) {
					vertx.eventBus().publish(config.outAddress(), results.getJsonObject(0));
				} else {
					logger.debug("No entry in database yet");
				}
			} else {
				logger.warn("Received no content from database");
			}
		});
	}

	/**
	 * Returns a MongoDB request for the latest data in the database
	 * based on the current configuration.
	 * @return a database request to get the latest data
	 */
	private DbRequest getDbRequest() {
		var fieldsList = new ArrayList<String>();
		var pathComponents = config.field().split("\\.");

		fieldsList.add(config.field()); // add requested field
		fieldsList.add("className"); // request global className

		// request all sub classNames
//		StringBuilder basePath = new StringBuilder();
//		for (String component : pathComponents) {
//			fieldsList.add(basePath + component + ".className");
//			basePath.append(component);
//		}

		// we want to have the most recent data -> sort after datetime
		var sortList = new ArrayList<String>();
		sortList.add("datetime");

		return new DbRequest(
				config.collection(),
				new JsonObject().toString(),
				fieldsList,
				sortList,
				1,
				0,
				config.field()
		);
	}

	/**
	 * Helper function to turn rate into milliseconds.
	 *
	 * @param rate the desired data rate
	 * @return milliseconds of (1/rate)
	 */
	private static long getRateInMillis(int rate) {
		return (long) ((1.0 / rate) * 1000.5);
	}

	/**
	 * The eventbus address for the MongoDB database service for find queries.
	 */
	private static final String findAddress =
			Address.incoming(MongoDatabaseService.class, "find");
}
