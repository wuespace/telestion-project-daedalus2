package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record AvailableStatus(
		@JsonProperty boolean imuAccAvail,
		@JsonProperty boolean imuGyroAvail,
		@JsonProperty boolean baroAvail,
		@JsonProperty boolean vacuumBaroAvail,
		@JsonProperty boolean tachoRotAvail,
		@JsonProperty boolean servoAmpsAvail,
		@JsonProperty boolean batTempAvail,
		@JsonProperty boolean voltsAvail

) implements JsonMessage {
	public AvailableStatus() {
		this(
				false, false, false, false,
				false, false, false, false
		);
	}

	public AvailableStatus(int rawAvailableStatus) {
		this(
				(rawAvailableStatus & 0b00000001) > 0,
				(rawAvailableStatus & 0b00000010) > 0,
				(rawAvailableStatus & 0b00000100) > 0,
				(rawAvailableStatus & 0b00001000) > 0,
				(rawAvailableStatus & 0b00010000) > 0,
				(rawAvailableStatus & 0b00100000) > 0,
				(rawAvailableStatus & 0b01000000) > 0,
				(rawAvailableStatus & 0b10000000) > 0
		);
	}
}
