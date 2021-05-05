package de.wuespace.telestion.project.daedalus2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.extension.mavlink.message.MavlinkMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MessageTransformer extends AbstractVerticle {
	private Configuration configuration;

	@SuppressWarnings("unused")
	public MessageTransformer() {
		this(null);
	}

	public MessageTransformer(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public void start(Promise<Void> startPromise) {
		var eb = vertx.eventBus();

		this.configuration = Config.get(this.configuration, config(), Configuration.class);

		eb.consumer(this.configuration.inAddress(), raw -> JsonMessage.on(MavlinkMessage.class, raw, message -> {
			var beautifulMessage = new TransformedMessage("fjei");
			eb.publish(this.configuration.outAddress(), beautifulMessage.json());
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
