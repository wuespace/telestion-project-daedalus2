package de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record LogMessages(
		@JsonProperty String source,
		@JsonProperty LogMessage[] messages
		) implements JsonMessage {
}
