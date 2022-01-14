package de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.api.verticle.trait.WithSharedData;
import de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.*;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class TelecommandConsole extends TelestionVerticle<TelecommandConsole.Configuration>
		implements WithEventBus, WithSharedData {

	/**
	 * @param inAddress        the address on which new telemetry is coming in
	 * @param notifyAddress    the address on which the verticle publishes the new state if something changes
	 * @param requestAddress   the address on which someone can request the current state (will be replied)
	 * @param maxNumberOfLines the maximum number of lines that are stored for each source
	 */
	public record Configuration(
			@JsonProperty String inAddress,
			@JsonProperty String notifyAddress,
			@JsonProperty String requestAddress,
			@JsonProperty int maxNumberOfLines
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, "tc-console-notify", "tc-console-request", 300);
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		register(getConfig().inAddress(), this::appendLogMessage, LogMessage.class);
		register(getDefaultConfig().requestAddress(), this::handleRequest, ConsoleRequest.class);
		logger.debug("Wait for new log messages...");
	}

	public void handleRequest(ConsoleRequest request, Message<Object> message) {
		if (request instanceof RequestState) {
			var source = ((RequestState) request).source();
			logger.info("State request for source {} received.", source);

			var messages = getLogMessages(source);

			message.reply(new ResponseState(messages).json());
			logger.info("Sent state request for {}.", source);
		} else if (request instanceof RequestClear) {
			var source = ((RequestClear) request).source();
			logger.info("Clear request for source {} received.", source);

			clearLogMessages(source);

			message.reply(new ResponseClear(source).json());
			logger.info("Cleared log messages for {}.", source);
		} else if (request instanceof RequestClearAll) {
			logger.info("Clear all request received.");

			clearAllLogMessages();

			message.reply(new ResponseClearAll().json());
			logger.info("Cleared all log messages.");
		} else {
			logger.warn("Unsupported request received. (Expected: RequestState/RequestClear, Got: {}). " +
							"Please register it in request handler to properly handle this request.",
					request.getClass().getSimpleName());
		}
	}

	private final String mapKey = getClass().getName();

	private void pushUpdate(String source) {
		var log = getLogMessages(source);
		publish(getConfig().notifyAddress(), log);
		logger.debug("Source {} updated.", source);
	}

	private void appendLogMessage(LogMessage log) {
		logger.info("Append new log message: {}", log);
		var messages = getLogMessages(log.source());
		var list = new LinkedList<>(List.of(messages.messages()));

		list.addLast(log);
		removeOldest(list, getConfig().maxNumberOfLines());

		var newMessages = new LogMessages(log.source(), list.toArray(LogMessage[]::new));
		setLogMessages(newMessages);
		pushUpdate(log.source());
	}

	private void clearLogMessages(String source) {
		var map = defaultLocalMap();
		map.remove(source);
		pushUpdate(source);
	}

	private void clearAllLogMessages() {
		var map = defaultLocalMap();
		map.keySet().forEach(this::clearLogMessages);
	}

	private LogMessages getLogMessages(String source) {
		try {
			var map = defaultLocalMap();
			var rawMessages = map.get(source);
			return Objects.isNull(rawMessages)
					? new LogMessages(source, new LogMessage[]{})
					: rawMessages.mapTo(LogMessages.class);
		} catch (IllegalArgumentException e) {
			logger.error("Cannot extract LogMessages from local map for source {}:", source, e.getCause());
			return new LogMessages(source, new LogMessage[]{});
		}
	}

	private void setLogMessages(LogMessages messages) {
		var source = messages.source();
		try {
			var map = defaultLocalMap();
			map.put(source, messages.json());
			pushUpdate(source);
		} catch (ClassCastException e) {
			logger.error("Cannot store messages in local map because the type of the message {} is unsupported:",
					messages.getClass().getName(), e.getCause());
		}
	}

	private LocalMap<String, JsonObject> defaultLocalMap() {
		return localMap(mapKey);
	}

	private static <T> void removeOldest(LinkedList<T> list, int max) {
		while (list.size() > max) {
			list.removeFirst();
		}
	}
}
