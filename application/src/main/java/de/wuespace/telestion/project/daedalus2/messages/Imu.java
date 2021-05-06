package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record Imu(
		@JsonProperty Accelerometer acc,
		@JsonProperty Gyro gyro
) implements JsonMessage {
	public Imu() {
		this(new Accelerometer(), new Gyro());
	}
}
