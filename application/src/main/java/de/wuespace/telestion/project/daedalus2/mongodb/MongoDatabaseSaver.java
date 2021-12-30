package de.wuespace.telestion.project.daedalus2.mongodb;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.services.message.Address;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import java.util.Date;

import static de.wuespace.telestion.project.daedalus2.util.DateUtils.getISO8601StringForDate;

/**
 * MongoDatabaseService is a verticle which connects to a local running MongoDB-Database and listens for incoming
 * database requests to process.
 * Mongo specific:
 * Data is always saved in their exclusive collection which is always named after their Class.name.
 *
 * @author Jan Tischhöfer, Pablo Klaschka, Ludwig Richter
 * @version 07-05-2021
 */
@SuppressWarnings("unused")
public class MongoDatabaseSaver extends TelestionVerticle<MongoDatabaseSaver.Configuration> implements WithEventBus {

	public static final String SAVE_ADDRESS = Address.incoming(MongoDatabaseSaver.class, "save");

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
	public record Configuration(
			@JsonProperty String host,
			@JsonProperty int port,
			@JsonProperty String dbName,
			@JsonProperty String username,
			@JsonProperty String password,
			@JsonProperty String dbPoolName
	) implements TelestionConfiguration {
		Configuration() {
			this("127.0.0.1", 27017, "daedalus2", null, null, "d2Pool");
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		var dbConfig = new JsonObject()
				.put("db_name", getConfig().dbName())
				.put("useObjectId", true)
				.put("host", getConfig().host())
				.put("port", getConfig().port())
				.put("username", getConfig().username())
				.put("password", getConfig().password());
		this.client = MongoClient.createShared(vertx, dbConfig, getConfig().dbPoolName());
		register(SAVE_ADDRESS, message -> save((JsonObject) message.body()));
	}

	@Override
	public void onStop(Promise<Void> stopPromise) {
		this.client.close(result -> {
			if (result.failed()) {
				stopPromise.fail(result.cause());
				return;
			}
			stopPromise.tryComplete();
		});
	}

	private MongoClient client;

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

		String encodedJSON = document.encode();
		logger.info("Saving to MongoDB: {}", encodedJSON);
		client.save("d2", document);
	}
}
