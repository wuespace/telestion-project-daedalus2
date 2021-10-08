package de.wuespace.telestion.project.daedalus2.checklist;

import de.wuespace.telestion.api.message.JsonMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.pointer.JsonPointer;

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
public class ChecklistManager extends AbstractVerticle {
	public ChecklistManager() {
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		reset();

		var eb = vertx.eventBus();
		eb.consumer(config().getString("dispatchAddress", "checklist-dispatch"),
				raw -> JsonMessage.on(
						ChecklistCommand.class, raw, this::op
				)
		);
	}

	private void op(ChecklistCommand command) {
		List<String> params = command.params() != null ? command.params() : List.of();

		switch (command.op()) {
			case "done":
				if (params.size() > 0) {
					String selector = params.get(0);
					setChecklistState((JsonObject) JsonPointer
							.from(selector)
							.writeJson(getChecklistState(), true)
					);
				}
				break;
			case "reset":
				if (params.size() > 0) {
					String selector = params.get(0);
					setChecklistState((JsonObject) JsonPointer
							.from(selector)
							.writeJson(getChecklistState(), false)
					);
				} else {
					reset();
				}
				break;
		}

		// always broadcast new state after operation
		getVertx().eventBus().publish(config().getString("outAddress", "checklist-state"), getChecklistState());
	}

	private void reset() {
		setChecklistState(config().getJsonObject("checklist"));
	}

	private JsonObject getChecklistState() {
		return (JsonObject) vertx.sharedData()
				.getLocalMap("checklist-state")
				.getOrDefault("checklist", config().getJsonObject("checklist").copy());
	}

	private void setChecklistState(JsonObject checklistState) {
		vertx.sharedData()
				.getLocalMap("checklist-state")
				.put("checklist", checklistState);
	}
}
