package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record Controller(
		@JsonProperty float bladePitch,
		@JsonProperty float finAngle
) implements JsonMessage {
	public Controller() {
		this(0, 0);
	}
}
