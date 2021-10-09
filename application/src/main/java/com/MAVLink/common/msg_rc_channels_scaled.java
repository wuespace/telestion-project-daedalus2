/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE RC_CHANNELS_SCALED PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * The scaled values of the RC channels received: (-100%) -10000, (0%) 0, (100%) 10000. Channels that are inactive
 * should be set to UINT16_MAX.
 */
public class msg_rc_channels_scaled extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_RC_CHANNELS_SCALED = 34;
	public static final int MAVLINK_MSG_LENGTH = 22;
	private static final long serialVersionUID = MAVLINK_MSG_ID_RC_CHANNELS_SCALED;


	/**
	 * Timestamp (time since system boot).
	 */
	public long time_boot_ms;

	/**
	 * RC channel 1 value scaled.
	 */
	public short chan1_scaled;

	/**
	 * RC channel 2 value scaled.
	 */
	public short chan2_scaled;

	/**
	 * RC channel 3 value scaled.
	 */
	public short chan3_scaled;

	/**
	 * RC channel 4 value scaled.
	 */
	public short chan4_scaled;

	/**
	 * RC channel 5 value scaled.
	 */
	public short chan5_scaled;

	/**
	 * RC channel 6 value scaled.
	 */
	public short chan6_scaled;

	/**
	 * RC channel 7 value scaled.
	 */
	public short chan7_scaled;

	/**
	 * RC channel 8 value scaled.
	 */
	public short chan8_scaled;

	/**
	 * Servo output port (set of 8 outputs = 1 port). Flight stacks running on Pixhawk should use: 0 = MAIN, 1 = AUX.
	 */
	public short port;

	/**
	 * Receive signal strength indicator in device-dependent units/scale. Values: [0-254], UINT8_MAX: invalid/unknown.
	 */
	public short rssi;


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
		packet.msgid = MAVLINK_MSG_ID_RC_CHANNELS_SCALED;

		packet.payload.putUnsignedInt(time_boot_ms);
		packet.payload.putShort(chan1_scaled);
		packet.payload.putShort(chan2_scaled);
		packet.payload.putShort(chan3_scaled);
		packet.payload.putShort(chan4_scaled);
		packet.payload.putShort(chan5_scaled);
		packet.payload.putShort(chan6_scaled);
		packet.payload.putShort(chan7_scaled);
		packet.payload.putShort(chan8_scaled);
		packet.payload.putUnsignedByte(port);
		packet.payload.putUnsignedByte(rssi);

		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a rc_channels_scaled message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.time_boot_ms = payload.getUnsignedInt();
		this.chan1_scaled = payload.getShort();
		this.chan2_scaled = payload.getShort();
		this.chan3_scaled = payload.getShort();
		this.chan4_scaled = payload.getShort();
		this.chan5_scaled = payload.getShort();
		this.chan6_scaled = payload.getShort();
		this.chan7_scaled = payload.getShort();
		this.chan8_scaled = payload.getShort();
		this.port = payload.getUnsignedByte();
		this.rssi = payload.getUnsignedByte();

		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_rc_channels_scaled() {
		this.msgid = MAVLINK_MSG_ID_RC_CHANNELS_SCALED;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_rc_channels_scaled(long time_boot_ms,
			short chan1_scaled,
			short chan2_scaled,
			short chan3_scaled,
			short chan4_scaled,
			short chan5_scaled,
			short chan6_scaled,
			short chan7_scaled,
			short chan8_scaled,
			short port,
			short rssi) {
		this.msgid = MAVLINK_MSG_ID_RC_CHANNELS_SCALED;

		this.time_boot_ms = time_boot_ms;
		this.chan1_scaled = chan1_scaled;
		this.chan2_scaled = chan2_scaled;
		this.chan3_scaled = chan3_scaled;
		this.chan4_scaled = chan4_scaled;
		this.chan5_scaled = chan5_scaled;
		this.chan6_scaled = chan6_scaled;
		this.chan7_scaled = chan7_scaled;
		this.chan8_scaled = chan8_scaled;
		this.port = port;
		this.rssi = rssi;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_rc_channels_scaled(long time_boot_ms,
			short chan1_scaled,
			short chan2_scaled,
			short chan3_scaled,
			short chan4_scaled,
			short chan5_scaled,
			short chan6_scaled,
			short chan7_scaled,
			short chan8_scaled,
			short port,
			short rssi,
			int sysid,
			int compid,
			boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_RC_CHANNELS_SCALED;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.time_boot_ms = time_boot_ms;
		this.chan1_scaled = chan1_scaled;
		this.chan2_scaled = chan2_scaled;
		this.chan3_scaled = chan3_scaled;
		this.chan4_scaled = chan4_scaled;
		this.chan5_scaled = chan5_scaled;
		this.chan6_scaled = chan6_scaled;
		this.chan7_scaled = chan7_scaled;
		this.chan8_scaled = chan8_scaled;
		this.port = port;
		this.rssi = rssi;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_rc_channels_scaled(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_RC_CHANNELS_SCALED;

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
		return "MAVLINK_MSG_ID_RC_CHANNELS_SCALED - sysid:" + sysid + " compid:" + compid + " time_boot_ms:" + time_boot_ms + " chan1_scaled:" + chan1_scaled + " chan2_scaled:" + chan2_scaled + " chan3_scaled:" + chan3_scaled + " chan4_scaled:" + chan4_scaled + " chan5_scaled:" + chan5_scaled + " chan6_scaled:" + chan6_scaled + " chan7_scaled:" + chan7_scaled + " chan8_scaled:" + chan8_scaled + " port:" + port + " rssi:" + rssi + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_RC_CHANNELS_SCALED";
	}
}
