package de.wuespace.telestion.project.daedalus2.checklist.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

/**
 * A generic request for the checklist verticle.
 * It can change the state of the verticle and always return responses.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = RequestState.class, name = "state"),
		@JsonSubTypes.Type(value = RequestNewState.class, name = "new-state")
})
public interface ChecklistRequest extends JsonMessage {
}
