package de.wuespace.telestion.project.daedalus2.mavlink;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Parser;
import com.MAVLink.daedalus.msg_log;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.LogMessage;
import de.wuespace.telestion.services.connection.rework.ConnectionData;
import de.wuespace.telestion.services.monitoring.MessageLogger;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class MavLinkTMParser extends TelestionVerticle<MavLinkTMParser.Configuration> implements WithEventBus {

	public static final String DEFAULT_IN_ADDRESS = "raw-mavlink";

	public record Configuration(
			@JsonProperty Map<String, String> sysIdMapping,
			@JsonProperty String inAddress,
			@JsonProperty String outAddressLog
	) implements TelestionConfiguration {
		public Configuration() {
			this(new HashMap<>(), DEFAULT_IN_ADDRESS, "mavlink-tm-log");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		var vertx = Vertx.vertx();

		Logger logger = LoggerFactory.getLogger("MavlinkTM Test");

		vertx.eventBus().consumer("unknown/SEED_SYSTEM_T", event -> {
			logger.info("=== RECEIVED: ===");
			logger.info("Headers:");
			event.headers().forEach(header ->
					logger.info(
							MessageFormat.format(
									"{0}: {1}",
									header.getKey(),
									header.getValue()
							)
					)
			);
			if (event.body() instanceof JsonObject obj) {
				logger.info("Body:");
				logger.info(obj.encodePrettily());
			}
		});

		// TODO: Improve usage with ListDeployer (when released in core)
		vertx.deployVerticle(MessageLogger.class, new DeploymentOptions());
		var configuration = new Configuration(Map.of("0", "abc"), DEFAULT_IN_ADDRESS, "mavlink-tm-log");
		vertx.deployVerticle(MavLinkTMParser.class, new DeploymentOptions().setConfig(configuration.json()));

		Thread.sleep(2000);

		logger.info("Publishing data");
		vertx.eventBus().publish(DEFAULT_IN_ADDRESS, new ConnectionData(new byte[]{
				(byte) 0xAB,
				(byte) 0xFD,
				(byte) 0x01,
				(byte) 0x00,
				(byte) 0x00,
				(byte) 0x00,
		}, null).json());

		vertx.eventBus().publish(DEFAULT_IN_ADDRESS, new ConnectionData(new byte[]{
				(byte) 0xFF,
				(byte) 0x00,
				(byte) 0x13,
				(byte) 0x27,
				(byte) 0x00,
				(byte) 0x00,
				(byte) 0x36,
				(byte) 0x23
		}, null).json());
	}

	public MavLinkTMParser() {
		this.parser = new Parser(true);
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		register(getConfig().inAddress(), this::handle, ConnectionData.class);
	}

	private final Parser parser;

	private void handle(ConnectionData data) {
		for (var b : data.rawData()) {
			var packet = parser.mavlink_parse_char(b);

			if (packet != null) {
				try {
					var msg = packet.unpack();

					final long receiveTime = System.currentTimeMillis();

					ObjectMapper mapper = new ObjectMapper();
					var json = mapper.writeValueAsString(msg);

					var options = new DeliveryOptions()
							.addHeader("receive-time", Json.encode(receiveTime));

					var source = getConfig().sysIdMapping().getOrDefault(msg.sysid + "", "unknown");
					var type = msg.name().substring(15);

					attachTimeHeader(msg, receiveTime, options);

					publish(source + "/" + type, new JsonObject(json), options);

					// extra for msg_log MavLink message type
					if (msg instanceof msg_log) {
						var message = ((msg_log) msg).getLog_Msg().trim();
						publish(getConfig().outAddressLog(), new LogMessage(source, message));
					}
				} catch (Exception e) {
					logger.error("Cannot unpack MavLink packet {}", packet, e.getCause());
				}
			}
		}

		var parseTime = System.currentTimeMillis();
		var options = new DeliveryOptions()
				.addHeader("receive-time", Json.encode(parseTime))
				.addHeader("time", Json.encode(parseTime));

		var parserInformation = new JsonObject()
				.put("received", parser.stats.receivedPacketCount)
				.put("lost", parser.stats.lostPacketCount)
				.put("crc-error", parser.stats.crcErrorCount);

		publish("mavlink/parser", parserInformation, options);

		logger.info("Received: {}, Lost: {}, CRC-Error: {}",
				parser.stats.receivedPacketCount, parser.stats.lostPacketCount, parser.stats.crcErrorCount);
	}

	private void attachTimeHeader(MAVLinkMessage msg, long receiveTime, DeliveryOptions options) {
		try {
			long time = (long) msg.getClass().getField("timeLocal").get(msg);
			options.addHeader("time", Json.encode(time));
		} catch (Exception e) {
			options.addHeader("time", Json.encode(receiveTime));
		}
	}
}
