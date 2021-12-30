package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines an Iridium Payload information element with custom payload information.
 */
@SuppressWarnings("unused")
public record IEPayload(
		@JsonProperty double latitude,
		@JsonProperty double longitude,
		@JsonProperty int altitude,
		@JsonProperty PayloadAppendix payload_appendix
) implements InformationElement {
}
