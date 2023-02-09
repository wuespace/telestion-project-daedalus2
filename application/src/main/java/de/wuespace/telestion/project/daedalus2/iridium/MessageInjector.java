package de.wuespace.telestion.project.daedalus2.iridium;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.daedalus2.iridium.message.*;

import java.time.Instant;
import java.util.List;

@SuppressWarnings("unused")
public class MessageInjector extends TelestionVerticle<MessageInjector.Configuration>
		implements WithEventBus {

	public record Configuration(
			@JsonProperty String outAddress,
			@JsonProperty String seedAImei,
			@JsonProperty String seedBImei,
			@JsonProperty int delay
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, null, null, 2000);
		}
	}

	@Override
	public void onStart() throws Exception {
		vertx.setPeriodic(getConfig().delay(), id -> {
			var sentTime = Instant.now();
			sendSeedAMessage(sentTime);
			sendSeedBMessage(sentTime);
		});
	}

	private void sendSeedAMessage(Instant sentTime) {
		var header = new IEHeader(0, getConfig().seedAImei(), 0, 1, 1, sentTime.getEpochSecond());
		var location = new IELocation(-1, -1, 67.887, 21.0851, 10);
		var payload = new IEPayload(new ValidDaedalus2Payload(true, 32, false, 49.9, 9.9, 0));

		var iridiumMessage = new IridiumMessage(List.of(header, location, payload));

		publish(getConfig().outAddress(), iridiumMessage);
	}

	private void sendSeedBMessage(Instant sentTime) {
		var header = new IEHeader(0, getConfig().seedBImei(), 0, 1, 1, sentTime.getEpochSecond());
		var location = new IELocation(-1, -1, 67.921, 21.5422, 10);
		var payload = new IEPayload(new ValidDaedalus2Payload(true, 32, false, 49.9, 9.9, 0));

		var iridiumMessage = new IridiumMessage(List.of(header, location, payload));

		publish(getConfig().outAddress(), iridiumMessage);
	}
}
