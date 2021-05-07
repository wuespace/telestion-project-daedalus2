package de.wuespace.telestion.project.daedalus2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.services.connection.rework.RawMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import java.util.ArrayList;

/**
 * Listens for {@link RawMessage} messages and buffers them to "complete", potential MavLink packages, which, then,
 * get re-published onto the event bus to get parsed, etc.
 * <p>
 * It gets configured using {@link Configuration} with {@link Configuration#inAddress} (which it listens to) and
 * {@link Configuration#outAddress}, which it publishes to. Furthermore, {@link Configuration#longestMessageLength}
 * defines the maximum language a Mavlink message can have (usually 255+25=280 Bytes). The internal buffer has to have
 * at least this many bytes remaining to "look" for new messages within the raw data input.
 *
 * @author Pablo Klaschka
 * @version 2021-05-05
 */
public class SerialSplitter extends AbstractVerticle {
	private Configuration config;

	private ArrayList<Byte> dataQueue;

	@SuppressWarnings("unused")
	public SerialSplitter() {
		this(null);
	}

	public SerialSplitter(Configuration config) {
		this.config = config;
	}

	@Override
	public void start(Promise<Void> startPromise) {
		var eb = vertx.eventBus();

		this.config = Config.get(this.config, config(), Configuration.class);

		this.dataQueue = new ArrayList<>();

		eb.consumer(this.config.inAddress(), raw -> JsonMessage.on(RawMessage.class, raw, message -> {
			// Add the bytes to the buffer/queue
			for (var b : message.data()) {
				dataQueue.add(b);
			}

			// flush all potential messages of the current queue
			flush();
		}));

		startPromise.complete();
	}

	/**
	 * Flushes the messages in {@link SerialSplitter#dataQueue}.
	 * <p>
	 * Every potential message
	 */
	private void flush() {
		while (this.dataQueue.size() >= this.config.longestMessageLength()) {
			if (this.dataQueue.get(0) == (byte) 0xFD) {
				var length = this.dataQueue.get(1) + 11; // TODO: Support messages with signatures (going to n+25)

				if (length <= this.dataQueue.size()) {
					// We found a potential message
					var rawMessageCandidate = new byte[length];

					// Add all message bytes to the candidate ...
					for (int i = 0; i < length; i++) {
						rawMessageCandidate[i] = this.dataQueue.get(i);
					}

					// ... and send it away
					vertx.eventBus().publish(this.config.outAddress(), new RawMessage(rawMessageCandidate).json());
				}
			}

			// this.dataQueue.size(0) cannot belong to any more messages => throw it out
			this.dataQueue.remove(0);
		}
	}

	/**
	 * Configuration for the {@link SerialSplitter} verticle.
	 *
	 * @param inAddress            the address the verticle listens to
	 * @param outAddress           the address the verticle publishes to
	 * @param longestMessageLength the length of the longest possible message in bytes
	 * @author Pablo Klaschka
	 * @version 2021-05-05
	 */
	public record Configuration(
			@JsonProperty
			String inAddress,
			@JsonProperty
			String outAddress,
			@JsonProperty
			int longestMessageLength
	) implements JsonMessage {
		@SuppressWarnings("unused")
		private Configuration() {
			this(null, null, 280);
		}
	}
}
