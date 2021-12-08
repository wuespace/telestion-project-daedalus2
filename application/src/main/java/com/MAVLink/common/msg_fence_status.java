/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE FENCE_STATUS PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Status of geo-fencing. Sent in extended status stream when fencing enabled.
 */
public class msg_fence_status extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_FENCE_STATUS = 162;
	public static final int MAVLINK_MSG_LENGTH = 9;
	private static final long serialVersionUID = MAVLINK_MSG_ID_FENCE_STATUS;


	/**
	 * Time (since boot) of last breach.
	 */
	public long breach_time;

	/**
	 * Number of fence breaches.
	 */
	public int breach_count;

	/**
	 * Breach status (0 if currently inside fence, 1 if outside).
	 */
	public short breach_status;

	/**
	 * Last breach type.
	 */
	public short breach_type;

	/**
	 * Active action to prevent fence breach
	 */
	public short breach_mitigation;


	/**
	 * Generates the payload for a mavlink message for a message of this type
	 *
	 * @return
	 */
	@Override
	public MAVLinkPacket pack() {
		MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH, isMavlink2);
		packet.sysid = 255;
		packet.compid = 190;
		packet.msgid = MAVLINK_MSG_ID_FENCE_STATUS;

		packet.payload.putUnsignedInt(breach_time);
		packet.payload.putUnsignedShort(breach_count);
		packet.payload.putUnsignedByte(breach_status);
		packet.payload.putUnsignedByte(breach_type);

		if (isMavlink2) {
			packet.payload.putUnsignedByte(breach_mitigation);

		}
		return packet;
	}

	/**
	 * Decode a fence_status message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.breach_time = payload.getUnsignedInt();
		this.breach_count = payload.getUnsignedShort();
		this.breach_status = payload.getUnsignedByte();
		this.breach_type = payload.getUnsignedByte();

		if (isMavlink2) {
			this.breach_mitigation = payload.getUnsignedByte();

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_fence_status() {
		this.msgid = MAVLINK_MSG_ID_FENCE_STATUS;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_fence_status(long breach_time, int breach_count, short breach_status, short breach_type, short breach_mitigation) {
		this.msgid = MAVLINK_MSG_ID_FENCE_STATUS;

		this.breach_time = breach_time;
		this.breach_count = breach_count;
		this.breach_status = breach_status;
		this.breach_type = breach_type;
		this.breach_mitigation = breach_mitigation;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_fence_status(long breach_time, int breach_count, short breach_status, short breach_type, short breach_mitigation, int sysid, int compid, boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_FENCE_STATUS;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.breach_time = breach_time;
		this.breach_count = breach_count;
		this.breach_status = breach_status;
		this.breach_type = breach_type;
		this.breach_mitigation = breach_mitigation;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_fence_status(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_FENCE_STATUS;

		this.sysid = mavLinkPacket.sysid;
		this.compid = mavLinkPacket.compid;
		this.isMavlink2 = mavLinkPacket.isMavlink2;
		unpack(mavLinkPacket.payload);
	}


	/**
	 * Returns a string with the MSG name and data
	 */
	@Override
	public String toString() {
		return "MAVLINK_MSG_ID_FENCE_STATUS - sysid:" + sysid + " compid:" + compid + " breach_time:" + breach_time + " breach_count:" + breach_count + " breach_status:" + breach_status + " breach_type:" + breach_type + " breach_mitigation:" + breach_mitigation + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_FENCE_STATUS";
	}
}
