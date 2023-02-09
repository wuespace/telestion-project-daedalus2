package de.wuespace.telestion.project.daedalus2.iridium.message;

/**
 * Describes an invalid payload from a Daedalus2 seed.
 * @param raw the raw received content from the seed in hexadecimal encoded values
 */
public record InvalidDaedalus2Payload(
		String raw
) implements Daedalus2Payload {
}
