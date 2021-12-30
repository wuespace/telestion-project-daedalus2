package de.wuespace.telestion.project.daedalus2.mavlink;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.daedalus.msg_con_cmd;
import com.MAVLink.daedalus.msg_assist_now_upload;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.daedalus2.mavlink.message.AssistNowTelecommand;
import de.wuespace.telestion.project.daedalus2.mavlink.message.RawTelecommand;
import de.wuespace.telestion.project.daedalus2.mavlink.message.Telecommand;
import de.wuespace.telestion.project.daedalus2.mavlink.message.TCSent;
import de.wuespace.telestion.services.connection.rework.RawMessage;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static de.wuespace.telestion.project.daedalus2.util.ByteArrayUtils.toUnsignedShort;

@SuppressWarnings("unused")
public class TelecommandSender extends TelestionVerticle<TelecommandSender.Configuration> implements WithEventBus {

	public record Configuration(
			@JsonProperty String inAddress,
			@JsonProperty String outAddress,
			@JsonProperty String notifyAddress,
			@JsonProperty Map<String, Integer> compIdAliasMapping,
			@JsonProperty int sysId
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, null, null, null, 0);
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		register(getConfig().inAddress(), (tc, message) -> {
			try {
				// convert String to ASCII byte code
				handleConCmdTC(tc.target(), tc.msg().getBytes(StandardCharsets.ISO_8859_1));
				message.reply(0);
			} catch (Exception e) {
				message.fail(500, e.getMessage());
			}
		}, Telecommand.class);

		register(getConfig().inAddress(), (rtc, message) -> {
			try {
				// pass through raw data from telecommand
				handleConCmdTC(rtc.target(), rtc.rawData());
				message.reply(0);
			} catch (Exception e) {
				message.fail(500, e.getMessage());
			}
		}, RawTelecommand.class);

		register(getConfig().inAddress(), (atc, message) -> {
			try {
				// pass through raw data from telecommand
				handleAssistNowTC(atc.target(), atc.rawData());
				message.reply(0);
			} catch (Exception e) {
				message.fail(500, e.getMessage());
			}
		}, AssistNowTelecommand.class);
	}

	private void handleConCmdTC(String target, byte[] payload) {
		logger.debug("Received ConCmdTC from {} with payload: {}", target, payload);
		var message = new msg_con_cmd(
				payload,
				getConfig().sysId(),
				getConfig().compIdAliasMapping().get(target),
				true
		);

		sendMAVLinkMessage(message, target);
	}

	private void handleAssistNowTC(String target, byte[] payload) {
		var message = new msg_assist_now_upload(
				toUnsignedShort(payload),
				getConfig().sysId(),
				getConfig().compIdAliasMapping().get(target),
				true
		);

		sendMAVLinkMessage(message, target);
	}

	private void sendMAVLinkMessage(MAVLinkMessage message, String target) {
		logger.debug("Send new Mavlink message to {}: {}", target, message);
		// pack, encode and publish package
		byte[] bytes = message.pack().encodePacket();
		publish(getConfig().outAddress(), new RawMessage(bytes));

		// notify others
		final long sendTime = System.currentTimeMillis();
		var options = new DeliveryOptions()
				.addHeader("send-time", Json.encode(sendTime));
		publish(getConfig().notifyAddress(), new TCSent(target), options);
	}
}
