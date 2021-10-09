/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE AUTH_KEY PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Emit an encrypted signature / key identifying this system. PLEASE NOTE: This protocol has been kept simple, so
 * transmitting the key requires an encrypted channel for true safety.
 */
public class msg_auth_key extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_AUTH_KEY = 7;
	public static final int MAVLINK_MSG_LENGTH = 32;
	private static final long serialVersionUID = MAVLINK_MSG_ID_AUTH_KEY;


	/**
	 * key
	 */
	public byte[] key = new byte[32];


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
		packet.msgid = MAVLINK_MSG_ID_AUTH_KEY;


		for (int i = 0; i < key.length; i++) {
			packet.payload.putByte(key[i]);
		}


		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a auth_key message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();


		for (int i = 0; i < this.key.length; i++) {
			this.key[i] = payload.getByte();
		}


		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_auth_key() {
		this.msgid = MAVLINK_MSG_ID_AUTH_KEY;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_auth_key(byte[] key) {
		this.msgid = MAVLINK_MSG_ID_AUTH_KEY;

		this.key = key;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_auth_key(byte[] key, int sysid, int compid, boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_AUTH_KEY;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.key = key;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_auth_key(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_AUTH_KEY;

		this.sysid = mavLinkPacket.sysid;
		this.compid = mavLinkPacket.compid;
		this.isMavlink2 = mavLinkPacket.isMavlink2;
		unpack(mavLinkPacket.payload);
	}


	/**
	 * Sets the buffer of this message with a string, adds the necessary padding
	 */
	public void setKey(String str) {
		int len = Math.min(str.length(), 32);
		for (int i = 0; i < len; i++) {
			key[i] = (byte) str.charAt(i);
		}

		for (int i = len; i < 32; i++) {            // padding for the rest of the buffer
			key[i] = 0;
		}
	}

	/**
	 * Gets the message, formatted as a string
	 */
	public String getKey() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 32; i++) {
			if (key[i] != 0)
				buf.append((char) key[i]);
			else
				break;
		}
		return buf.toString();

	}

	/**
	 * Returns a string with the MSG name and data
	 */
	@Override
	public String toString() {
		return "MAVLINK_MSG_ID_AUTH_KEY - sysid:" + sysid + " compid:" + compid + " key:" + key + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_AUTH_KEY";
	}
}
