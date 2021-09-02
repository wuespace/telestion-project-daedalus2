package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines an Iridium Payload information element with custom payload information.
 *
 * @param type the type of information element (here <code>"payload"</code>)
 * @param data the custom payload received from the sender
 */
public record IEPayload(
		@JsonProperty String type,
		@JsonProperty IEPayloadData data
) implements InformationElement {
	private IEPayload() {
		this(null, null);
	}
}
