package de.wuespace.telestion.project.daedalus2.gps.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A response to a {@link RequestAbort} request.
 * The result defines the outcome of the issued request.
 *
 * @param result can be one of the following:
 *               <table>
 *               	<tr>
 *               		<td><code>0</code></td>
 *               		<td>Transmission has been aborted</td>
 *               	</tr>
 *               	<tr>
 *               		<td><code>1</code></td>
 *               		<td>Transmission is not running. Nothing to abort</td>
 *               	</tr>
 *               </table>
 */
public record ResponseAbort(@JsonProperty int result) implements AGpsResponse {
	public ResponseAbort() {
		this(-1);
	}
}
