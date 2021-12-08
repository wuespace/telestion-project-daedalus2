/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE COMMAND_ACK PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Report status of a command. Includes feedback whether the command was executed. The command microservice is documented at https://mavlink.io/en/services/command.html
 */
public class msg_command_ack extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_COMMAND_ACK = 77;
	public static final int MAVLINK_MSG_LENGTH = 10;
	private static final long serialVersionUID = MAVLINK_MSG_ID_COMMAND_ACK;


	/**
	 * Command ID (of acknowledged command).
	 */
	public int command;

	/**
	 * Result of command.
	 */
	public short result;

	/**
	 * Also used as result_param1, it can be set with an enum containing the errors reasons of why the command was denied, or the progress percentage when result is MAV_RESULT_IN_PROGRESS (UINT8_MAX if the progress is unknown).
	 */
	public short progress;

	/**
	 * Additional parameter of the result, example: which parameter of MAV_CMD_NAV_WAYPOINT caused it to be denied.
	 */
	public int result_param2;

	/**
	 * System ID of the target recipient. This is the ID of the system that sent the command for which this COMMAND_ACK is an acknowledgement.
	 */
	public short target_system;

	/**
	 * Component ID of the target recipient. This is the ID of the system that sent the command for which this COMMAND_ACK is an acknowledgement.
	 */
	public short target_component;


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
		packet.msgid = MAVLINK_MSG_ID_COMMAND_ACK;

		packet.payload.putUnsignedShort(command);
		packet.payload.putUnsignedByte(result);

		if (isMavlink2) {
			packet.payload.putUnsignedByte(progress);
			packet.payload.putInt(result_param2);
			packet.payload.putUnsignedByte(target_system);
			packet.payload.putUnsignedByte(target_component);

		}
		return packet;
	}

	/**
	 * Decode a command_ack message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.command = payload.getUnsignedShort();
		this.result = payload.getUnsignedByte();

		if (isMavlink2) {
			this.progress = payload.getUnsignedByte();
			this.result_param2 = payload.getInt();
			this.target_system = payload.getUnsignedByte();
			this.target_component = payload.getUnsignedByte();

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_command_ack() {
		this.msgid = MAVLINK_MSG_ID_COMMAND_ACK;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_command_ack(int command, short result, short progress, int result_param2, short target_system, short target_component) {
		this.msgid = MAVLINK_MSG_ID_COMMAND_ACK;

		this.command = command;
		this.result = result;
		this.progress = progress;
		this.result_param2 = result_param2;
		this.target_system = target_system;
		this.target_component = target_component;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_command_ack(int command, short result, short progress, int result_param2, short target_system, short target_component, int sysid, int compid, boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_COMMAND_ACK;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.command = command;
		this.result = result;
		this.progress = progress;
		this.result_param2 = result_param2;
		this.target_system = target_system;
		this.target_component = target_component;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_command_ack(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_COMMAND_ACK;

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
		return "MAVLINK_MSG_ID_COMMAND_ACK - sysid:" + sysid + " compid:" + compid + " command:" + command + " result:" + result + " progress:" + progress + " result_param2:" + result_param2 + " target_system:" + target_system + " target_component:" + target_component + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_COMMAND_ACK";
	}
}
