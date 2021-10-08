package de.wuespace.telestion.project.daedalus2.mavlink;

import com.MAVLink.Parser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.services.connection.rework.ConnectionData;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import java.text.MessageFormat;
import java.util.Map;

public class MavlinkTM extends AbstractVerticle {
	private Configuration config;

	public static void main(String[] args) throws InterruptedException {
		var vertx = Vertx.vertx();

		vertx.eventBus().consumer("system-t", event -> {
			System.out.println("=== RECEIVED: ===");
			System.out.println("Headers:");
			event.headers().forEach(header ->
					System.out.println(
							MessageFormat.format(
									"{0}: {1}",
									header.getKey(),
									header.getValue()
							)
					)
			);
			if (event.body() instanceof JsonObject obj) {
				System.out.println("Body:");
				System.out.println(obj.encodePrettily());
			}
		});

		vertx.deployVerticle(new MavlinkTM());

		Thread.sleep(2000);

		vertx.eventBus().publish("raw-mavlink", new ConnectionData(new byte[]{
				(byte) 0xAB,
				(byte) 0xFD,
				(byte) 0x01,
				(byte) 0x00,
				(byte) 0x00,
				(byte) 0x00,
		}, null).json());

		vertx.eventBus().publish("raw-mavlink", new ConnectionData(new byte[]{
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

		eb.consumer("raw-mavlink", raw -> JsonMessage.on(ConnectionData.class, raw, message -> {
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

						try {
							long time = (long) msg.getClass().getField("timeLocal").get(msg);
							options.addHeader("time", Json.encode(time));
						} catch (Exception e) {
							options.addHeader("time", Json.encode(receiveTime));
						}

						eb.publish(source + "/" + type, new JsonObject(json), options);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}));


		startPromise.complete();
	}

	public record Configuration(@JsonProperty Map<String, String> sysIdMapping) implements JsonMessage {
		public Configuration() {
			this(null);
		}
	}
}
