/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE PLAY_TUNE_V2 PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Play vehicle tone/tune (buzzer). Supersedes message PLAY_TUNE.
 */
public class msg_play_tune_v2 extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_PLAY_TUNE_V2 = 400;
	public static final int MAVLINK_MSG_LENGTH = 254;
	private static final long serialVersionUID = MAVLINK_MSG_ID_PLAY_TUNE_V2;


	/**
	 * Tune format
	 */
	public long format;

	/**
	 * System ID
	 */
	public short target_system;

	/**
	 * Component ID
	 */
	public short target_component;

	/**
	 * Tune definition as a NULL-terminated string.
	 */
	public byte[] tune = new byte[248];


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
		packet.msgid = MAVLINK_MSG_ID_PLAY_TUNE_V2;

		packet.payload.putUnsignedInt(format);
		packet.payload.putUnsignedByte(target_system);
		packet.payload.putUnsignedByte(target_component);

		for (int i = 0; i < tune.length; i++) {
			packet.payload.putByte(tune[i]);
		}


		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a play_tune_v2 message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.format = payload.getUnsignedInt();
		this.target_system = payload.getUnsignedByte();
		this.target_component = payload.getUnsignedByte();

		for (int i = 0; i < this.tune.length; i++) {
			this.tune[i] = payload.getByte();
		}


		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_play_tune_v2() {
		this.msgid = MAVLINK_MSG_ID_PLAY_TUNE_V2;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_play_tune_v2(long format, short target_system, short target_component, byte[] tune) {
		this.msgid = MAVLINK_MSG_ID_PLAY_TUNE_V2;

		this.format = format;
		this.target_system = target_system;
		this.target_component = target_component;
		this.tune = tune;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_play_tune_v2(long format,
			short target_system,
			short target_component,
			byte[] tune,
			int sysid,
			int compid,
			boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_PLAY_TUNE_V2;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.format = format;
		this.target_system = target_system;
		this.target_component = target_component;
		this.tune = tune;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_play_tune_v2(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_PLAY_TUNE_V2;

		this.sysid = mavLinkPacket.sysid;
		this.compid = mavLinkPacket.compid;
		this.isMavlink2 = mavLinkPacket.isMavlink2;
		unpack(mavLinkPacket.payload);
	}


	/**
	 * Sets the buffer of this message with a string, adds the necessary padding
	 */
	public void setTune(String str) {
		int len = Math.min(str.length(), 248);
		for (int i = 0; i < len; i++) {
			tune[i] = (byte) str.charAt(i);
		}

		for (int i = len; i < 248; i++) {            // padding for the rest of the buffer
			tune[i] = 0;
		}
	}

	/**
	 * Gets the message, formatted as a string
	 */
	public String getTune() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 248; i++) {
			if (tune[i] != 0)
				buf.append((char) tune[i]);
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
		return "MAVLINK_MSG_ID_PLAY_TUNE_V2 - sysid:" + sysid + " compid:" + compid + " format:" + format + " " +
				"target_system:" + target_system + " target_component:" + target_component + " tune:" + tune + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_PLAY_TUNE_V2";
	}
}
