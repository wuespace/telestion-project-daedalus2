package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines an Iridium Confirmation information element.
 * It forms the application layer acknowledgement, or confirmation, that may optionally be returned from
 * the MO DirectIP vendor server to the Iridium Gateway.
 *
 * @param success when <code>true</code>, the message was successfully received
 */
@SuppressWarnings("unused")
public record IEConfirmation(
		@JsonProperty boolean success
) implements InformationElement {
	private IEConfirmation() {
		this(false);
	}
}
