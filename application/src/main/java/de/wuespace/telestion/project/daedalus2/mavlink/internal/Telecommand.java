package de.wuespace.telestion.project.daedalus2.mavlink.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record Telecommand(
		@JsonProperty
		String target,
		@JsonProperty
		String msg
		) implements JsonMessage {
}
