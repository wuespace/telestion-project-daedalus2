/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE SET_GPS_GLOBAL_ORIGIN PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Sets the GPS co-ordinates of the vehicle local origin (0,0,0) position. Vehicle should emit GPS_GLOBAL_ORIGIN irrespective of whether the origin is changed. This enables transform between the local coordinate frame and the global (GPS) coordinate frame, which may be necessary when (for example) indoor and outdoor settings are connected and the MAV should move from in- to outdoor.
 */
public class msg_set_gps_global_origin extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN = 48;
	public static final int MAVLINK_MSG_LENGTH = 21;
	private static final long serialVersionUID = MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN;


	/**
	 * Latitude (WGS84)
	 */
	public int latitude;

	/**
	 * Longitude (WGS84)
	 */
	public int longitude;

	/**
	 * Altitude (MSL). Positive for up.
	 */
	public int altitude;

	/**
	 * System ID
	 */
	public short target_system;

	/**
	 * Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude of the number.
	 */
	public long time_usec;


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
		packet.msgid = MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN;

		packet.payload.putInt(latitude);
		packet.payload.putInt(longitude);
		packet.payload.putInt(altitude);
		packet.payload.putUnsignedByte(target_system);

		if (isMavlink2) {
			packet.payload.putUnsignedLong(time_usec);

		}
		return packet;
	}

	/**
	 * Decode a set_gps_global_origin message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.latitude = payload.getInt();
		this.longitude = payload.getInt();
		this.altitude = payload.getInt();
		this.target_system = payload.getUnsignedByte();

		if (isMavlink2) {
			this.time_usec = payload.getUnsignedLong();

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_set_gps_global_origin() {
		this.msgid = MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_set_gps_global_origin(int latitude, int longitude, int altitude, short target_system, long time_usec) {
		this.msgid = MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN;

		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.target_system = target_system;
		this.time_usec = time_usec;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_set_gps_global_origin(int latitude, int longitude, int altitude, short target_system, long time_usec, int sysid, int compid, boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.target_system = target_system;
		this.time_usec = time_usec;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_set_gps_global_origin(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN;

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
		return "MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN - sysid:" + sysid + " compid:" + compid + " latitude:" + latitude + " longitude:" + longitude + " altitude:" + altitude + " target_system:" + target_system + " time_usec:" + time_usec + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN";
	}
}
