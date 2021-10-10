/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE EJECTOR_LOG PACKING
package com.MAVLink.daedalus;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Contains log data of the Ejector
 */
public class msg_ejector_log extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_EJECTOR_LOG = 10008;
	public static final int MAVLINK_MSG_LENGTH = 255;
	private static final long serialVersionUID = MAVLINK_MSG_ID_EJECTOR_LOG;


	/**
	 * Ejector local time
	 */
	public long time_local;

	/**
	 * Logging data
	 */
	public byte[] log_msg = new byte[247];


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
		packet.msgid = MAVLINK_MSG_ID_EJECTOR_LOG;

		packet.payload.putLong(time_local);

		for (int i = 0; i < log_msg.length; i++) {
			packet.payload.putByte(log_msg[i]);
		}


		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a ejector_log message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.time_local = payload.getLong();

		for (int i = 0; i < this.log_msg.length; i++) {
			this.log_msg[i] = payload.getByte();
		}


		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_ejector_log() {
		this.msgid = MAVLINK_MSG_ID_EJECTOR_LOG;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_ejector_log(long time_local, byte[] log_msg) {
		this.msgid = MAVLINK_MSG_ID_EJECTOR_LOG;

		this.time_local = time_local;
		this.log_msg = log_msg;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_ejector_log(long time_local, byte[] log_msg, int sysid, int compid, boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_EJECTOR_LOG;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.time_local = time_local;
		this.log_msg = log_msg;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_ejector_log(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_EJECTOR_LOG;

		this.sysid = mavLinkPacket.sysid;
		this.compid = mavLinkPacket.compid;
		this.isMavlink2 = mavLinkPacket.isMavlink2;
		unpack(mavLinkPacket.payload);
	}


	/**
	 * Sets the buffer of this message with a string, adds the necessary padding
	 */
	public void setLog_Msg(String str) {
		int len = Math.min(str.length(), 247);
		for (int i = 0; i < len; i++) {
			log_msg[i] = (byte) str.charAt(i);
		}

		for (int i = len; i < 247; i++) {            // padding for the rest of the buffer
			log_msg[i] = 0;
		}
	}

	/**
	 * Gets the message, formatted as a string
	 */
	public String getLog_Msg() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 247; i++) {
			if (log_msg[i] != 0)
				buf.append((char) log_msg[i]);
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
		return "MAVLINK_MSG_ID_EJECTOR_LOG - sysid:" + sysid + " compid:" + compid + " time_local:" + time_local + " " +
				"log_msg:" + log_msg + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_EJECTOR_LOG";
	}
}