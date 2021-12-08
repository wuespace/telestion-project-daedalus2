/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE MEMORY_VECT PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Send raw controller memory. The use of this message is discouraged for normal packets, but a quite efficient way for testing new messages and getting experimental debug output.
 */
public class msg_memory_vect extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_MEMORY_VECT = 249;
	public static final int MAVLINK_MSG_LENGTH = 36;
	private static final long serialVersionUID = MAVLINK_MSG_ID_MEMORY_VECT;


	/**
	 * Starting address of the debug variables
	 */
	public int address;

	/**
	 * Version code of the type variable. 0=unknown, type ignored and assumed int16_t. 1=as below
	 */
	public short ver;

	/**
	 * Type code of the memory variables. for ver = 1: 0=16 x int16_t, 1=16 x uint16_t, 2=16 x Q15, 3=16 x 1Q14
	 */
	public short type;

	/**
	 * Memory contents at specified address
	 */
	public byte value[] = new byte[32];


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
		packet.msgid = MAVLINK_MSG_ID_MEMORY_VECT;

		packet.payload.putUnsignedShort(address);
		packet.payload.putUnsignedByte(ver);
		packet.payload.putUnsignedByte(type);

		for (int i = 0; i < value.length; i++) {
			packet.payload.putByte(value[i]);
		}


		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a memory_vect message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.address = payload.getUnsignedShort();
		this.ver = payload.getUnsignedByte();
		this.type = payload.getUnsignedByte();

		for (int i = 0; i < this.value.length; i++) {
			this.value[i] = payload.getByte();
		}


		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_memory_vect() {
		this.msgid = MAVLINK_MSG_ID_MEMORY_VECT;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_memory_vect(int address, short ver, short type, byte[] value) {
		this.msgid = MAVLINK_MSG_ID_MEMORY_VECT;

		this.address = address;
		this.ver = ver;
		this.type = type;
		this.value = value;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_memory_vect(int address, short ver, short type, byte[] value, int sysid, int compid, boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_MEMORY_VECT;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.address = address;
		this.ver = ver;
		this.type = type;
		this.value = value;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_memory_vect(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_MEMORY_VECT;

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
		return "MAVLINK_MSG_ID_MEMORY_VECT - sysid:" + sysid + " compid:" + compid + " address:" + address + " ver:" + ver + " type:" + type + " value:" + value + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_MEMORY_VECT";
	}
}
