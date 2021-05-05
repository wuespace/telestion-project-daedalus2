package de.wuespace.telestion.project.daedalus2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record TransformedMessage(
		@JsonProperty
		String zeug
) implements JsonMessage {
	@SuppressWarnings("unused")
	public TransformedMessage() {
		this(null);
	}
}
