package de.wuespace.telestion.project.daedalus2.gps.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.project.daedalus2.gps.AGpsState;

/**
 * A response to a {@link RequestState} request.
 *
 * @param state the current A-GPS verticle state
 */
@SuppressWarnings("unused")
public record ResponseState(@JsonProperty AGpsState state) implements AGpsResponse {
	public ResponseState() {
		this(null);
	}
}
