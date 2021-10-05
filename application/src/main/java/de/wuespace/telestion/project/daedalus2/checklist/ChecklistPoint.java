package de.wuespace.telestion.project.daedalus2.checklist;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record ChecklistPoint(@JsonProperty String name) implements JsonMessage {
	public ChecklistPoint() {
		this(null);
	}
}
