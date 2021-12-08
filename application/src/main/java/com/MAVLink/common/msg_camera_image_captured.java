/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE CAMERA_IMAGE_CAPTURED PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Information about a captured image. This is emitted every time a message is captured.
 * MAV_CMD_REQUEST_MESSAGE can be used to (re)request this message for a specific sequence number or range of sequence numbers:
 * MAV_CMD_REQUEST_MESSAGE.param2 indicates the sequence number the first image to send, or set to -1 to send the message for all sequence numbers.
 * MAV_CMD_REQUEST_MESSAGE.param3 is used to specify a range of messages to send:
 * set to 0 (default) to send just the the message for the sequence number in param 2,
 * set to -1 to send the message for the sequence number in param 2 and all the following sequence numbers,
 * set to the sequence number of the final message in the range.
 */
public class msg_camera_image_captured extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_CAMERA_IMAGE_CAPTURED = 263;
	public static final int MAVLINK_MSG_LENGTH = 255;
	private static final long serialVersionUID = MAVLINK_MSG_ID_CAMERA_IMAGE_CAPTURED;


	/**
	 * Timestamp (time since UNIX epoch) in UTC. 0 for unknown.
	 */
	public long time_utc;

	/**
	 * Timestamp (time since system boot).
	 */
	public long time_boot_ms;

	/**
	 * Latitude where image was taken
	 */
	public int lat;

	/**
	 * Longitude where capture was taken
	 */
	public int lon;

	/**
	 * Altitude (MSL) where image was taken
	 */
	public int alt;

	/**
	 * Altitude above ground
	 */
	public int relative_alt;

	/**
	 * Quaternion of camera orientation (w, x, y, z order, zero-rotation is 1, 0, 0, 0)
	 */
	public float q[] = new float[4];

	/**
	 * Zero based index of this image (i.e. a new image will have index CAMERA_CAPTURE_STATUS.image count -1)
	 */
	public int image_index;

	/**
	 * Deprecated/unused. Component IDs are used to differentiate multiple cameras.
	 */
	public short camera_id;

	/**
	 * Boolean indicating success (1) or failure (0) while capturing this image.
	 */
	public byte capture_result;

	/**
	 * URL of image taken. Either local storage or http://foo.jpg if camera provides an HTTP interface.
	 */
	public byte file_url[] = new byte[205];


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
		packet.msgid = MAVLINK_MSG_ID_CAMERA_IMAGE_CAPTURED;

		packet.payload.putUnsignedLong(time_utc);
		packet.payload.putUnsignedInt(time_boot_ms);
		packet.payload.putInt(lat);
		packet.payload.putInt(lon);
		packet.payload.putInt(alt);
		packet.payload.putInt(relative_alt);

		for (int i = 0; i < q.length; i++) {
			packet.payload.putFloat(q[i]);
		}

		packet.payload.putInt(image_index);
		packet.payload.putUnsignedByte(camera_id);
		packet.payload.putByte(capture_result);

		for (int i = 0; i < file_url.length; i++) {
			packet.payload.putByte(file_url[i]);
		}


		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a camera_image_captured message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.time_utc = payload.getUnsignedLong();
		this.time_boot_ms = payload.getUnsignedInt();
		this.lat = payload.getInt();
		this.lon = payload.getInt();
		this.alt = payload.getInt();
		this.relative_alt = payload.getInt();

		for (int i = 0; i < this.q.length; i++) {
			this.q[i] = payload.getFloat();
		}

		this.image_index = payload.getInt();
		this.camera_id = payload.getUnsignedByte();
		this.capture_result = payload.getByte();

		for (int i = 0; i < this.file_url.length; i++) {
			this.file_url[i] = payload.getByte();
		}


		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_camera_image_captured() {
		this.msgid = MAVLINK_MSG_ID_CAMERA_IMAGE_CAPTURED;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_camera_image_captured(long time_utc, long time_boot_ms, int lat, int lon, int alt, int relative_alt, float[] q, int image_index, short camera_id, byte capture_result, byte[] file_url) {
		this.msgid = MAVLINK_MSG_ID_CAMERA_IMAGE_CAPTURED;

		this.time_utc = time_utc;
		this.time_boot_ms = time_boot_ms;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.relative_alt = relative_alt;
		this.q = q;
		this.image_index = image_index;
		this.camera_id = camera_id;
		this.capture_result = capture_result;
		this.file_url = file_url;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_camera_image_captured(long time_utc, long time_boot_ms, int lat, int lon, int alt, int relative_alt, float[] q, int image_index, short camera_id, byte capture_result, byte[] file_url, int sysid, int compid, boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_CAMERA_IMAGE_CAPTURED;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.time_utc = time_utc;
		this.time_boot_ms = time_boot_ms;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.relative_alt = relative_alt;
		this.q = q;
		this.image_index = image_index;
		this.camera_id = camera_id;
		this.capture_result = capture_result;
		this.file_url = file_url;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_camera_image_captured(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_CAMERA_IMAGE_CAPTURED;

		this.sysid = mavLinkPacket.sysid;
		this.compid = mavLinkPacket.compid;
		this.isMavlink2 = mavLinkPacket.isMavlink2;
		unpack(mavLinkPacket.payload);
	}


	/**
	 * Sets the buffer of this message with a string, adds the necessary padding
	 */
	public void setFile_Url(String str) {
		int len = Math.min(str.length(), 205);
		for (int i = 0; i < len; i++) {
			file_url[i] = (byte) str.charAt(i);
		}

		for (int i = len; i < 205; i++) {            // padding for the rest of the buffer
			file_url[i] = 0;
		}
	}

	/**
	 * Gets the message, formated as a string
	 */
	public String getFile_Url() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 205; i++) {
			if (file_url[i] != 0)
				buf.append((char) file_url[i]);
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
		return "MAVLINK_MSG_ID_CAMERA_IMAGE_CAPTURED - sysid:" + sysid + " compid:" + compid + " time_utc:" + time_utc + " time_boot_ms:" + time_boot_ms + " lat:" + lat + " lon:" + lon + " alt:" + alt + " relative_alt:" + relative_alt + " q:" + q + " image_index:" + image_index + " camera_id:" + camera_id + " capture_result:" + capture_result + " file_url:" + file_url + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_CAMERA_IMAGE_CAPTURED";
	}
}
