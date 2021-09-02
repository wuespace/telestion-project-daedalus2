package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record Message(@JsonProperty InformationElement... elements) implements JsonMessage {
	private Message() {
		this((InformationElement) null);
	}
}
