package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines an Iridium Payload information element with custom payload information.
 *
 * @param data the custom payload received from the sender
 */
@SuppressWarnings("unused")
public record IEPayload(
		@JsonProperty IEPayloadData data
) implements InformationElement {
	private IEPayload() {
		this(null);
	}
}
