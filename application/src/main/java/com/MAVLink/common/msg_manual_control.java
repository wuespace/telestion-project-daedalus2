/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE MANUAL_CONTROL PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * This message provides an API for manually controlling the vehicle using standard joystick axes nomenclature, along
 * with a joystick-like input device. Unused axes can be disabled and buttons states are transmitted as individual
 * on/off bits of a bitmask
 */
public class msg_manual_control extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_MANUAL_CONTROL = 69;
	public static final int MAVLINK_MSG_LENGTH = 18;
	private static final long serialVersionUID = MAVLINK_MSG_ID_MANUAL_CONTROL;


	/**
	 * X-axis, normalized to the range [-1000,1000]. A value of INT16_MAX indicates that this axis is invalid.
	 * Generally corresponds to forward(1000)-backward(-1000) movement on a joystick and the pitch of a vehicle.
	 */
	public short x;

	/**
	 * Y-axis, normalized to the range [-1000,1000]. A value of INT16_MAX indicates that this axis is invalid.
	 * Generally corresponds to left(-1000)-right(1000) movement on a joystick and the roll of a vehicle.
	 */
	public short y;

	/**
	 * Z-axis, normalized to the range [-1000,1000]. A value of INT16_MAX indicates that this axis is invalid.
	 * Generally corresponds to a separate slider movement with maximum being 1000 and minimum being -1000 on a
	 * joystick and the thrust of a vehicle. Positive values are positive thrust, negative values are negative thrust.
	 */
	public short z;

	/**
	 * R-axis, normalized to the range [-1000,1000]. A value of INT16_MAX indicates that this axis is invalid.
	 * Generally corresponds to a twisting of the joystick, with counter-clockwise being 1000 and clockwise being
	 * -1000, and the yaw of a vehicle.
	 */
	public short r;

	/**
	 * A bitfield corresponding to the joystick buttons' 0-15 current state, 1 for pressed, 0 for released. The lowest
	 * bit corresponds to Button 1.
	 */
	public int buttons;

	/**
	 * The system to be controlled.
	 */
	public short target;

	/**
	 * A bitfield corresponding to the joystick buttons' 16-31 current state, 1 for pressed, 0 for released. The
	 * lowest bit corresponds to Button 16.
	 */
	public int buttons2;

	/**
	 * Set bits to 1 to indicate which of the following extension fields contain valid data: bit 0: pitch, bit 1: roll.
	 */
	public short enabled_extensions;

	/**
	 * Pitch-only-axis, normalized to the range [-1000,1000]. Generally corresponds to pitch on vehicles with
	 * additional degrees of freedom. Valid if bit 0 of enabled_extensions field is set. Set to 0 if invalid.
	 */
	public short s;

	/**
	 * Roll-only-axis, normalized to the range [-1000,1000]. Generally corresponds to roll on vehicles with additional
	 * degrees of freedom. Valid if bit 1 of enabled_extensions field is set. Set to 0 if invalid.
	 */
	public short t;


	/**
	 * Generates the payload for a mavlink message for a message of this type
	 *
	 * @return
	 */
	@Override
	public MAVLinkPacket pack() {
		MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH, isMavlink2);
		packet.sysid = sysid;
		packet.compid = compid;
		packet.msgid = MAVLINK_MSG_ID_MANUAL_CONTROL;

		packet.payload.putShort(x);
		packet.payload.putShort(y);
		packet.payload.putShort(z);
		packet.payload.putShort(r);
		packet.payload.putUnsignedShort(buttons);
		packet.payload.putUnsignedByte(target);

		if (isMavlink2) {
			packet.payload.putUnsignedShort(buttons2);
			packet.payload.putUnsignedByte(enabled_extensions);
			packet.payload.putShort(s);
			packet.payload.putShort(t);

		}
		return packet;
	}

	/**
	 * Decode a manual_control message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.x = payload.getShort();
		this.y = payload.getShort();
		this.z = payload.getShort();
		this.r = payload.getShort();
		this.buttons = payload.getUnsignedShort();
		this.target = payload.getUnsignedByte();

		if (isMavlink2) {
			this.buttons2 = payload.getUnsignedShort();
			this.enabled_extensions = payload.getUnsignedByte();
			this.s = payload.getShort();
			this.t = payload.getShort();

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_manual_control() {
		this.msgid = MAVLINK_MSG_ID_MANUAL_CONTROL;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_manual_control(short x,
			short y,
			short z,
			short r,
			int buttons,
			short target,
			int buttons2,
			short enabled_extensions,
			short s,
			short t) {
		this.msgid = MAVLINK_MSG_ID_MANUAL_CONTROL;

		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
		this.buttons = buttons;
		this.target = target;
		this.buttons2 = buttons2;
		this.enabled_extensions = enabled_extensions;
		this.s = s;
		this.t = t;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_manual_control(short x,
			short y,
			short z,
			short r,
			int buttons,
			short target,
			int buttons2,
			short enabled_extensions,
			short s,
			short t,
			int sysid,
			int compid,
			boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_MANUAL_CONTROL;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
		this.buttons = buttons;
		this.target = target;
		this.buttons2 = buttons2;
		this.enabled_extensions = enabled_extensions;
		this.s = s;
		this.t = t;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_manual_control(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_MANUAL_CONTROL;

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
		return "MAVLINK_MSG_ID_MANUAL_CONTROL - sysid:" + sysid + " compid:" + compid + " x:" + x + " y:" + y + " z:" + z + " r:" + r + " buttons:" + buttons + " target:" + target + " buttons2:" + buttons2 + " enabled_extensions:" + enabled_extensions + " s:" + s + " t:" + t + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_MANUAL_CONTROL";
	}
}
