package de.wuespace.telestion.project.daedalus2.iridium.message;

/**
 * Defines an Iridium Payload information element with custom payload information.
 */
@SuppressWarnings("unused")
public record IEPayload(
		Daedalus2Payload payload
) implements InformationElement {
}
