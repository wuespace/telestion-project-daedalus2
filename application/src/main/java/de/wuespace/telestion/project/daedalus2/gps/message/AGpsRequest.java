package de.wuespace.telestion.project.daedalus2.gps.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

/**
 * A generic request for the A-GPS verticle.
 * It can change the state of the verticle and always return responses.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = RequestState.class, name = "state"),
		@JsonSubTypes.Type(value = RequestNewTarget.class, name = "new-target"),
		@JsonSubTypes.Type(value = RequestNewData.class, name = "new-data"),
		@JsonSubTypes.Type(value = RequestTransmission.class, name = "transmission"),
		@JsonSubTypes.Type(value = RequestAbort.class, name = "abort")
})
public interface AGpsRequest extends JsonMessage {
}
