package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines an Iridium Location information element.
 * It provides an estimate of the originating IMEI’s location.
 *
 * @param type                the type of information element (here <code>"location"</code>)
 * @param reserved            reserved value
 * @param format_code         reserved value
 * @param latitude            the estimated latitude in degrees
 * @param longitude           the estimated longitude in degrees
 * @param center_point_radius provides the radius (in Kilometers) around the center point within which the IMEI is located with an 80% probability of accuracy
 */
public record IELocation(
		@JsonProperty String type,
		@JsonProperty int reserved,
		@JsonProperty int format_code,
		@JsonProperty double latitude,
		@JsonProperty double longitude,
		@JsonProperty double center_point_radius
) {
	private IELocation() {
		this(null, -1, -1, 0.0, 0.0, 0.0);
	}
}
