package de.wuespace.telestion.project.daedalus2.mavlink;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Parser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.services.connection.rework.ConnectionData;
import de.wuespace.telestion.services.monitoring.MessageLogger;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import java.text.MessageFormat;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class MavlinkTM extends AbstractVerticle {
	public static final String DEFAULT_IN_ADDRESS = "raw-mavlink";
	private Configuration config;

	public MavlinkTM(Configuration config) { this.config = config; }
	public MavlinkTM() { this(null); }

	public static void main(String[] args) throws InterruptedException {
		var vertx = Vertx.vertx();

		Logger logger = Logger.getLogger("MavlinkTM Test");
		logger.setLevel(Level.ALL);

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

		vertx.deployVerticle(new MessageLogger());
		vertx.deployVerticle(new MavlinkTM(new MavlinkTM.Configuration(Map.of("0", "abc"), DEFAULT_IN_ADDRESS)));

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

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		this.config = Config.get(this.config, new Configuration(), config(), Configuration.class);

		var eb = getVertx().eventBus();
		var parser = new Parser(true);

		eb.consumer(config.inAddress(), raw -> JsonMessage.on(ConnectionData.class, raw, message -> {
			for (var b : message.rawData()) {
				var packet = parser.mavlink_parse_char(b);

				if (packet != null) {
					try {
						var msg = packet.unpack();

						final long receiveTime = System.currentTimeMillis();

						ObjectMapper mapper = new ObjectMapper();
						var json = mapper.writeValueAsString(msg);

						var options = new DeliveryOptions()
								.addHeader("receive-time", Json.encode(receiveTime));

						var source = config.sysIdMapping().getOrDefault(msg.sysid + "", "unknown");
						var type = msg.name().substring(15);

						attachTimeHeader(msg, receiveTime, options);

						eb.publish(source + "/" + type, new JsonObject(json), options);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}));


		startPromise.complete();
	}

	private void attachTimeHeader(MAVLinkMessage msg, long receiveTime, DeliveryOptions options) {
		try {
			long time = (long) msg.getClass().getField("timeLocal").get(msg);
			options.addHeader("time", Json.encode(time));
		} catch (Exception e) {
			options.addHeader("time", Json.encode(receiveTime));
		}
	}

	public record Configuration(@JsonProperty Map<String, String> sysIdMapping, @JsonProperty String inAddress) implements JsonMessage {
		public Configuration() {
			this(null, DEFAULT_IN_ADDRESS);
		}
	}
}
