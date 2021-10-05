package de.wuespace.telestion.project.daedalus2.checklist.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RequestNewState(@JsonProperty int index, @JsonProperty boolean newState) implements ChecklistRequest {
	public RequestNewState() {
		this(-1, false);
	}
}
