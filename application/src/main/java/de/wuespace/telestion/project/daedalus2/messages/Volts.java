package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record Volts(
		@JsonProperty float rxsmVolts,
		@JsonProperty float bat1_volts,
		@JsonProperty float bat2_volts,
		@JsonProperty float rail3V3Volts
) implements JsonMessage {
	public Volts() {
		this(0, 0, 0, 0);
	}
}
