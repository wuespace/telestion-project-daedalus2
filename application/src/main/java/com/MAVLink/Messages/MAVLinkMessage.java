/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

package com.MAVLink.Messages;

import java.io.Serializable;

import com.MAVLink.MAVLinkPacket;

/**
 * Common interface for all MAVLink messages
 */
public abstract class MAVLinkMessage implements Serializable {
	private static final long serialVersionUID = -7754622750478538539L;
	// The MAVLink message classes have been changed to implement Serializable,
	// this way is possible to pass a mavlink message trought the Service-Acctivity interface

	public int sysid;
	public int compid;
	public int msgid;
	public boolean isMavlink2;

	public abstract MAVLinkPacket pack();

	public abstract void unpack(com.MAVLink.Messages.MAVLinkPayload payload);

	public abstract String toString();

	public abstract String name();
}
