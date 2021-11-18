package de.wuespace.telestion.project.daedalus2.mavlink;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.daedalus.msg_con_cmd;
import com.MAVLink.daedalus.msg_assist_now_upload;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.mavlink.internal.AssistNowTelecommand;
import de.wuespace.telestion.project.daedalus2.mavlink.internal.RawTelecommand;
import de.wuespace.telestion.project.daedalus2.mavlink.internal.Telecommand;
import de.wuespace.telestion.project.daedalus2.mavlink.message.TCSent;
import de.wuespace.telestion.services.connection.rework.RawMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TelecommandSender extends AbstractVerticle {

	public record Configuration(
			@JsonProperty String inAddress,
			@JsonProperty String outAddress,
			@JsonProperty String notifyAddress,
			@JsonProperty Map<String, Integer> compIdAliasMapping,
			@JsonProperty int sysId
	) implements JsonMessage {
		public Configuration() {
			this(null, null, null, null, 0);
		}
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		config = Config.get(config, new Configuration(), config(), Configuration.class);
		// From here on there will be no more changes to the config

		vertx.eventBus().consumer(config.inAddress(), raw -> {
			JsonMessage.on(Telecommand.class, raw, tc -> {
				try {
					// convert String to ASCII byte code
					handleConCmdTC(tc.target(), tc.msg().getBytes(StandardCharsets.ISO_8859_1));
					raw.reply(0);
				} catch (Exception e) {
					raw.fail(500, e.getMessage());
				}
			});
			JsonMessage.on(RawTelecommand.class, raw, rtc -> {
				try {
					// pass through raw data from telecommand
					handleConCmdTC(rtc.target(), rtc.rawData());
					raw.reply(0);
				} catch (Exception e) {
					raw.fail(500, e.getMessage());
				}
			});
			JsonMessage.on(AssistNowTelecommand.class, raw, attc -> {
				try {
					// pass through raw data from telecommand
					handleAssistNowTC(attc.target(), attc.rawData());
					raw.reply(0);
				} catch (Exception e) {
					raw.fail(500, e.getMessage());
				}
			});
		});

		startPromise.complete();
	}

	public TelecommandSender() {
		this(null);
	}

	public TelecommandSender(Configuration config) {
		this.config = config;
	}

	private Configuration config;

	private void handleConCmdTC(String target, byte[] payload) {
		logger.debug("Received ConCmdTC from {} with payload: {}", target, payload);
		var message = new msg_con_cmd(
				payload,
				config.sysId(),
				config.compIdAliasMapping().get(target),
				true
		);

		sendMAVLinkMessage(message, target);
	}

	private void handleAssistNowTC(String target, byte[] payload) {
		logger.debug("Received AssistNowTC from {} with payload: {}", target, payload);
		var message = new msg_assist_now_upload(
				toShort(payload),
				config.sysId(),
				config.compIdAliasMapping().get(target),
				true
		);

		sendMAVLinkMessage(message, target);
	}

	private void sendMAVLinkMessage(MAVLinkMessage message, String target) {
		// pack and encode
		byte[] bytes = message.pack().encodePacket();
		// convert to byte string to allow transfer over event bus
		String byteString = new BigInteger(1, bytes).toString(16);

		// publish package
		logger.debug("Publishing encoded MAVLink packet: {}", byteString);
		vertx.eventBus().publish(config.outAddress(), new RawMessage(bytes).json());

		// notify others
		final long sendTime = System.currentTimeMillis();
		var options = new DeliveryOptions()
				.addHeader("send-time", Json.encode(sendTime));
		vertx.eventBus().publish(config.notifyAddress(), new TCSent(target).json(), options);
	}

	private static final Logger logger = LoggerFactory.getLogger(TelecommandSender.class);

	/**
	 * Converts a byte array to a short array.
	 * @param array the byte array to convert
	 * @return a short array with the contents of the byte array
	 */
	private static short[] toShort(byte[] array) {
		short[] conv = new short[array.length];
		for (int i = 0; i < array.length; i++) {
			conv[i] = array[i];
		}
		return conv;
	}
}
