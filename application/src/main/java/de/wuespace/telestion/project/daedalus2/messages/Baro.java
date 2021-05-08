package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record Baro(
		@JsonProperty float press,
		@JsonProperty float temp,
		@JsonProperty float vacuumPress
) implements JsonMessage {
	public Baro() {
		this(0, 0, 0);
	}
}
