package de.wuespace.telestion.project.daedalus2.iridium;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.iridium.message.IEHeader;
import de.wuespace.telestion.project.daedalus2.iridium.message.IELocation;
import de.wuespace.telestion.project.daedalus2.iridium.message.IEPayload;

@SuppressWarnings("unused")
public record MappedMessage(
		@JsonProperty IEHeader header,
		@JsonProperty IEPayload payload,
		@JsonProperty IELocation location
) implements JsonMessage {
}
