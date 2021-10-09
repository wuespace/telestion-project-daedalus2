/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE GPS_INJECT_DATA PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Data for injecting into the onboard GPS (used for DGPS)
 */
public class msg_gps_inject_data extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_GPS_INJECT_DATA = 123;
	public static final int MAVLINK_MSG_LENGTH = 113;
	private static final long serialVersionUID = MAVLINK_MSG_ID_GPS_INJECT_DATA;


	/**
	 * System ID
	 */
	public short target_system;

	/**
	 * Component ID
	 */
	public short target_component;

	/**
	 * Data length
	 */
	public short len;

	/**
	 * Raw data (110 is enough for 12 satellites of RTCMv2)
	 */
	public short[] data = new short[110];


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
		packet.msgid = MAVLINK_MSG_ID_GPS_INJECT_DATA;

		packet.payload.putUnsignedByte(target_system);
		packet.payload.putUnsignedByte(target_component);
		packet.payload.putUnsignedByte(len);

		for (int i = 0; i < data.length; i++) {
			packet.payload.putUnsignedByte(data[i]);
		}


		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a gps_inject_data message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.target_system = payload.getUnsignedByte();
		this.target_component = payload.getUnsignedByte();
		this.len = payload.getUnsignedByte();

		for (int i = 0; i < this.data.length; i++) {
			this.data[i] = payload.getUnsignedByte();
		}


		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_gps_inject_data() {
		this.msgid = MAVLINK_MSG_ID_GPS_INJECT_DATA;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_gps_inject_data(short target_system, short target_component, short len, short[] data) {
		this.msgid = MAVLINK_MSG_ID_GPS_INJECT_DATA;

		this.target_system = target_system;
		this.target_component = target_component;
		this.len = len;
		this.data = data;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_gps_inject_data(short target_system,
			short target_component,
			short len,
			short[] data,
			int sysid,
			int compid,
			boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_GPS_INJECT_DATA;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.target_system = target_system;
		this.target_component = target_component;
		this.len = len;
		this.data = data;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_gps_inject_data(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_GPS_INJECT_DATA;

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
		return "MAVLINK_MSG_ID_GPS_INJECT_DATA - sysid:" + sysid + " compid:" + compid + " target_system:" + target_system + " target_component:" + target_component + " len:" + len + " data:" + data + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_GPS_INJECT_DATA";
	}
}
