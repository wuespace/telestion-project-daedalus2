package de.wuespace.telestion.project.daedalus2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.mavlink.TelecommandSender;
import de.wuespace.telestion.services.connection.rework.SenderData;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Listens for {@link SenderData} messages and logs them byte-wise.
 *
 * @author Ludwig Richter
 * @author Pablo Klaschka
 * @version 2021-06-25
 */
public class SerialPrinter extends AbstractVerticle {
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		config = Config.get(config, config(), SerialPrinter.Configuration.class);

		vertx.eventBus().consumer(config.inAddress(),
				raw -> JsonMessage.on(SenderData.class, raw, this::log));

		super.start(startPromise);
	}

	/**
	 * Configuration for the {@link SerialPrinter} verticle.
	 *
	 * @param inAddress the address the verticle listens to
	 * @author Ludwig Richter
	 * @author Pablo Klaschka
	 * @version 2021-06-25
	 */
	public record Configuration(
			@JsonProperty
			String inAddress
	) implements JsonMessage {
		@SuppressWarnings("unused")
		public Configuration() {
			this(null);
		}
	}

	public SerialPrinter() {
		this(null);
	}

	public SerialPrinter(Configuration config) {
		this.config = config;
	}

	private void log(SenderData data) {
		logger.debug("Will send: {}", Arrays.toString(data.rawData()));
	}

	private Configuration config;

	private final static Logger logger = LoggerFactory.getLogger(TelecommandSender.class);
}
