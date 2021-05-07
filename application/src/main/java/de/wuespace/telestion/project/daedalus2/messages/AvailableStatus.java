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
}
