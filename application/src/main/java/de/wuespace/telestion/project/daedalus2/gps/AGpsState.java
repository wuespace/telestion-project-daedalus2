package de.wuespace.telestion.project.daedalus2.gps;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

/**
 * The current state of the A-GPS verticle.
 *
 * @param state the current state
 *              <table>
 *              <tr>
 *              	<td><code>"idle"</code></td>
 *              	<td>verticle is in idle state</td>
 *              </tr>
 *              <tr>
 *              	<td><code>"transmitting"</code></td>
 *              	<td>verticle is currently transmitting</td>
 *              </tr>
 *              </table>
 * @param dataName the name of the stored A-GPS data or <code>null</code> if no data provided yet
 * @param target the target name which receives the A-GPS data or <code>null</code> if no target provided yet
 * @param progress the current transmission progress or <code>-1</code> if not transmitting
 */
@SuppressWarnings("unused")
public record AGpsState(@JsonProperty String state, @JsonProperty String dataName, @JsonProperty String target,
						@JsonProperty double progress) implements JsonMessage {
	public AGpsState() {
		this(null, null, null, -1.0);
	}
}
