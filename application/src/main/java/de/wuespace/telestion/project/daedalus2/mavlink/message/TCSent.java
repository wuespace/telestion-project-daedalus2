package de.wuespace.telestion.project.daedalus2.mavlink.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record TCSent(@JsonProperty String target) implements JsonMessage {
	private TCSent() {
		this(null);
	}
}
