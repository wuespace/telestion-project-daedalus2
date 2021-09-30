package de.wuespace.telestion.project.daedalus2.gps;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.project.daedalus2.mavlink.internal.RawTelecommand;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.shareddata.LocalMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class AGpsTransmitter extends AbstractVerticle {
	/**
	 * The configuration for the A-GPS transmitter verticle.
	 *
	 * @param notifyAddress   the address the verticle notifies others about a changing state
	 * @param requestAddress  the address to change the verticle's state (used on request-response basis)
	 * @param outAddress      the address the Telecommand messages are sent out
	 * @param bytesPerMessage the maximum number of bytes per Telecommands
	 * @param delay           the delay between Telecommands in milliseconds
	 */
	public static record Configuration(
			@JsonProperty String notifyAddress,
			@JsonProperty String requestAddress,
			@JsonProperty String outAddress,
			@JsonProperty int bytesPerMessage,
			@JsonProperty int delay
	) {
		public Configuration() {
			this(null, null, null, 30, 2000);
		}
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		// TODO: define merged message types for requests
		// TODO: register consumers on all addresses

		startPromise.complete();
	}

	public AGpsTransmitter(String notifyAddress, String requestAddress, String outAddress, int bytesPerMessage, int delay) {
		this.forcedConfig = new Configuration(notifyAddress, requestAddress, outAddress, bytesPerMessage, delay);
	}

	public AGpsTransmitter() {
		this.forcedConfig = null;
	}

	/**
	 * Returns the current configuration for this verticle.
	 * If the verticle is not started yet, the configuration is {@code null}.
	 *
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

	private Long timerId;

	private List<byte[]> chunks;

	private int currentChunk = -1;

	private final Logger logger = LoggerFactory.getLogger(AGpsTransmitter.class);

	private boolean store(AGpsData data) {
		if (isSending()) {
			logger.warn("Cannot store new A-GPS data, because another thread is currently transmitting.");
			return false;
		}

		setData(data);
		logger.info("Received new A-GPS data: " + data);
		return true;
	}

	private boolean send() {
		if (isSending()) {
			logger.warn("Cannot transmit current A-GPS data, because another is currently doing so.");
			return false;
		}

		var data = getData();
		if (data == null) {
			logger.warn("No data to transmit.");
			return false;
		}

		logger.debug("Transmitting stored A-GPS data " + data.name());
		// first, lock the session
		setSending(true);
		// convert from base64 to binary blob
		byte[] binaryBlob = Base64.getDecoder().decode(data.encodedData());
		// split binary blob
		chunks = splitBinaryBlob(binaryBlob, config.bytesPerMessage());
		currentChunk = 0;
		logger.info("Created " + chunks.size() + " chunks.");
		logger.debug("Chunks: " + chunks);
		// trigger timer chain
		sendChunk(null);
		// successfully initiated
		return true;
	}

	private boolean abort() {
		if (!isSending()) {
			logger.info("Not transmitting. Nothing to abort.");
			return false;
		}

		// try to cancel the timer
		vertx.cancelTimer(timerId);
		// clean up
		chunks = null;
		currentChunk = -1;
		setSending(false);
		logger.info("Aborted current transmission.");
		return true;
	}

	private void sendChunk(Long id) {
		logger.debug("Sending chunk " + currentChunk);
		vertx.eventBus().publish(
				config.outAddress(),
				new RawTelecommand(getTarget(), chunks.get(currentChunk))
		);

		currentChunk++;
		if (currentChunk < chunks.size()) {
			// indirect self call
			timerId = vertx.setTimer(config.delay(), this::sendChunk);
		} else {
			// finished -> clean up
			timerId = null;
			chunks = null;
			currentChunk = -1;
			setSending(false);
			logger.info("All chunks sent.");
		}
	}

	private boolean isSending() {
		final LocalMap<String, Boolean> map = vertx.sharedData().getLocalMap(LOCAL_MAP_NAME);
		return map.get(MAP_KEY_IS_SENDING);
	}

	private void setSending(boolean newState) {
		final LocalMap<String, Boolean> map = vertx.sharedData().getLocalMap(LOCAL_MAP_NAME);
		map.put(MAP_KEY_IS_SENDING, newState);
	}

	private String getTarget() {
		final LocalMap<String, String> map = vertx.sharedData().getLocalMap(LOCAL_MAP_NAME);
		return map.get(MAP_KEY_TARGET);
	}

	private void setTarget(String target) {
		final LocalMap<String, String> map = vertx.sharedData().getLocalMap(LOCAL_MAP_NAME);
		map.put(MAP_KEY_TARGET, target);
	}

	private AGpsData getData() {
		final LocalMap<String, AGpsData> map = vertx.sharedData().getLocalMap(LOCAL_MAP_NAME);
		return map.get(MAP_KEY_DATA);
	}

	private void setData(AGpsData data) {
		final LocalMap<String, AGpsData> map = vertx.sharedData().getLocalMap(LOCAL_MAP_NAME);
		map.put(MAP_KEY_DATA, data);
	}

	private static List<byte[]> splitBinaryBlob(byte[] binaryBlob, int bytesPerMessage) {
		List<byte[]> list = new ArrayList<>();
		// determine number of full chunks
		final var fullChunks = binaryBlob.length / bytesPerMessage;
		// scrape out data from chunk boundaries
		for (var i = 0; i < fullChunks; i++) {
			list.add(Arrays.copyOfRange(binaryBlob, i * fullChunks, (i + 1) * fullChunks));
		}
		// don't forget the binary blob end
		if (fullChunks * bytesPerMessage < binaryBlob.length) {
			list.add(Arrays.copyOfRange(binaryBlob, fullChunks * bytesPerMessage, binaryBlob.length));
		}

		return list;
	}

	private static final String LOCAL_MAP_NAME = "gps-fix-transmitter-shared";
	private static final String MAP_KEY_IS_SENDING = "is-sending";
	private static final String MAP_KEY_TARGET = "target";
	private static final String MAP_KEY_DATA = "data";
}
