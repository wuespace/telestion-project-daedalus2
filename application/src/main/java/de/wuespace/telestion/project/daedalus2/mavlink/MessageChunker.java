package de.wuespace.telestion.project.daedalus2.mavlink;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.mavlink.internal.SplitData;
import de.wuespace.telestion.services.connection.rework.RawMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/**
 * Splits outgoing telecommands into smaller chunks and send them in a regular time interval.
 * It gives regular updates on current chunks, time interval, chunk size, etc. on Redis readable channels.
 *
 * @author Ludwig Richter
 */
public class MessageChunker extends AbstractVerticle {
	public static final String MAP_NAME = MessageChunker.class.getName();

	public static final String CHUNK_FIELD_KEY = "chunks";

	public static final String REDIS_NAME = "message-chunker";

	public record Configuration(
			@JsonProperty String inAddress,
			@JsonProperty String outAddress,
			@JsonProperty int chunkSize,
			@JsonProperty int timePerMessage
	) implements JsonMessage {
		public Configuration() {
			this(null, null, 64, 10);
		}
	}

	@Override
	public void start() {
		config = Config.get(config, new Configuration(), config(), Configuration.class);
		// setup interval to publish chunks in regular intervals
		vertx.setPeriodic(Duration.ofMillis(config.timePerMessage).toMillis(), this::publishChunk);
		// setup consumer to add new data to chunk list
		vertx.eventBus().consumer(config.inAddress, raw -> JsonMessage.on(RawMessage.class, raw, this::addMessage));
		// fill Redis the first time
		notify(0);
	}

	private Configuration config;

	private void publishChunk(long intervalId) {
		var data = getData();
		// stop if data is empty
		if (data.length == 0) return;
		var splitData = splitData(data, config.chunkSize);
		setData(splitData.remainder());
		vertx.eventBus().publish(config.outAddress, new RawMessage(splitData.chunk()).json());
	}

	private void addMessage(RawMessage message) {
		var newData = concatData(getData(), message.data());
		setData(newData);
	}


	private byte[] getData() {
		Map<String, byte[]> map = vertx.sharedData().getLocalMap(MAP_NAME);
		return map.getOrDefault(CHUNK_FIELD_KEY, new byte[]{});
	}

	private void setData(byte[] data) {
		Map<String, byte[]> map = vertx.sharedData().getLocalMap(MAP_NAME);
		map.put(CHUNK_FIELD_KEY, data);
		notify(data.length);
	}

	private void notify(int newSize) {
		final long currentTime = System.currentTimeMillis();
		logger.debug("New chunk list size is {}", newSize);

		var values = new JsonObject()
				.put("list-size", newSize)
				.put("chunk-size", config.chunkSize)
				.put("time-per-message", config.timePerMessage);
		var options = new DeliveryOptions()
				.addHeader("receive-time", Json.encode(currentTime))
				.addHeader("time", Json.encode(currentTime));

		vertx.eventBus().publish(REDIS_NAME + "/information", values, options);
	}

	private static List<byte[]> chunkData(byte[] data, int chunkSize) {
		var list = new ArrayList<byte[]>();

		// split up data
		int i = 0;
		for (; (i+1) * chunkSize < data.length; i++) {
			list.add(Arrays.copyOfRange(data, i * chunkSize, (i+1) * chunkSize));
		}
		// don't forget the rest
		list.add(Arrays.copyOfRange(data, i * chunkSize, data.length));

		return list;
	}

	/**
	 * Concat two byte arrays to one.
	 * @param a the first byte array
	 * @param b the second byte array
	 * @return a new byte array containing the data from the first and second one in this sequence
	 */
	private static byte[] concatData(byte[] a, byte[] b) {
		var newData = new byte[a.length + b.length];

		System.arraycopy(a, 0, newData, 0, a.length);
		System.arraycopy(b, 0, newData, a.length, b.length);

		return newData;
	}

	/**
	 * Splits apart the given data into a chunk and a remainder.
	 *
	 * <pre>[data] -> [chunk:size] + [remainder]</pre>
	 *
	 * @param data the data to split up
	 * @param chunkSize the size of the chunk
	 * @return the chunk and the remainder
	 */
	private static SplitData splitData(byte[] data, int chunkSize) {
		if (data.length <= chunkSize) {
			// data fits in one chunk -> simply return data and an empty array as remainder
			return new SplitData(data, new byte[]{});
		}

		var chunk = new byte[chunkSize];
		var remainder = new byte[data.length - chunkSize];

		System.arraycopy(data, 0, chunk, 0, chunk.length);
		System.arraycopy(data, chunkSize, remainder, 0, remainder.length);

		return new SplitData(chunk, remainder);
	}

	private static final Logger logger = LoggerFactory.getLogger(MessageChunker.class);
}
