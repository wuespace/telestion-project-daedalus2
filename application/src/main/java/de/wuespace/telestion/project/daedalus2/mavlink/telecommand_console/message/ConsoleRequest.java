package de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = RequestState.class, name = "state"),
		@JsonSubTypes.Type(value = RequestClear.class, name = "clear")
})
public interface ConsoleRequest extends JsonMessage {
}
