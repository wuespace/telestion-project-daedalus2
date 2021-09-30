package de.wuespace.telestion.project.daedalus2.gps.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A response to a {@link RequestNewData} request.
 * The result defines the outcome of the issued request.
 *
 * @param result can be one of the following:
 *               <table>
 *               	<tr>
 *               		<td><code>0</code></td>
 *               		<td>New target has been stored</td>
 *               	</tr>
 *               	<tr>
 *               		<td><code>1</code></td>
 *               		<td>Is already transmitting</td>
 *               	</tr>
 *               </table>
 */
public record ResponseNewTarget(@JsonProperty int result) implements AGpsResponse {
	public ResponseNewTarget() {
		this(-1);
	}
}
