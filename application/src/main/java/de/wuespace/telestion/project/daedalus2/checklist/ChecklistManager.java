package de.wuespace.telestion.project.daedalus2.checklist;

import de.wuespace.telestion.api.message.JsonMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.pointer.JsonPointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * A manager for a synchronized checklist, configured in the verticle configuration.
 * <p>
 * The verticle takes in the "root" checklist as configuration:
 * <pre>
 * type Checklist = { [key: string]: boolean | Checklist }
 * type Configuration = {
 *     checklist: Checklist,
 *     dispatchAddress?: string = "checklist-dispatch",
 *     outAddress?: string = "checklist-state"
 * }
 * </pre>
 */
@SuppressWarnings("unused")
public class ChecklistManager extends AbstractVerticle {

	public static final String DEFAULT_OUT_ADDRESS = "checklist-state";
	public static final String DEFAULT_DISPATCH_ADDRESS = "checklist-dispatch";
	public static final String MAP_KEY = "checklist-state";
	public static final String CHECKLIST_KEY = "checklist";

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		reset();

		var eb = vertx.eventBus();
		eb.consumer(config().getString("dispatchAddress", DEFAULT_DISPATCH_ADDRESS),
				raw -> JsonMessage.on(
						ChecklistCommand.class, raw, this::op
				)
		);
	}

	private void op(ChecklistCommand command) {
		List<String> params = command.params() != null ? command.params() : List.of();

		String op = command.op();
		switch (op) {
			case "done":
				if (!params.isEmpty()) {
					String selector = params.get(0);
					setChecklistState((JsonObject) JsonPointer
							.from(selector)
							.writeJson(getChecklistState(), true)
					);
				}
				break;
			case "reset":
				if (!params.isEmpty()) {
					String selector = params.get(0);
					setChecklistState((JsonObject) JsonPointer
							.from(selector)
							.writeJson(getChecklistState(), false)
					);
				} else {
					reset();
				}
				break;
			case "continue":
				break;
			default:
				logger.warn("Illegal operation requested: {}", op);
		}

		// always broadcast new state after operation
		getVertx().eventBus().publish(config().getString("outAddress", DEFAULT_OUT_ADDRESS), getChecklistState());
	}

	private void reset() {
		setChecklistState(config().getJsonObject(CHECKLIST_KEY));
	}

	private JsonObject getChecklistState() {
		return (JsonObject) vertx.sharedData()
				.getLocalMap(MAP_KEY)
				.getOrDefault(CHECKLIST_KEY, config().getJsonObject(CHECKLIST_KEY).copy());
	}

	private void setChecklistState(JsonObject checklistState) {
		vertx.sharedData()
				.getLocalMap(MAP_KEY)
				.put(CHECKLIST_KEY, checklistState);
	}

	private static final Logger logger = LoggerFactory.getLogger(ChecklistManager.class);
}
