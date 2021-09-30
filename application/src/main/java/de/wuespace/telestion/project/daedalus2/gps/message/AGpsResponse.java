package de.wuespace.telestion.project.daedalus2.gps.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = ResponseState.class, name = "state"),
		@JsonSubTypes.Type(value = ResponseNewData.class, name = "new-data"),
		@JsonSubTypes.Type(value = ResponseNewTarget.class, name = "new-target"),
		@JsonSubTypes.Type(value = ResponseTransmission.class, name = "transmission"),
		@JsonSubTypes.Type(value = ResponseAbort.class, name = "abort")
})
public interface AGpsResponse extends JsonMessage {
}
