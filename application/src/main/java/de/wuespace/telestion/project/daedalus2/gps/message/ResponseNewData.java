package de.wuespace.telestion.project.daedalus2.gps.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A response to a {@link RequestTransmission} request.
 * The result defines the outcome of the issued request.
 *
 * @param result can be one of the following:
 *               <table>
 *               	<tr>
 *               		<td><code>0</code></td>
 *               		<td>New data has been stored</td>
 *               	</tr>
 *               	<tr>
 *               		<td><code>1</code></td>
 *               		<td>Is already transmitting</td>
 *               	</tr>
 *               </table>
 */
@SuppressWarnings("unused")
public record ResponseNewData(@JsonProperty int result) implements AGpsResponse {
	public ResponseNewData() {
		this(-1);
	}
}
