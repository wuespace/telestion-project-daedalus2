package de.wuespace.telestion.project.daedalus2.checklist.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.project.daedalus2.checklist.ChecklistState;

public record ResponseState(@JsonProperty ChecklistState state) implements ChecklistResponse {
	public ResponseState() {
		this(null);
	}
}
