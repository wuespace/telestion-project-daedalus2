package de.wuespace.telestion.project.daedalus2.checklist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.api.verticle.trait.WithSharedData;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.pointer.JsonPointer;
import io.vertx.core.shareddata.LocalMap;

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
public class ChecklistManager extends TelestionVerticle<ChecklistManager.Configuration> implements WithEventBus, WithSharedData {

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Configuration(
			@JsonProperty String dispatchAddress,
			@JsonProperty String outAddress
	) implements TelestionConfiguration {
		public Configuration() {
			this("checklist-dispatch", "checklist-state");
		}
	}

	@Override
	public void onStart() {
		setDefaultConfig(new Configuration());
		resetState();
		register(getConfig().dispatchAddress(), this::handle, ChecklistCommand.class);
	}

	private void handle(ChecklistCommand command) {
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
					resetState();
				}
				break;
			case "continue":
				break;
			default:
				logger.warn("Illegal operation requested: {}", op);
		}

		// always broadcast new state after operation
		publish(getConfig().outAddress(), getChecklistState());
	}

	private void resetState() {
		setChecklistState(getCheckList());
	}

	private JsonObject getChecklistState() {
		return getLocalMap().getOrDefault(CHECKLIST_KEY, getCheckList().copy());
	}

	/**
	 * Returns the configured checklist from the configuration.
	 * @return the configured checklist
	 */
	private JsonObject getCheckList() {
		return getGenericConfig().getJsonObject("checklist", new JsonObject());
	}

	private void setChecklistState(JsonObject checklistState) {
		getLocalMap().put(CHECKLIST_KEY, checklistState);
	}

	private LocalMap<String, JsonObject> getLocalMap() {
		return this.localMap(MAP_KEY);
	}

	private static final String MAP_KEY = "checklist-state";
	private static final String CHECKLIST_KEY = "checklist";
}
