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
 *               		<td>The transmission has started</td>
 *               	</tr>
 *               	<tr>
 *               		<td><code>1</code></td>
 *               		<td>Is already transmitting</td>
 *               	</tr>
 *               	<tr>
 *               		<td><code>2</code></td>
 *               		<td>No A-GPS data to upload</td>
 *               	</tr>
 *               	<tr>
 *               		<td><code>3</code></td>
 *               		<td>No target to transmit data to</td>
 *               	</tr>
 *               	<tr>
 *               		<td><code>4</code></td>
 *               		<td>Cannot split binary blob</td>
 *               	</tr>
 *               	<tr>
 *               		<td><code>5</code></td>
 *               		<td>Cannot decode uploaded data (not base64)</td>
 *               	</tr>
 *               </table>
 */
@SuppressWarnings("unused")
public record ResponseTransmission(@JsonProperty int result) implements AGpsResponse {
	public ResponseTransmission() {
		this(-1);
	}
}
