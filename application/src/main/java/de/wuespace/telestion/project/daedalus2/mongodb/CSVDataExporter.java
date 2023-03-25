package de.wuespace.telestion.project.daedalus2.mongodb;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.daedalus2.mongodb.message.CSVData;
import de.wuespace.telestion.project.daedalus2.mongodb.message.CSVDataRequest;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import java.util.*;
import java.util.stream.Collectors;

public class CSVDataExporter extends TelestionVerticle<CSVDataExporter.Configuration> implements WithEventBus {

	public static final String SEPARATOR = ";";

	public static final String[] HEADERS = new String[]{
			"datetime",
			"iridium.cdr",
			"iridium.imei",
			"iridium.session_status",
			"iridium.momsn",
			"iridium.mtmns",
			"iridium.time",
			"iridium.latitude",
			"iridium.longitude",
			"iridium.center_point_radius",
			"payload.gps_fix_avail",
			"payload.seed_state",
			"payload.valid.latitude",
			"payload.valid.longitude",
			"payload.valid.altitude",
			"payload.latest.latitude",
			"payload.latest.longitude",
			"payload.latest.altitude"
	};

	public record Configuration(
			@JsonProperty String requestAddress,
			@JsonProperty String host,
			@JsonProperty int port,
			@JsonProperty String dbName,
			@JsonProperty String username,
			@JsonProperty String password,
			@JsonProperty String dbPoolName,
			// target name - target IMEI
			@JsonProperty Map<String, String> imeiMapping
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, "localhost", 27017, "daedalus2", null, null, "d2Pool", new HashMap<>());
		}
	}

	@Override
	public void onStart() throws Exception {
		logger.debug("Start verticle");
		setDefaultConfig(new Configuration());
		var dbConfig = new JsonObject()
				.put("db_name", getConfig().dbName())
				.put("useObjectId", true)
				.put("host", getConfig().host())
				.put("port", getConfig().port())
				.put("username", getConfig().username())
				.put("password", getConfig().password());
		this.client = MongoClient.createShared(vertx, dbConfig, getConfig().dbPoolName());
		register(getConfig().requestAddress(), this::handle, CSVDataRequest.class);
	}

	@Override
	public void onStop(Promise<Void> stopPromise) throws Exception {
		client.close().onComplete(stopPromise);
	}

	private MongoClient client;

	private void handle(CSVDataRequest request, Message<Object> message) {
		getAllIridiumMessages(request.target())
				.map(this::createCSVData)
				.onSuccess(message::reply)
				.onFailure(cause -> message.fail(500, cause.getMessage()));
	}

	private CSVData createCSVData(List<JsonObject> results) {
		var header = String.join(SEPARATOR, HEADERS) + '\n';
		var body = results.stream()
				.map(this::extractInfos)
				.map(this::mergeInfos)
				.collect(Collectors.joining("\n"));

		return new CSVData(0, header + body);
	}

	private Future<List<JsonObject>> getAllIridiumMessages(String target) {
		var document = new JsonObject()
				.put("iridium", new JsonObject().put("$exists", true))
				.put("iridium.imei", getConfig().imeiMapping().getOrDefault(target, ""));

		return client.find("d2", document);
	}

	private String[] extractInfos(JsonObject result) {
		var datetime = result.getJsonObject("datetime", new JsonObject());
		var iridium = result.getJsonObject("iridium", new JsonObject());
		var payload = result.getJsonObject("payload", new JsonObject());
		var valid = payload.getJsonObject("valid", new JsonObject());
		var latest = payload.getJsonObject("latest", new JsonObject());
		var infos = new String[HEADERS.length];

		// datetime
		getString(infos, 0, datetime, "$date");
		// iridium
		getLong(infos, 1, iridium, "cdr");
		getString(infos, 2, iridium, "imei");
		getInteger(infos, 3, iridium, "session_status");
		getInteger(infos, 4, iridium, "momsn");
		getInteger(infos, 5, iridium, "mtmsn");
		getLong(infos, 6, iridium, "time");
		getDouble(infos, 7, iridium, "latitude");
		getDouble(infos, 8, iridium, "longitude");
		getDouble(infos, 9, iridium, "center_point_radius");
		// payload
		getBoolean(infos, 10, payload, "gps_fix_avail");
		getInteger(infos, 11, payload, "seed_state");
		getDouble(infos, 12, valid, "latitude");
		getDouble(infos, 13, valid, "longitude");
		getInteger(infos, 14, valid, "altitude");
		getDouble(infos, 15, latest, "latitude");
		getDouble(infos, 16, latest, "longitude");
		getInteger(infos, 17, latest, "altitude");

		return infos;
	}

	private String mergeInfos(String[] infos) {
		return String.join(SEPARATOR, infos);
	}

	private static void getInteger(String[] result, int pos, JsonObject obj, String key) {
		try {
			var value = obj.getInteger(key);
			result[pos] = Objects.nonNull(value) ? value.toString() : "";
		} catch (ClassCastException ignored) {
			result[pos] = "";
		}
	}

	private static void getLong(String[] result, int pos, JsonObject obj, String key) {
		try {
			var value = obj.getLong(key);
			result[pos] = Objects.nonNull(value) ? value.toString() : "";
		} catch (ClassCastException ignored) {
			result[pos] = "";
		}
	}

	private static void getDouble(String[] result, int pos, JsonObject obj, String key) {
		try {
			var value = obj.getDouble(key);
			result[pos] = Objects.nonNull(value) ? value.toString() : "";
		} catch (ClassCastException ignored) {
			result[pos] = "";
		}
	}

	private static void getBoolean(String[] result, int pos, JsonObject obj, String key) {
		try {
			var value = obj.getBoolean(key);
			result[pos] = Objects.nonNull(value) ? value ? "true" : "false" : "";
		} catch (ClassCastException ignored) {
			result[pos] = "";
		}
	}

	private static void getString(String[] result, int pos, JsonObject obj, String key) {
		result[pos] = obj.getString(key, "");
	}
}
