package de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = ResponseState.class, name = "state"),
		@JsonSubTypes.Type(value = ResponseClear.class, name = "clear"),
		@JsonSubTypes.Type(value = ResponseClearAll.class, name = "clear-all")
})
public interface ConsoleResponse extends JsonMessage {
}
