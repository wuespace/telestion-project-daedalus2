/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE SEED_HEARTBEAT PACKING
package com.MAVLink.daedalus;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Contains information about the current state and local time.
 */
public class msg_seed_heartbeat extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_SEED_HEARTBEAT = 39205;
	public static final int MAVLINK_MSG_LENGTH = 54;
	private static final long serialVersionUID = MAVLINK_MSG_ID_SEED_HEARTBEAT;


	/**
	 * Seed local time
	 */
	public long time_local;

	/**
	 * system time
	 */
	public long d2time;

	/**
	 * angular velocity around z-axis
	 */
	public float imu_gyro_z;

	/**
	 * adc measurements millivolts/milliamps
	 */
	public int[] adc_measurements_sbc = new int[8];

	/**
	 * adc measurements millivolts/milliamps
	 */
	public int[] adc_measurements_cop = new int[8];

	/**
	 * number of received and executed telecommands
	 */
	public short telecommand_cnt;

	/**
	 * current System state
	 */
	public short state_cur;

	/**
	 * Is the LIDAR Hole currently open?
	 */
	public short lidar_cover_open;

	/**
	 * truthy if heater fault occurred
	 */
	public short bat_heater_fault;

	/**
	 * imu_gyro_avail, bat_temp_avail, rxsm_voltage_avail in this order with individual size of 1 bit
	 */
	public short available_status;

	/**
	 * rxsm_allowed, bat1_allowed and bat2_allowed, rxsm_used, bat1_used and bat2_used in this order with individual
	 * size of 1 bit.
	 */
	public short bat_status;


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
		packet.msgid = MAVLINK_MSG_ID_SEED_HEARTBEAT;

		packet.payload.putLong(time_local);
		packet.payload.putUnsignedInt(d2time);
		packet.payload.putFloat(imu_gyro_z);

		for (int i = 0; i < adc_measurements_sbc.length; i++) {
			packet.payload.putUnsignedShort(adc_measurements_sbc[i]);
		}


		for (int i = 0; i < adc_measurements_cop.length; i++) {
			packet.payload.putUnsignedShort(adc_measurements_cop[i]);
		}

		packet.payload.putUnsignedByte(telecommand_cnt);
		packet.payload.putUnsignedByte(state_cur);
		packet.payload.putUnsignedByte(lidar_cover_open);
		packet.payload.putUnsignedByte(bat_heater_fault);
		packet.payload.putUnsignedByte(available_status);
		packet.payload.putUnsignedByte(bat_status);

		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a seed_heartbeat message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.time_local = payload.getLong();
		this.d2time = payload.getUnsignedInt();
		this.imu_gyro_z = payload.getFloat();

		for (int i = 0; i < this.adc_measurements_sbc.length; i++) {
			this.adc_measurements_sbc[i] = payload.getUnsignedShort();
		}


		for (int i = 0; i < this.adc_measurements_cop.length; i++) {
			this.adc_measurements_cop[i] = payload.getUnsignedShort();
		}

		this.telecommand_cnt = payload.getUnsignedByte();
		this.state_cur = payload.getUnsignedByte();
		this.lidar_cover_open = payload.getUnsignedByte();
		this.bat_heater_fault = payload.getUnsignedByte();
		this.available_status = payload.getUnsignedByte();
		this.bat_status = payload.getUnsignedByte();

		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_seed_heartbeat() {
		this.msgid = MAVLINK_MSG_ID_SEED_HEARTBEAT;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_seed_heartbeat(long time_local,
			long d2time,
			float imu_gyro_z,
			int[] adc_measurements_sbc,
			int[] adc_measurements_cop,
			short telecommand_cnt,
			short state_cur,
			short lidar_cover_open,
			short bat_heater_fault,
			short available_status,
			short bat_status) {
		this.msgid = MAVLINK_MSG_ID_SEED_HEARTBEAT;

		this.time_local = time_local;
		this.d2time = d2time;
		this.imu_gyro_z = imu_gyro_z;
		this.adc_measurements_sbc = adc_measurements_sbc;
		this.adc_measurements_cop = adc_measurements_cop;
		this.telecommand_cnt = telecommand_cnt;
		this.state_cur = state_cur;
		this.lidar_cover_open = lidar_cover_open;
		this.bat_heater_fault = bat_heater_fault;
		this.available_status = available_status;
		this.bat_status = bat_status;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_seed_heartbeat(long time_local,
			long d2time,
			float imu_gyro_z,
			int[] adc_measurements_sbc,
			int[] adc_measurements_cop,
			short telecommand_cnt,
			short state_cur,
			short lidar_cover_open,
			short bat_heater_fault,
			short available_status,
			short bat_status,
			int sysid,
			int compid,
			boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_SEED_HEARTBEAT;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.time_local = time_local;
		this.d2time = d2time;
		this.imu_gyro_z = imu_gyro_z;
		this.adc_measurements_sbc = adc_measurements_sbc;
		this.adc_measurements_cop = adc_measurements_cop;
		this.telecommand_cnt = telecommand_cnt;
		this.state_cur = state_cur;
		this.lidar_cover_open = lidar_cover_open;
		this.bat_heater_fault = bat_heater_fault;
		this.available_status = available_status;
		this.bat_status = bat_status;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_seed_heartbeat(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_SEED_HEARTBEAT;

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
		return "MAVLINK_MSG_ID_SEED_HEARTBEAT - sysid:" + sysid + " compid:" + compid + " time_local:" + time_local +
				" d2time:" + d2time + " imu_gyro_z:" + imu_gyro_z + " adc_measurements_sbc:" + adc_measurements_sbc +
				" adc_measurements_cop:" + adc_measurements_cop + " telecommand_cnt:" + telecommand_cnt + " state_cur" +
				":" + state_cur + " lidar_cover_open:" + lidar_cover_open + " bat_heater_fault:" + bat_heater_fault +
				" available_status:" + available_status + " bat_status:" + bat_status + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_SEED_HEARTBEAT";
	}
}
