package de.wuespace.telestion.project.daedalus2.mavlink.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record RawTelecommand(@JsonProperty String target, @JsonProperty byte[] rawData) implements JsonMessage {
}
