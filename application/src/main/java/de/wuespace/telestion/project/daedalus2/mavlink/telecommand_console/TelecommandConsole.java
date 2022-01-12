package de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.api.verticle.trait.WithSharedData;
import de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.*;
import io.vertx.core.eventbus.Message;
import io.vertx.core.shareddata.LocalMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class TelecommandConsole extends TelestionVerticle<TelecommandConsole.Configuration>
		implements WithEventBus, WithSharedData {

	/**
	 * @param inAddress the address on which new telemetry is coming in
	 * @param notifyAddress the address on which the verticle publishes the new state if something changes
	 * @param requestAddress the address on which someone can request the current state (will be replied)
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
		register(getConfig().inAddress(), this::addLogMessage, LogMessage.class);
		register(getDefaultConfig().requestAddress(), this::handleRequest, ConsoleRequest.class);
		logger.debug("Wait for new log messages...");
	}

	public void handleRequest(ConsoleRequest request, Message<Object> message) {
		if (request instanceof RequestState) {
			var source = ((RequestState) request).source();
			logger.info("State request for source {} received.", source);

			message.reply(new ResponseState(getLogMessages(source)).json());
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

	private void addLogMessage(LogMessage log) {
		var map = defaultLocalMap();
		var messages = decode(map.getOrDefault(log.source(), null));

		var list = new LinkedList<>(List.of(messages));
		list.addLast(log.message());
		removeOldest(list, getConfig().maxNumberOfLines());

		map.put(log.source(), encode(list.toArray(String[]::new)));
		pushUpdate(log.source());
	}

	private void clearLogMessages(String source) {
		var map = defaultLocalMap();
		map.put(source, encode(null));
		pushUpdate(source);
	}

	private void clearAllLogMessages() {
		var map = defaultLocalMap();
		map.keySet().forEach(this::clearLogMessages);
	}

	private Log getLogMessages(String source) {
		return new Log(source, decode(defaultLocalMap().getOrDefault(source, null)));
	}

	private LocalMap<String, String> defaultLocalMap() {
		return localMap(mapKey);
	}

	private static void removeOldest(LinkedList<String> messages, int max) {
		while (messages.size() > max) {
			messages.removeFirst();
		}
	}

	private static String encode(String[] messages) {
		if (Objects.isNull(messages)) return "";
		return String.join(TOTALLY_SECURE_SEPARATOR, messages);
	}

	private static String[] decode(String encoded) {
		if (Objects.isNull(encoded)) return new String[]{};
		return encoded.split(TOTALLY_SECURE_SEPARATOR);
	}

	private static final String[] emptyStringArray = new String[]{};

	private static final String TOTALLY_SECURE_SEPARATOR = "separator-separator-separator";
}
