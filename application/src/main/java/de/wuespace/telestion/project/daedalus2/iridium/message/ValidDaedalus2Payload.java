package de.wuespace.telestion.project.daedalus2.iridium.message;

/**
 * Describes a valid payload from a Daedalus2 seed.
 *
 * @param gps_fix_avail a binary flag indicating the trustworthiness of the given coordinates
 *                      directly read from the GPS module
 * @param seed_state    the current seed state
 * @param is_valid_fix      when <code>true</code> the current payload is the last valid state the seed got
 *                      from the GPS module
 * @param latitude      the current/last valid latitude of the seed
 * @param longitude     the current/last valid longitude of the seed
 * @param altitude      the current/last valid altitude of the seed
 */
public record ValidDaedalus2Payload(
		boolean gps_fix_avail,
		int seed_state,
		boolean is_valid_fix,
		double latitude,
		double longitude,
		int altitude
) implements Daedalus2Payload {
}
