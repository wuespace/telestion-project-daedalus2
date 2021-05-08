package de.wuespace.telestion.project.daedalus2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.mavlink.SeedSystemT;
import de.wuespace.telestion.project.daedalus2.messages.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

/**
 * Listens for {@link SeedSystemT} messages and converts them to further "beautified" {@link SystemT} messages.<br />
 * <p>
 * These new messages, among other enhancements, add hierarchy to the data (making it easier to query specific
 * sets of data) and transforms int-encoded bit sequences to objects.<br />
 * <p>
 * It gets configured using {@link Configuration} with {@link Configuration#inAddress} (which it listens to) and
 * {@link Configuration#outAddress}, which it publishes to.
 *
 * @author Pablo Klaschka
 * @version 2021-05-08
 */
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

		eb.consumer(this.config.inAddress(), raw -> JsonMessage.on(SeedSystemT.class, raw, rawSystemT -> {
			var beautifulMessage = new SystemT(rawSystemT);
			eb.publish(this.config.outAddress(), beautifulMessage.json());
		}));

		startPromise.complete();
	}

	/**
	 * Configuration for the {@link MessageTransformer} verticle.
	 *
	 * @param inAddress  the address the verticle listens to
	 * @param outAddress the address the verticle publishes to
	 * @author Pablo Klaschka
	 * @version 2021-05-05
	 */
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
