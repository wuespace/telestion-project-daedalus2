package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record SystemConfig(
		@JsonProperty boolean rxsmAllowed,
		@JsonProperty boolean bat1Allowed,
		@JsonProperty boolean bat2Allowed
) implements JsonMessage {
	public SystemConfig() {
		this(
				false,false,false
		);
	}

	public SystemConfig(int rawSystemConfig) {
		this(
				(rawSystemConfig & 0b001) > 0,
				(rawSystemConfig & 0b010) > 0,
				(rawSystemConfig & 0b100) > 0
		);
	}
}
