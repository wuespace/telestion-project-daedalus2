package de.wuespace.telestion.project.daedalus2.gps;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

@SuppressWarnings("unused")
public record AGpsData(@JsonProperty String name, @JsonProperty String encodedData) implements JsonMessage {
	public AGpsData() {
		this(null, null);
	}
}
