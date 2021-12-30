package de.wuespace.telestion.project.daedalus2.gps;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.api.verticle.trait.WithSharedData;
import de.wuespace.telestion.project.daedalus2.gps.message.*;
import de.wuespace.telestion.project.daedalus2.mavlink.message.AssistNowTelecommand;
import io.vertx.core.eventbus.Message;
import io.vertx.core.shareddata.LocalMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@SuppressWarnings("unused")
public class AGpsTransmitter extends TelestionVerticle<AGpsTransmitter.Configuration>
		implements WithEventBus, WithSharedData {

	/**
	 * The configuration for the A-GPS transmitter verticle.
	 *
	 * @param notifyAddress   the address the verticle notifies others about a changing state
	 * @param requestAddress  the address to change the verticle's state (used on request-response basis)
	 * @param outAddress      the address the Telecommand messages are sent out
	 * @param bytesPerMessage the maximum number of bytes per Telecommands
	 * @param delay           the delay between Telecommands in milliseconds
	 */
	public record Configuration(
			@JsonProperty String notifyAddress,
			@JsonProperty String requestAddress,
			@JsonProperty String outAddress,
			@JsonProperty int bytesPerMessage,
			@JsonProperty int delay
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, null, null, 30, 2000);
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		register(getConfig().requestAddress(), this::handle, AGpsRequest.class);
	}

	/**
	 * Timer ID to store the timeout id for the next chunk send
	 */
	private Long timerId;

	/**
	 * The list of chunks containing the raw bytes of the A-GPS data.
	 */
	private List<byte[]> chunks;

	/**
	 * The target which receives the A-GPS data.
	 * Only valid in transmit state.
	 */
	private String target;

	/**
	 * The index of the chunk which will be next transmitted once the timer gets called.
	 */
	private int currentChunk = -1;

	/**
	 * Handles incoming requests for state or state changes in the verticle.
	 *
	 * @param request the decoded request from the channel
	 */
	private void handle(AGpsRequest request, Message<Object> message) {
		if (request instanceof RequestState) {
			logger.debug("State request received");
			message.reply(new ResponseState(getState()).json());
		} else if (request instanceof RequestNewData requestNewData) {
			logger.debug("New data request received");
			final var result = store((requestNewData).data());
			message.reply(new ResponseNewData(result).json());
		} else if (request instanceof RequestTransmission) {
			logger.debug("Transmission request received");
			final var result = transmit();
			message.reply(new ResponseTransmission(result).json());
		} else if (request instanceof RequestAbort) {
			logger.debug("Abort request received");
			final var result = abort();
			message.reply(new ResponseAbort(result).json());
		} else if (request instanceof RequestNewTarget requestNewTarget) {
			logger.debug("New target request received");
			final var result = newTarget((requestNewTarget).target());
			message.reply(new ResponseNewTarget(result).json());
		}
	}

	/**
	 * Stores new A-GPS data in the shared storage space.
	 *
	 * @param data the A-GPS data to store
	 * @return the result value
	 */
	private int store(AGpsData data) {
		if (isTransmitting()) {
			logger.warn("Cannot store new A-GPS data, because another thread is currently transmitting.");
			return 1;
		}

		setData(data);
		notifyConsumers();
		logger.info("Received new A-GPS data: {}", data);
		return 0;
	}

	/**
	 * Stores a new target for the A-GPS data in the shared storage space.
	 *
	 * @param target the new target for the A-GPS data
	 * @return the result value
	 */
	private int newTarget(String target) {
		if (isTransmitting()) {
			logger.warn("Cannot store new target, because another thread is currently transmitting.");
			return 1;
		}

		setTarget(target);
		notifyConsumers();
		logger.info("Received new target: {}", target);
		return 0;
	}

	/**
	 * Starts the transmission of the stored A-GPS data in small chunks.
	 *
	 * @return the result value
	 */
	private int transmit() {
		if (isTransmitting()) {
			logger.warn("Cannot transmit current A-GPS data, because another is currently doing so.");
			return 1;
		}

		final var data = getData();
		if (data == null) {
			logger.warn("No data to transmit.");
			return 2;
		}

		final var currentTarget = getTarget();
		if (currentTarget == null) {
			logger.warn("No target to transmit data to");
			return 3;
		}

		String dataName = data.name();
		logger.debug("Transmitting stored A-GPS data {}", dataName);
		// first, lock the session
		setTransmitting(true);
		// convert from base64 to binary blob
		byte[] binaryBlob;
		try {
			binaryBlob = Base64.getDecoder().decode(data.encodedData());
		} catch (Exception e) {
			logger.warn("Cannot decode uploaded data (not base64?)");
			setTransmitting(false);
			return 5;
		}
		// split binary blob
		try {
			chunks = splitBinaryBlob(binaryBlob, getConfig().bytesPerMessage());
		} catch (Exception e) {
			logger.warn("Cannot split binary blob.");
			setTransmitting(false);
			return 4;
		}
		this.target = currentTarget;
		currentChunk = 0;
		int numberOfChunks = chunks.size();
		logger.info("Created {} chunks.", numberOfChunks);
		logger.debug("Chunks: {}", chunks);
		notifyConsumers();
		// trigger timer chain
		sendChunk(null);
		// successfully initiated
		return 0;
	}

	/**
	 * Aborts the current transmission.
	 *
	 * @return the result value
	 */
	private int abort() {
		if (!isTransmitting()) {
			logger.info("Not transmitting. Nothing to abort.");
			return 1;
		}

		// try to cancel the timer
		vertx.cancelTimer(timerId);
		// clean up
		chunks = null;
		target = null;
		currentChunk = -1;
		setTransmitting(false);
		notifyConsumers();
		logger.info("Aborted current transmission.");
		return 0;
	}

	/**
	 * Sends the next chunk in the chunk list.
	 *
	 * @param id the timer id
	 */
	private void sendChunk(Long id) {
		logger.debug("Sending chunk {}", currentChunk);
		publish(getConfig().outAddress(), new AssistNowTelecommand(target, chunks.get(currentChunk)));
		notifyConsumers();

		currentChunk++;
		if (currentChunk < chunks.size()) {
			// indirect self call
			timerId = vertx.setTimer(getConfig().delay(), this::sendChunk);
		} else {
			// finished -> clean up
			timerId = null;
			chunks = null;
			target = null;
			currentChunk = -1;
			setTransmitting(false);
			notifyConsumers();
			logger.info("All chunks sent.");
		}
	}

	/**
	 * Notifies the consumers via the `notifyAddress` about state changes in the verticle.
	 */
	private void notifyConsumers() {
		logger.debug("Notify consumers");
		publish(getConfig().notifyAddress(), getState());
	}

	/**
	 * Gathers and returns the current state information.
	 *
	 * @return the current state
	 */
	private AGpsState getState() {
		logger.debug("Gathering state information");

		final var data = getData();
		final var currentTarget = getTarget();
		final var isTransmitting = isTransmitting();
		final String state = isTransmitting ? "transmitting" : "idle";
		final String dataName = data == null ? null : data.name(); // where is the ?. operator...
		final double progress = isTransmitting ? currentChunk * 1.0 / chunks.size() : -1.0;

		return new AGpsState(state, dataName, currentTarget, progress);
	}

	/**
	 * Returns <code>true</code> when the verticle is currently transmitting the stored A-GPS data.
	 *
	 * @return <code>true</code> when the verticle is transmitting
	 */
	private boolean isTransmitting() {
		var map = this.<Boolean>getLocalMap();
		return map.getOrDefault(MAP_KEY_IS_TRANSMITTING, false);
	}

	/**
	 * Sets the current transmission state. Usually used on start or end of a complete transmission.
	 *
	 * @param newState the new transmission state
	 */
	private void setTransmitting(boolean newState) {
		var map = this.<Boolean>getLocalMap();
		map.put(MAP_KEY_IS_TRANSMITTING, newState);
	}

	/**
	 * Returns the target where the Telecommand Sender should send the raw binary data.
	 *
	 * @return the target for current chunk
	 */
	private String getTarget() {
		var map = this.<String>getLocalMap();
		return map.getOrDefault(MAP_KEY_TARGET, null);
	}

	/**
	 * Sets a new target for the Telecommand Sender.
	 *
	 * @param target the new target
	 */
	private void setTarget(String target) {
		var map = this.<String>getLocalMap();
		map.put(MAP_KEY_TARGET, target);
	}

	/**
	 * Returns the currently stored A-GPS data.
	 *
	 * @return the stored A-GPS data
	 */
	private AGpsData getData() {
		var map = this.<String>getLocalMap();

		var name = map.getOrDefault(MAP_KEY_DATA_NAME, null);
		var encoded = map.getOrDefault(MAP_KEY_DATA_ENCODED, null);

		return encoded == null || name == null ? null : new AGpsData(name, encoded);
	}

	/**
	 * Stores the new A-GPS data in shared storage space.
	 *
	 * @param data the new A-GPS data to store
	 */
	private void setData(AGpsData data) {
		var map = this.<String>getLocalMap();

		map.put(MAP_KEY_DATA_NAME, data.name());
		map.put(MAP_KEY_DATA_ENCODED, data.encodedData());
	}

	private <V> LocalMap<String, V> getLocalMap() {
		return localMap(LOCAL_MAP_NAME);
	}

	/**
	 * Splits a given binary blob into chunks of the same size.
	 * The last smaller part is then additionally attached to the list.
	 *
	 * @param binaryBlob      the binary blob to split up
	 * @param bytesPerMessage the number of bytes per chunk or message
	 * @return the list containing chunks with the same size and the last smaller part
	 */
	private static List<byte[]> splitBinaryBlob(byte[] binaryBlob, int bytesPerMessage) {
		List<byte[]> list = new ArrayList<>();
		// determine number of full chunks
		final var fullChunks = binaryBlob.length / bytesPerMessage;
		// scrape out data from chunk boundaries
		for (var i = 0; i < fullChunks; i++) {
			list.add(Arrays.copyOfRange(binaryBlob, i * bytesPerMessage, (i + 1) * bytesPerMessage));
		}
		// don't forget the binary blob end
		if (fullChunks * bytesPerMessage < binaryBlob.length) {
			list.add(Arrays.copyOfRange(binaryBlob, fullChunks * bytesPerMessage, binaryBlob.length));
		}

		return list;
	}

	private static final String LOCAL_MAP_NAME = "gps-fix-transmitter-shared";
	private static final String MAP_KEY_IS_TRANSMITTING = "is-transmitting";
	private static final String MAP_KEY_TARGET = "target";
	private static final String MAP_KEY_DATA_NAME = "data-name";
	private static final String MAP_KEY_DATA_ENCODED = "data-encoded";
}
