package de.wuespace.telestion.project.daedalus2.mongodb;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.extension.mongodb.DbResponse;
import de.wuespace.telestion.extension.mongodb.MongoDatabaseService;
import de.wuespace.telestion.services.message.Address;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * MongoDatabaseService is a verticle which connects to a local running MongoDB-Database and listens for incoming
 * database requests to process.
 * Mongo specific:
 * Data is always saved in their exclusive collection which is always named after their Class.name.
 *
 * @author Jan Tischhöfer
 * @version 07-05-2021
 */
@SuppressWarnings("unused")
public final class MongoDatabaseSaver extends AbstractVerticle {
	/**
	 * Configuration for the {@link MongoDatabaseSaver} verticle.
	 * <br />
	 * <a href="https://vertx.io/docs/4.1.0/vertx-mongo-client/java/">
	 * https://vertx.io/docs/4.1.0/vertx-mongo-client/java/
	 * </a>
	 *
	 * @param host       The host the MongoDB instance is running. Defaults to {@code 127.0.0.1}.
	 * @param port       The port the MongoDB instance is listening on. Defaults to {@code 27017}.
	 * @param dbName     Name of the database in the MongoDB instance to use. Defaults to {@code daedalus2}.
	 * @param username   The username to authenticate. Default is {@code null}. (meaning no authentication required)
	 * @param password   The password to use to authenticate.
	 * @param dbPoolName The data source name in MongoDB which is shared between other MongoDB verticles.
	 */
	private static record Configuration(@JsonProperty String host,
										@JsonProperty int port,
										@JsonProperty String dbName,
										@JsonProperty String username,
										@JsonProperty String password,
										@JsonProperty String dbPoolName) {
		Configuration() {
			this("127.0.0.1", 27017, "daedalus2", null, null, "d2Pool");
		}
	}

	@Override
	public void start(Promise<Void> startPromise) {
		config = Config.get(forcedConfig, new Configuration(), config(), Configuration.class);
		var dbConfig = new JsonObject()
				.put("db_name", config.dbName)
				.put("useObjectId", true)
				.put("host", config.host)
				.put("port", config.port)
				.put("username", config.username)
				.put("password", config.password);
		this.client = MongoClient.createShared(vertx, dbConfig, config.dbPoolName);
		this.registerConsumers();

		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) {
		this.client.close(result -> {
			if (result.failed()) {
				stopPromise.fail(result.cause());
				return;
			}
			stopPromise.tryComplete();
		});
	}

	/**
	 * This constructor supplies default options.
	 *
	 * @param host       The host the MongoDB instance is running.
	 * @param port       The port the MongoDB instance is listening on.
	 * @param dbName     Name of the database in the MongoDB instance to use.
	 * @param username   The username to authenticate.({@code null} meaning no authentication required)
	 * @param password   The password to use to authenticate.
	 * @param dbPoolName The data source name in MongoDB which is shared between other MongoDB verticles.
	 */
	public MongoDatabaseSaver(String host, int port,
								String dbName,
								String username, String password,
								String dbPoolName) {
		this.forcedConfig = new Configuration(host, port, dbName, username, password, dbPoolName);
	}

	/**
	 * If this constructor is used at all, settings have to be specified in the config file.
	 */
	public MongoDatabaseSaver() {
		this.forcedConfig = null;
	}

	public Configuration getConfig() {
		return config;
	}

	private final Logger logger = LoggerFactory.getLogger(MongoDatabaseSaver.class);
	private final Configuration forcedConfig;
	private Configuration config;
	private MongoClient client;

	private final String inSave = Address.incoming(MongoDatabaseSaver.class, "save");
	private final String outSave = Address.outgoing(MongoDatabaseService.class, "save");

	/**
	 * Method to register consumers to the eventbus.
	 */
	private void registerConsumers() {
		vertx.eventBus().consumer(inSave, document -> this.save((JsonObject) document.body()));
	}

	/**
	 * Save the received document to the database.
	 * If a MongoDB-ObjectId is specified data will be upserted, meaning if the id does not exist it will be inserted,
	 * otherwise it will be updated. Else it will be inserted with a new id.
	 * Additionally, the current date/time is added for future queries regarding date and time.
	 * If the save was successful the database looks for the newly saved document and publishes it to the database
	 * outgoing address concatenated with "/Class.name".
	 * Through this behaviour clients (e.g. GUI) can listen
	 * to the outgoing address of a specific data value and will always be provided with the most recent data.
	 *
	 * @param document a JsonMessage validated through the {@code JsonMessage.on} method
	 */
	private void save(JsonObject document) {
		var dateString = getISO8601StringForDate(new Date());
		document.put("datetime", new JsonObject().put("$date", dateString));

		logger.info("Saving to MongoDB: {}", document.encode());
		client.save("d2", document, res -> {
			if (res.failed()) {
				logger.error("DB Save failed: ", res.cause());
				return;
			}
			String id = res.result();
			client.find("d2", new JsonObject().put("_id", id), rec -> {
				if (rec.failed()) {
					logger.error("DB Find failed: ", rec.cause());
					return;
				}
				DbResponse dbRes = new DbResponse(rec.result());
				vertx.eventBus().publish(outSave.concat("/").concat("d2"), dbRes.json());
			});
		});
	}

	/**
	 * Helper function to convert a {@link Date} to a ISO-8601 Date/Time string.
	 *
	 * @param date {@link Date} that should be converted.
	 * @return ISO-8601 Date/Time string representation
	 */
	private static String getISO8601StringForDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.GERMANY);
		dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
		var dateString = dateFormat.format(date);
		return dateFormat.format(date);
	}
}
