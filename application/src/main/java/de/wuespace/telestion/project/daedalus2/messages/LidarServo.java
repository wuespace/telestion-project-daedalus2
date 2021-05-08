package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record LidarServo(
		@JsonProperty boolean lidarServoOn,
		@JsonProperty boolean lidarServoOpen

) implements JsonMessage {
	public LidarServo() {
		this(
				false,false
		);
	}

	public LidarServo(int rawLidarServo) {
		this(
				(rawLidarServo & 0b01) > 0,
				(rawLidarServo & 0b10) > 0
		);
	}
}
