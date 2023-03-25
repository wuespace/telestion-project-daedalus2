package de.wuespace.telestion.project.daedalus2.mongodb.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record CSVDataRequest(
		@JsonProperty String target
) implements JsonMessage {
}
