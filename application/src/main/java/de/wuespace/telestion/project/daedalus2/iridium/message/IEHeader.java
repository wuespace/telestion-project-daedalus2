package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines an Iridium Header information element.
 *
 * @param cdr            the call data record id maintained in the Iridium Gateway Database is given a unique value independent
 *                       of all other information in order
 * @param imei           the International Mobile Equipment Identifier from the sending unit
 * @param session_status the current status of the SBD session between the IMEI and the Iridium Gateway
 *                       <table>
 *                       	<tr>
 *                       		<th>Value</th>
 *                       		<th>Description</th>
 *                       	</tr>
 *                       	<tr>
 *                       		<td>0</td>
 *                       		<td>The SBD session completed successfully.</td>
 *                       	</tr>
 *                       	<tr>
 *                       		<td>1</td>
 *                       		<td>The MO message transfer, if any, was successful. The MT message queued at the Iridium Gateway is too large to be transferred within a single SBD session.</td>
 *                       	</tr>
 *                       	<tr>
 *                       		<td>2</td>
 *                       		<td>The MO message transfer, if any, was successful. The reported location was determined to be of unacceptable quality. This value is only applicable to IMEIs using SBD protocol revision 1.</td>
 *                       	</tr>
 *                       	<tr>
 *                       		<td>10</td>
 *                       		<td>The SBD session timed out before session completion.</td>
 *                       	</tr>
 *                       	<tr>
 *                       		<td>12</td>
 *                       		<td>The MO message being transferred by the IMEI is too large to be transferred within a single SBD session.</td>
 *                       	</tr>
 *                       	<tr>
 *                       		<td>13</td>
 *                       		<td>An RF link loss occurred during the SBD session.</td>
 *                       	</tr>
 *                       	<tr>
 *                       		<td>14</td>
 *                       		<td>An IMEI protocol anomaly occurred during SBD session.</td>
 *                       	</tr>
 *                       	<tr>
 *                       		<td>15</td>
 *                       		<td>The IMEI is prohibited from accessing the Iridium Gateway.</td>
 *                       	</tr>
 *                       </table>
 * @param momsn          The mobile-originated message sequence number associated with the SBD session.
 *                       It is incremented by the IMEI after every successful session.
 * @param mtmsn          The mobile-terminated message sequence numberassociated with the SBD session.
 *                       This value is set by the Iridium Gateway at the time that an MT message is queued for delivery.
 * @param time           Provides a UTC timestamp of the IMEI session between the IMEI and the Iridium Gateway in the format of an epoch time integer.
 */
@SuppressWarnings("unused")
public record IEHeader(
		@JsonProperty long cdr,
		@JsonProperty String imei,
		@JsonProperty int session_status,
		@JsonProperty int momsn,
		@JsonProperty int mtmsn,
		@JsonProperty long time
) implements InformationElement {
	private IEHeader() {
		this(-1, null, -1, (short) -1, (short) -1, -1);
	}
}
