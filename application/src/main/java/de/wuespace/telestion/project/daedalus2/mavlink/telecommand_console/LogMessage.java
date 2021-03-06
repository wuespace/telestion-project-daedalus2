package de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record LogMessage(
		@JsonProperty String source,
		@JsonProperty String message,
		@JsonProperty long receiveTime,
		@JsonProperty long time_local
) implements JsonMessage {
}
