package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record Accelerometer(
		@JsonProperty float x,
		@JsonProperty float y,
		@JsonProperty float z
) implements JsonMessage {
}
