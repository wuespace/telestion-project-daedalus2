package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

/**
 * Describes a payload from a Daedalus2 seed.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = ValidDaedalus2Payload.class, name = "valid"),
		@JsonSubTypes.Type(value = InvalidDaedalus2Payload.class, name = "invalid")
})
public interface Daedalus2Payload extends JsonMessage {
}
