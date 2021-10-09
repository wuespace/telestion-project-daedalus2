package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

import java.util.List;

@SuppressWarnings("unused")
public record IridiumMessage(
		@JsonProperty List<InformationElement> elements
) implements JsonMessage {
	public IridiumMessage() { this (List.of()); }
}
