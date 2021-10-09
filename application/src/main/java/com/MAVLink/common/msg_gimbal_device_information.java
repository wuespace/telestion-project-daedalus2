/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE GIMBAL_DEVICE_INFORMATION PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Information about a low level gimbal. This message should be requested by the gimbal manager or a ground station
 * using MAV_CMD_REQUEST_MESSAGE. The maximum angles and rates are the limits by hardware. However, the limits by
 * software used are likely different/smaller and dependent on mode/settings/etc..
 */
public class msg_gimbal_device_information extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION = 283;
	public static final int MAVLINK_MSG_LENGTH = 144;
	private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION;


	/**
	 * UID of gimbal hardware (0 if unknown).
	 */
	public long uid;

	/**
	 * Timestamp (time since system boot).
	 */
	public long time_boot_ms;

	/**
	 * Version of the gimbal firmware, encoded as: (Dev & 0xff) << 24 | (Patch & 0xff) << 16 | (Minor & 0xff) << 8 |
	 * (Major & 0xff).
	 */
	public long firmware_version;

	/**
	 * Version of the gimbal hardware, encoded as: (Dev & 0xff) << 24 | (Patch & 0xff) << 16 | (Minor & 0xff) << 8 |
	 * (Major & 0xff).
	 */
	public long hardware_version;

	/**
	 * Minimum hardware roll angle (positive: rolling to the right, negative: rolling to the left)
	 */
	public float roll_min;

	/**
	 * Maximum hardware roll angle (positive: rolling to the right, negative: rolling to the left)
	 */
	public float roll_max;

	/**
	 * Minimum hardware pitch angle (positive: up, negative: down)
	 */
	public float pitch_min;

	/**
	 * Maximum hardware pitch angle (positive: up, negative: down)
	 */
	public float pitch_max;

	/**
	 * Minimum hardware yaw angle (positive: to the right, negative: to the left)
	 */
	public float yaw_min;

	/**
	 * Maximum hardware yaw angle (positive: to the right, negative: to the left)
	 */
	public float yaw_max;

	/**
	 * Bitmap of gimbal capability flags.
	 */
	public int cap_flags;

	/**
	 * Bitmap for use for gimbal-specific capability flags.
	 */
	public int custom_cap_flags;

	/**
	 * Name of the gimbal vendor.
	 */
	public byte[] vendor_name = new byte[32];

	/**
	 * Name of the gimbal model.
	 */
	public byte[] model_name = new byte[32];

	/**
	 * Custom name of the gimbal given to it by the user.
	 */
	public byte[] custom_name = new byte[32];


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
		packet.msgid = MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION;

		packet.payload.putUnsignedLong(uid);
		packet.payload.putUnsignedInt(time_boot_ms);
		packet.payload.putUnsignedInt(firmware_version);
		packet.payload.putUnsignedInt(hardware_version);
		packet.payload.putFloat(roll_min);
		packet.payload.putFloat(roll_max);
		packet.payload.putFloat(pitch_min);
		packet.payload.putFloat(pitch_max);
		packet.payload.putFloat(yaw_min);
		packet.payload.putFloat(yaw_max);
		packet.payload.putUnsignedShort(cap_flags);
		packet.payload.putUnsignedShort(custom_cap_flags);

		for (int i = 0; i < vendor_name.length; i++) {
			packet.payload.putByte(vendor_name[i]);
		}


		for (int i = 0; i < model_name.length; i++) {
			packet.payload.putByte(model_name[i]);
		}


		for (int i = 0; i < custom_name.length; i++) {
			packet.payload.putByte(custom_name[i]);
		}


		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a gimbal_device_information message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.uid = payload.getUnsignedLong();
		this.time_boot_ms = payload.getUnsignedInt();
		this.firmware_version = payload.getUnsignedInt();
		this.hardware_version = payload.getUnsignedInt();
		this.roll_min = payload.getFloat();
		this.roll_max = payload.getFloat();
		this.pitch_min = payload.getFloat();
		this.pitch_max = payload.getFloat();
		this.yaw_min = payload.getFloat();
		this.yaw_max = payload.getFloat();
		this.cap_flags = payload.getUnsignedShort();
		this.custom_cap_flags = payload.getUnsignedShort();

		for (int i = 0; i < this.vendor_name.length; i++) {
			this.vendor_name[i] = payload.getByte();
		}


		for (int i = 0; i < this.model_name.length; i++) {
			this.model_name[i] = payload.getByte();
		}


		for (int i = 0; i < this.custom_name.length; i++) {
			this.custom_name[i] = payload.getByte();
		}


		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_gimbal_device_information() {
		this.msgid = MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_gimbal_device_information(long uid,
			long time_boot_ms,
			long firmware_version,
			long hardware_version,
			float roll_min,
			float roll_max,
			float pitch_min,
			float pitch_max,
			float yaw_min,
			float yaw_max,
			int cap_flags,
			int custom_cap_flags,
			byte[] vendor_name,
			byte[] model_name,
			byte[] custom_name) {
		this.msgid = MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION;

		this.uid = uid;
		this.time_boot_ms = time_boot_ms;
		this.firmware_version = firmware_version;
		this.hardware_version = hardware_version;
		this.roll_min = roll_min;
		this.roll_max = roll_max;
		this.pitch_min = pitch_min;
		this.pitch_max = pitch_max;
		this.yaw_min = yaw_min;
		this.yaw_max = yaw_max;
		this.cap_flags = cap_flags;
		this.custom_cap_flags = custom_cap_flags;
		this.vendor_name = vendor_name;
		this.model_name = model_name;
		this.custom_name = custom_name;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_gimbal_device_information(long uid,
			long time_boot_ms,
			long firmware_version,
			long hardware_version,
			float roll_min,
			float roll_max,
			float pitch_min,
			float pitch_max,
			float yaw_min,
			float yaw_max,
			int cap_flags,
			int custom_cap_flags,
			byte[] vendor_name,
			byte[] model_name,
			byte[] custom_name,
			int sysid,
			int compid,
			boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.uid = uid;
		this.time_boot_ms = time_boot_ms;
		this.firmware_version = firmware_version;
		this.hardware_version = hardware_version;
		this.roll_min = roll_min;
		this.roll_max = roll_max;
		this.pitch_min = pitch_min;
		this.pitch_max = pitch_max;
		this.yaw_min = yaw_min;
		this.yaw_max = yaw_max;
		this.cap_flags = cap_flags;
		this.custom_cap_flags = custom_cap_flags;
		this.vendor_name = vendor_name;
		this.model_name = model_name;
		this.custom_name = custom_name;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_gimbal_device_information(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION;

		this.sysid = mavLinkPacket.sysid;
		this.compid = mavLinkPacket.compid;
		this.isMavlink2 = mavLinkPacket.isMavlink2;
		unpack(mavLinkPacket.payload);
	}


	/**
	 * Sets the buffer of this message with a string, adds the necessary padding
	 */
	public void setVendor_Name(String str) {
		int len = Math.min(str.length(), 32);
		for (int i = 0; i < len; i++) {
			vendor_name[i] = (byte) str.charAt(i);
		}

		for (int i = len; i < 32; i++) {            // padding for the rest of the buffer
			vendor_name[i] = 0;
		}
	}

	/**
	 * Gets the message, formatted as a string
	 */
	public String getVendor_Name() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 32; i++) {
			if (vendor_name[i] != 0)
				buf.append((char) vendor_name[i]);
			else
				break;
		}
		return buf.toString();

	}

	/**
	 * Sets the buffer of this message with a string, adds the necessary padding
	 */
	public void setModel_Name(String str) {
		int len = Math.min(str.length(), 32);
		for (int i = 0; i < len; i++) {
			model_name[i] = (byte) str.charAt(i);
		}

		for (int i = len; i < 32; i++) {            // padding for the rest of the buffer
			model_name[i] = 0;
		}
	}

	/**
	 * Gets the message, formatted as a string
	 */
	public String getModel_Name() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 32; i++) {
			if (model_name[i] != 0)
				buf.append((char) model_name[i]);
			else
				break;
		}
		return buf.toString();

	}

	/**
	 * Sets the buffer of this message with a string, adds the necessary padding
	 */
	public void setCustom_Name(String str) {
		int len = Math.min(str.length(), 32);
		for (int i = 0; i < len; i++) {
			custom_name[i] = (byte) str.charAt(i);
		}

		for (int i = len; i < 32; i++) {            // padding for the rest of the buffer
			custom_name[i] = 0;
		}
	}

	/**
	 * Gets the message, formatted as a string
	 */
	public String getCustom_Name() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 32; i++) {
			if (custom_name[i] != 0)
				buf.append((char) custom_name[i]);
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
		return "MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION - sysid:" + sysid + " compid:" + compid + " uid:" + uid + " " +
				"time_boot_ms:" + time_boot_ms + " firmware_version:" + firmware_version + " hardware_version:" + hardware_version + " roll_min:" + roll_min + " roll_max:" + roll_max + " pitch_min:" + pitch_min + " pitch_max:" + pitch_max + " yaw_min:" + yaw_min + " yaw_max:" + yaw_max + " cap_flags:" + cap_flags + " custom_cap_flags:" + custom_cap_flags + " vendor_name:" + vendor_name + " model_name:" + model_name + " custom_name:" + custom_name + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_GIMBAL_DEVICE_INFORMATION";
	}
}
