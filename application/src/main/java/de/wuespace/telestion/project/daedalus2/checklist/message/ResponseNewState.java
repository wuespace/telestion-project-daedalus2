package de.wuespace.telestion.project.daedalus2.checklist.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseNewState(@JsonProperty int result) implements ChecklistResponse {
	public ResponseNewState() {
		this(-1);
	}
}
