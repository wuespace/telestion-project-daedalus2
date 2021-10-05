package de.wuespace.telestion.project.daedalus2.checklist;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record ChecklistState(@JsonProperty ChecklistPoint[] points,
							 @JsonProperty boolean[] states) implements JsonMessage {
	public ChecklistState() {
		this(new ChecklistPoint[]{}, new boolean[]{});
	}
}
