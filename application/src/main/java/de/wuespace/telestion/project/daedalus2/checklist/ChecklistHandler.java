package de.wuespace.telestion.project.daedalus2.checklist;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.checklist.message.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.shareddata.LocalMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChecklistHandler extends AbstractVerticle {
	public record Configuration(
			@JsonProperty String requestAddress,
			@JsonProperty String notifyAddress,
			@JsonProperty ChecklistPoint[] steps
	) {
		public Configuration() {
			this(null, null, new ChecklistPoint[]{});
		}
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		config = Config.get(config, new Configuration(), config(), Configuration.class);

		// setup handler
		vertx.eventBus().consumer(config.requestAddress(),
				message -> JsonMessage.on(ChecklistRequest.class, message.body(),
						request -> handleRequest(request, message)));

		startPromise.complete();
	}

	public ChecklistHandler(Configuration forcedConfig) {
		config = forcedConfig;
	}

	public ChecklistHandler() {
	}

	public Configuration getConfig() {
		return config;
	}

	private Configuration config;

	private void handleRequest(ChecklistRequest request, Message<Object> message) {
		if (request instanceof RequestState) {
			logger.debug("State request received");
			message.reply(new ResponseState(getState()).json());
		} else if (request instanceof RequestNewState) {
			logger.debug("New state request received");
			final var result = newState(
					((RequestNewState) request).index(),
					((RequestNewState) request).newState());
			message.reply(new ResponseNewState(result).json());
		}
	}

	private int newState(int index, boolean newState) {
		if (index >= config.steps().length) {
			logger.warn("Index to change out of bounds. (Max: " + config.steps().length + "; got " + index + ")");
			return 1;
		}

		setChecked(index, newState);
		notifyConsumers();
		logger.info("New state for checklist point " + index + ": " + newState);
		return 0;
	}

	private void notifyConsumers() {
		logger.debug("Notify consumers");
		vertx.eventBus().publish(config.notifyAddress(), getState().json());
	}

	private ChecklistState getState() {
		final var steps = config.steps();
		final var states = new boolean[steps.length];

		for (int i = 0; i < steps.length; i++) {
			states[i] = isChecked(i);
		}

		return new ChecklistState(steps, states);
	}

	private boolean isChecked(int index) {
		final LocalMap<Integer, Boolean> map = vertx.sharedData().getLocalMap(LOCAL_MAP_NAME);
		return map.getOrDefault(index, false);
	}

	private void setChecked(int index, boolean newState) {
		final LocalMap<Integer, Boolean> map = vertx.sharedData().getLocalMap(LOCAL_MAP_NAME);
		map.put(index, newState);
	}

	private final static Logger logger = LoggerFactory.getLogger(ChecklistHandler.class);
	private static final String LOCAL_MAP_NAME = "checklist-handler-shared";
}
