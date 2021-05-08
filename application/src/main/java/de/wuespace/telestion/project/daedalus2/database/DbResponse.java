package de.wuespace.telestion.project.daedalus2.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;
import io.vertx.core.json.JsonObject;
import java.util.Collections;
import java.util.List;

public record DbResponse(
		@JsonProperty List<JsonObject> result) implements JsonMessage {
			private DbResponse() {
				this(Collections.emptyList());
			}
}
