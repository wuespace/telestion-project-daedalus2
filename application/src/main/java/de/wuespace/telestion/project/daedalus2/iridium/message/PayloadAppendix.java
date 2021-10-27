package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = AppendixNone.class, name = "none"),
		@JsonSubTypes.Type(value = AppendixRotation.class, name = "rotation"),
		@JsonSubTypes.Type(value = AppendixBlade.class, name = "blade"),
		@JsonSubTypes.Type(value = AppendixVelocity.class, name = "velocity")
})
public interface PayloadAppendix extends JsonMessage {
}
