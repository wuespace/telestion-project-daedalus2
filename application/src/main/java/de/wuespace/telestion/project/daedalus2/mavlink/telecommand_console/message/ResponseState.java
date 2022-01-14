package de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.LogMessages;

public record ResponseState(
		@JsonProperty LogMessages messages
) implements ConsoleResponse {
}
