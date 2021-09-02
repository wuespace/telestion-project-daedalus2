package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record IridiumMessage(
		@JsonProperty InformationElement[] elements
) implements JsonMessage {
}
