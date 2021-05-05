package de.wuespace.telestion.project.daedalus2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.extension.mavlink.message.MavlinkMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MessageTransformer extends AbstractVerticle {
	private Configuration config;

	@SuppressWarnings("unused")
	public MessageTransformer() {
		this(null);
	}

	public MessageTransformer(Configuration config) {
		this.config = config;
	}

	@Override
	public void start(Promise<Void> startPromise) {
		var eb = vertx.eventBus();

		this.config = Config.get(this.config, config(), Configuration.class);

		eb.consumer(this.config.inAddress(), raw -> JsonMessage.on(MavlinkMessage.class, raw, message -> {
			var beautifulMessage = new TransformedMessage("fjei");
			eb.publish(this.config.outAddress(), beautifulMessage.json());
		}));


		startPromise.complete();
	}

	public record Configuration(
			@JsonProperty
			String inAddress,
			@JsonProperty
			String outAddress
	) implements JsonMessage {
		@SuppressWarnings("unused")
		private Configuration() {
			this(null, null);
		}
	}
}
