package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record BatVolts(
		@JsonProperty float bat1_volts,
		@JsonProperty float bat2_volts
) implements JsonMessage {
	public BatVolts() {
		this(0, 0);
	}
}
