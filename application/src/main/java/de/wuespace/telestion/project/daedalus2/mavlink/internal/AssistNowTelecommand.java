package de.wuespace.telestion.project.daedalus2.mavlink.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record AssistNowTelecommand(@JsonProperty String target, @JsonProperty byte[] rawData) implements JsonMessage {
	public AssistNowTelecommand() {
		this(null, null);
	}
}
