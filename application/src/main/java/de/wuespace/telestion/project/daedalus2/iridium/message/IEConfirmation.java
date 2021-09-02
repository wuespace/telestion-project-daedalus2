package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines an Iridium Confirmation information element.
 * It forms the application layer acknowledgement, or confirmation, that may optionally be returned from
 * the MO DirectIP vendor server to the Iridium Gateway.
 *
 * @param type    the type of information element (here <code>"confirmation"</code>)
 * @param success when <code>true</code>, the message was successfully received
 */
public record IEConfirmation(
		@JsonProperty String type,
		@JsonProperty boolean success
) {
	private IEConfirmation() {
		this(null, false);
	}
}
