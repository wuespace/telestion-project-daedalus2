package de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RequestClear(
		@JsonProperty String source
) implements ConsoleRequest {
}
