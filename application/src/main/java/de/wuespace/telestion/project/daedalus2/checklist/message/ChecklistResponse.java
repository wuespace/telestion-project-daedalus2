package de.wuespace.telestion.project.daedalus2.checklist.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = ResponseState.class, name = "state"),
		@JsonSubTypes.Type(value = ResponseNewState.class, name = "new-state")
})
public interface ChecklistResponse extends JsonMessage {
}
