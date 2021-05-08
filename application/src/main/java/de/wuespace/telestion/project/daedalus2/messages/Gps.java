package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record Gps(
		@JsonProperty float latitude,
		@JsonProperty float longitude,
		@JsonProperty float horizontalDilutionOfPrecision,
		@JsonProperty float altitude,
		@JsonProperty int quality,
		@JsonProperty int numberOfSatsUsed
) implements JsonMessage {
	public Gps() {
		this(0, 0, 0, 0, 0, 0);
	}
}
