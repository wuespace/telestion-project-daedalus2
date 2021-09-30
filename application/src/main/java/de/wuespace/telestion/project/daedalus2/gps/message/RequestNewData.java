package de.wuespace.telestion.project.daedalus2.gps.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.project.daedalus2.gps.AGpsData;

/**
 * Requests the verticle to store the attached A-GPS data
 * in the shared data space.
 *
 * @param data the new A-GPS data to store
 */
public record RequestNewData(@JsonProperty AGpsData data) implements AGpsRequest {
	public RequestNewData() {
		this(null);
	}
}
