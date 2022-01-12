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
			logger.info("State request received.");
			message.reply(new ResponseState(getLogMessages(((RequestState) request).source())).json());
			logger.info("Current state sent.");
		} else if (request instanceof RequestClear) {
			logger.info("Clear request received.");
			clearLogMessages();
			message.reply(new ResponseClear().json());
			logger.info("Cleared log messages.");
		} else {
			logger.warn("Unsupported request received. (Expected: RequestState/RequestClear, Got: {}). " +
					"Please register it in request handler to properly handle this request.",
					request.getClass().getSimpleName());
		}
	}

	private final String mapKey = getClass().getName();

	private void pushUpdate(String source) {
		publish(getConfig().notifyAddress(), getLogMessages(source));
	}

	private void addLogMessage(LogMessage log) {
		var map = defaultLocalMap();
		var messages = map.getOrDefault(log.source(), emptyStringArray);

		var list = new LinkedList<>(List.of(messages));
		list.addLast(log.message());
		removeOldest(list, getConfig().maxNumberOfLines());

		map.put(log.source(), list.toArray(String[]::new));
		pushUpdate(log.source());
	}

	private void clearLogMessages() {
		var map = defaultLocalMap();
		var sources = map.keySet();
		map.clear();
		sources.forEach(this::pushUpdate);
	}

	private Log getLogMessages(String source) {
		return new Log(source, defaultLocalMap().getOrDefault(source, emptyStringArray));
	}

	private LocalMap<String, String[]> defaultLocalMap() {
		return localMap(mapKey);
	}

	private static void removeOldest(LinkedList<String> messages, int max) {
		while (messages.size() > max) {
			messages.removeFirst();
		}
	}

	private static final String[] emptyStringArray = new String[]{};
}
