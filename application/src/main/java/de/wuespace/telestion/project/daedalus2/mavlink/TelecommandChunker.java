package de.wuespace.telestion.project.daedalus2.mavlink;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.api.verticle.trait.WithSharedData;
import de.wuespace.telestion.services.connection.rework.RawMessage;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;

import java.time.Duration;

import static de.wuespace.telestion.project.daedalus2.util.ByteArrayUtils.concatData;
import static de.wuespace.telestion.project.daedalus2.util.ByteArrayUtils.splitData;

/**
 * Splits outgoing telecommands into smaller chunks and send them in a regular time interval.
 * It gives regular updates on current chunks, time interval, chunk size, etc. on Redis readable channels.
 *
 * @author Ludwig Richter
 */
@SuppressWarnings("unused")
public class TelecommandChunker extends TelestionVerticle<TelecommandChunker.Configuration>
		implements WithEventBus, WithSharedData {

	public static final String REDIS_NAME = "message-chunker";

	public record Configuration(
			@JsonProperty String inAddress,
			@JsonProperty String outAddress,
			@JsonProperty int chunkSize,
			@JsonProperty int timePerMessage
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, null, 64, 10);
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		var delay = Duration.ofMillis(getConfig().timePerMessage()).toMillis();
		// setup interval to publish chunks in regular intervals
		vertx.setPeriodic(delay, this::publishChunk);
		// setup consumer to add new data to chunk list
		register(getConfig().inAddress(), this::addMessage, RawMessage.class);
		// fill Redis the first time
		notify(0);
	}

	private void publishChunk(long intervalId) {
		var data = getData();
		// stop if data is empty
		if (data.length == 0) return;
		var splitData = splitData(data, getConfig().chunkSize());
		setData(splitData.remainder());

		publish(getConfig().outAddress(), splitData.toRawMessage());
	}

	private void addMessage(RawMessage message) {
		var newData = concatData(getData(), message.data());
		setData(newData);
	}

	private byte[] getData() {
		var map = getLocalMap();
		return map.getOrDefault(CHUNK_FIELD_KEY, new byte[]{});
	}

	private void setData(byte[] data) {
		var map = getLocalMap();
		map.put(CHUNK_FIELD_KEY, data);
		notify(data.length);
	}

	private void notify(int newSize) {
		final long currentTime = System.currentTimeMillis();
		logger.debug("New chunk list size is {}", newSize);

		var values = new JsonObject()
				.put("list-size", newSize)
				.put("chunk-size", getConfig().chunkSize())
				.put("time-per-message", getConfig().timePerMessage());
		var options = new DeliveryOptions()
				.addHeader("receive-time", Json.encode(currentTime))
				.addHeader("time", Json.encode(currentTime));

		publish(REDIS_NAME + "/information", values, options);
	}

	private LocalMap<String, byte[]> getLocalMap() {
		return localMap(MAP_NAME);
	}

	private static final String MAP_NAME = TelecommandChunker.class.getName();

	private static final String CHUNK_FIELD_KEY = "chunks";
}
