package de.wuespace.telestion.project.daedalus2.checklist;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

import java.util.List;

public record ChecklistCommand(
		@JsonProperty String op,
		@JsonProperty List<String> params
) implements JsonMessage {
	public ChecklistCommand() {
		this(null, null);
	}
}
