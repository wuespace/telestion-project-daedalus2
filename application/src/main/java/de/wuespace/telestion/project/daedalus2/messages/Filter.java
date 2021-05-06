package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record Filter(
		@JsonProperty float velVertical,
		@JsonProperty float velVerticalInd,
		@JsonProperty float heightGround,
		@JsonProperty float rotorRotRate,
		@JsonProperty float bodyRotRate
) implements JsonMessage {
	public Filter() {
		this(0, 0, 0, 0, 0);
	}
}
