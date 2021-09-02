package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

/**
 * The base for all Iridium information elements.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = IEHeader.class, name = "header"),
		@JsonSubTypes.Type(value = IEPayload.class, name = "payload"),
		@JsonSubTypes.Type(value = IELocation.class, name = "location"),
		@JsonSubTypes.Type(value = IEConfirmation.class, name = "confirmation")
})
public interface InformationElement extends JsonMessage {
}
