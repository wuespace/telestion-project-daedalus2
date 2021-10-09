package de.wuespace.telestion.project.daedalus2.gps.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Requests the verticle to store the new target in the shared data space.
 *
 * @param target the new target for the A-GPS data
 */
@SuppressWarnings("unused")
public record RequestNewTarget(@JsonProperty String target) implements AGpsRequest {
	public RequestNewTarget() {
		this(null);
	}
}
