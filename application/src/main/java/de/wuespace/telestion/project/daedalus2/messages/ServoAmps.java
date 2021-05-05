package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record ServoAmps(
		@JsonProperty float swashplate_servo1_amps,
		@JsonProperty float swashplate_servo2_amps,
		@JsonProperty float swashplate_servo3_amps,
		@JsonProperty float fin_servo_amps
) implements JsonMessage {
}
