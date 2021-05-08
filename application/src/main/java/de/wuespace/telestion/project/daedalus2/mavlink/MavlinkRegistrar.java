package de.wuespace.telestion.project.daedalus2.mavlink;

import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.extension.mavlink.MessageIndex;
import de.wuespace.telestion.extension.mavlink.annotation.MavInfo;
import de.wuespace.telestion.extension.mavlink.message.MavlinkMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MavlinkRegistrar extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) {
		var config = Config.get(this.forcedConfig, config(), Configuration.class);

		try {
			for (var name : config.classNames()) {
				var clazz = Class.forName(name);

				if (!clazz.isAssignableFrom(MavlinkMessage.class)) {
					logger.warn("MAVLink message " + name + " could not be loaded");
					return;
				}

				@SuppressWarnings("unchecked")
				var mavClass = (Class<MavlinkMessage>) clazz;

				if (mavClass.isAnnotationPresent(MavInfo.class)) {
					MessageIndex.put(mavClass.getAnnotation(MavInfo.class).id(), mavClass);
				} else {
					logger.warn("MAVLink message " + name + " does not have the required MavInfo annotation!");
				}
			}
		} catch(ClassNotFoundException e) {
			logger.error("Configured MAVLink message could not be found!", e);
		}

		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) {
		stopPromise.complete();
	}

	public record Configuration(String[] classNames) implements JsonMessage {
		private Configuration() {
			this(null);
		}
	}

	public MavlinkRegistrar(Configuration forcedConfig) {
		this.forcedConfig = forcedConfig;
	}

	private Configuration forcedConfig;
	private static final Logger logger = LoggerFactory.getLogger(MavlinkRegistrar.class);
}
