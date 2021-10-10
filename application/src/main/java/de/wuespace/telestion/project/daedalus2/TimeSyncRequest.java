package de.wuespace.telestion.project.daedalus2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

@SuppressWarnings("unused")
public record TimeSyncRequest(
		@JsonProperty String target,
		@JsonProperty String command
) implements JsonMessage {
	public TimeSyncRequest() {
		this(null, null);
	}
}
