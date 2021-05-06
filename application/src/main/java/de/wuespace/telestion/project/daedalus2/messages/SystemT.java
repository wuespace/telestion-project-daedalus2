package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

/**
 * A beautiful version of the Drehtest message
 *
 * @param timeLocal Seed local time
 */
public record SystemT(
		@JsonProperty
		long timeLocal,

		@JsonProperty
		Imu imu,

		@JsonProperty
		float tachoRotRate,

		@JsonProperty
		ServoAmps servoAmps,

		@JsonProperty
		BatVolts batVolts,

		@JsonProperty
		Filter filter,

		@JsonProperty
		Controller controller,

		@JsonProperty
		int controllerId,

		@JsonProperty
		Status status
) implements JsonMessage {
	public SystemT() {
		this(
				0,
				new Imu(),
				0,
				new ServoAmps(),
				new BatVolts(),
				new Filter(),
				new Controller(),
				0,
				new Status()
		);
	}
}
