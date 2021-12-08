/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE VFR_HUD PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Metrics typically displayed on a HUD for fixed wing aircraft.
 */
public class msg_vfr_hud extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_VFR_HUD = 74;
	public static final int MAVLINK_MSG_LENGTH = 20;
	private static final long serialVersionUID = MAVLINK_MSG_ID_VFR_HUD;


	/**
	 * Vehicle speed in form appropriate for vehicle type. For standard aircraft this is typically calibrated airspeed (CAS) or indicated airspeed (IAS) - either of which can be used by a pilot to estimate stall speed.
	 */
	public float airspeed;

	/**
	 * Current ground speed.
	 */
	public float groundspeed;

	/**
	 * Current altitude (MSL).
	 */
	public float alt;

	/**
	 * Current climb rate.
	 */
	public float climb;

	/**
	 * Current heading in compass units (0-360, 0=north).
	 */
	public short heading;

	/**
	 * Current throttle setting (0 to 100).
	 */
	public int throttle;


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
		packet.msgid = MAVLINK_MSG_ID_VFR_HUD;

		packet.payload.putFloat(airspeed);
		packet.payload.putFloat(groundspeed);
		packet.payload.putFloat(alt);
		packet.payload.putFloat(climb);
		packet.payload.putShort(heading);
		packet.payload.putUnsignedShort(throttle);

		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a vfr_hud message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.airspeed = payload.getFloat();
		this.groundspeed = payload.getFloat();
		this.alt = payload.getFloat();
		this.climb = payload.getFloat();
		this.heading = payload.getShort();
		this.throttle = payload.getUnsignedShort();

		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_vfr_hud() {
		this.msgid = MAVLINK_MSG_ID_VFR_HUD;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_vfr_hud(float airspeed, float groundspeed, float alt, float climb, short heading, int throttle) {
		this.msgid = MAVLINK_MSG_ID_VFR_HUD;

		this.airspeed = airspeed;
		this.groundspeed = groundspeed;
		this.alt = alt;
		this.climb = climb;
		this.heading = heading;
		this.throttle = throttle;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_vfr_hud(float airspeed, float groundspeed, float alt, float climb, short heading, int throttle, int sysid, int compid, boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_VFR_HUD;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.airspeed = airspeed;
		this.groundspeed = groundspeed;
		this.alt = alt;
		this.climb = climb;
		this.heading = heading;
		this.throttle = throttle;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_vfr_hud(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_VFR_HUD;

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
		return "MAVLINK_MSG_ID_VFR_HUD - sysid:" + sysid + " compid:" + compid + " airspeed:" + airspeed + " groundspeed:" + groundspeed + " alt:" + alt + " climb:" + climb + " heading:" + heading + " throttle:" + throttle + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_VFR_HUD";
	}
}
