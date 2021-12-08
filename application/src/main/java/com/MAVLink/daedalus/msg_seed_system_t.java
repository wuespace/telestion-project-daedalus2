/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE SEED_SYSTEM_T PACKING
package com.MAVLink.daedalus;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Contains the whole Seeds system_t information + local time + state.
 */
public class msg_seed_system_t extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_SEED_SYSTEM_T = 79071;
	public static final int MAVLINK_MSG_LENGTH = 142;
	private static final long serialVersionUID = MAVLINK_MSG_ID_SEED_SYSTEM_T;


	/**
	 * Seed local time
	 */
	public long time_local;

	/**
	 * system time
	 */
	public long d2time;

	/**
	 * number of the current iteration of the mainloop
	 */
	public long mainloop_itr_cnt;

	/**
	 * time the last mainloop iteration took
	 */
	public long mainloop_itr_time;

	/**
	 * acceleration along x-axis, multiple of g
	 */
	public float imu_acc_x;

	/**
	 * acceleration along y-axis, multiple of g
	 */
	public float imu_acc_y;

	/**
	 * acceleration along z-axis, multiple of g
	 */
	public float imu_acc_z;

	/**
	 * angular velocity around x-axis
	 */
	public float imu_gyro_x;

	/**
	 * angular velocity around y-axis
	 */
	public float imu_gyro_y;

	/**
	 * angular velocity around z-axis
	 */
	public float imu_gyro_z;

	/**
	 * air pressure
	 */
	public float baro_press;

	/**
	 * temperature in degrees Celsius
	 */
	public float baro_temp;

	/**
	 * air pressure
	 */
	public float vacuum_baro_press;

	/**
	 * rotation rate of the rotor in rad/s
	 */
	public float tacho_rot_rate;

	/**
	 * current latitude (N is positiv, S is negativ)
	 */
	public float gps_lat;

	/**
	 * current longitude (W is positiv, E is negativ)
	 */
	public float gps_long;

	/**
	 * horizontal dilution of precision
	 */
	public float gps_hdop;

	/**
	 * altitude above mean sea level
	 */
	public float gps_alt;

	/**
	 * vertical velocity of the seed in m/s – negative if seed is falling
	 */
	public float filter_vel_vertical;

	/**
	 * height above ground in m
	 */
	public float filter_height_ground;

	/**
	 * absolute (to air/world frame) rotation rate of the rotor in rad/s"
	 */
	public float filter_rotor_rot_rate;

	/**
	 * absolute (to air/world frame) rotation rate of the body in rad/s"
	 */
	public float fiter_body_rot_rate;

	/**
	 * setpoint for the pitch angle for the servos
	 */
	public float controller_blade_pitch;

	/**
	 * desired fin angle
	 */
	public float controller_fin_angle;

	/**
	 * adc measurements millivolts/milliamps
	 */
	public int adc_measurements_sbc[] = new int[8];

	/**
	 * adc measurements millivolts/milliamps
	 */
	public int adc_measurements_cop[] = new int[8];

	/**
	 * number of received and executed telecommands
	 */
	public short telecommand_cnt;

	/**
	 * current System state
	 */
	public short state_cur;

	/**
	 * Indicates the signal quality of Iridium
	 */
	public short iridium_RSSI;

	/**
	 * Is the LIDAR Hole currently open?
	 */
	public short lidar_cover_open;

	/**
	 * truthy if heater fault occurred
	 */
	public short bat_heater_fault;

	/**
	 * rxsm_allowed, bat1_allowed and bat2_allowed, rxsm_used, bat1_used, bat2_used and bat_heating_enabled in this order with individual size of 1 bit.
	 */
	public short bat_status;

	/**
	 * indicator for gps fix
	 */
	public short gps_quality;

	/**
	 * number of used satellites
	 */
	public short gps_satsUsed;

	/**
	 * identifier for blade controller type and finn controller type in this order with individual size of 4 bit.
	 */
	public short controller_ids;

	/**
	 * imu_acc_avail, imu_gyro_avail, baro_avail, vacuum_baro_avail, tacho_rot_avail, copAdcAvail, sbcAdcAvail in this order with individual size of 1 bit
	 */
	public short available_status;


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
		packet.msgid = MAVLINK_MSG_ID_SEED_SYSTEM_T;

		packet.payload.putLong(time_local);
		packet.payload.putUnsignedInt(d2time);
		packet.payload.putUnsignedInt(mainloop_itr_cnt);
		packet.payload.putUnsignedInt(mainloop_itr_time);
		packet.payload.putFloat(imu_acc_x);
		packet.payload.putFloat(imu_acc_y);
		packet.payload.putFloat(imu_acc_z);
		packet.payload.putFloat(imu_gyro_x);
		packet.payload.putFloat(imu_gyro_y);
		packet.payload.putFloat(imu_gyro_z);
		packet.payload.putFloat(baro_press);
		packet.payload.putFloat(baro_temp);
		packet.payload.putFloat(vacuum_baro_press);
		packet.payload.putFloat(tacho_rot_rate);
		packet.payload.putFloat(gps_lat);
		packet.payload.putFloat(gps_long);
		packet.payload.putFloat(gps_hdop);
		packet.payload.putFloat(gps_alt);
		packet.payload.putFloat(filter_vel_vertical);
		packet.payload.putFloat(filter_height_ground);
		packet.payload.putFloat(filter_rotor_rot_rate);
		packet.payload.putFloat(fiter_body_rot_rate);
		packet.payload.putFloat(controller_blade_pitch);
		packet.payload.putFloat(controller_fin_angle);

		for (int i = 0; i < adc_measurements_sbc.length; i++) {
			packet.payload.putUnsignedShort(adc_measurements_sbc[i]);
		}


		for (int i = 0; i < adc_measurements_cop.length; i++) {
			packet.payload.putUnsignedShort(adc_measurements_cop[i]);
		}

		packet.payload.putUnsignedByte(telecommand_cnt);
		packet.payload.putUnsignedByte(state_cur);
		packet.payload.putUnsignedByte(iridium_RSSI);
		packet.payload.putUnsignedByte(lidar_cover_open);
		packet.payload.putUnsignedByte(bat_heater_fault);
		packet.payload.putUnsignedByte(bat_status);
		packet.payload.putUnsignedByte(gps_quality);
		packet.payload.putUnsignedByte(gps_satsUsed);
		packet.payload.putUnsignedByte(controller_ids);
		packet.payload.putUnsignedByte(available_status);

		if (isMavlink2) {

		}
		return packet;
	}

	/**
	 * Decode a seed_system_t message into this class fields
	 *
	 * @param payload The message to decode
	 */
	@Override
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();

		this.time_local = payload.getLong();
		this.d2time = payload.getUnsignedInt();
		this.mainloop_itr_cnt = payload.getUnsignedInt();
		this.mainloop_itr_time = payload.getUnsignedInt();
		this.imu_acc_x = payload.getFloat();
		this.imu_acc_y = payload.getFloat();
		this.imu_acc_z = payload.getFloat();
		this.imu_gyro_x = payload.getFloat();
		this.imu_gyro_y = payload.getFloat();
		this.imu_gyro_z = payload.getFloat();
		this.baro_press = payload.getFloat();
		this.baro_temp = payload.getFloat();
		this.vacuum_baro_press = payload.getFloat();
		this.tacho_rot_rate = payload.getFloat();
		this.gps_lat = payload.getFloat();
		this.gps_long = payload.getFloat();
		this.gps_hdop = payload.getFloat();
		this.gps_alt = payload.getFloat();
		this.filter_vel_vertical = payload.getFloat();
		this.filter_height_ground = payload.getFloat();
		this.filter_rotor_rot_rate = payload.getFloat();
		this.fiter_body_rot_rate = payload.getFloat();
		this.controller_blade_pitch = payload.getFloat();
		this.controller_fin_angle = payload.getFloat();

		for (int i = 0; i < this.adc_measurements_sbc.length; i++) {
			this.adc_measurements_sbc[i] = payload.getUnsignedShort();
		}


		for (int i = 0; i < this.adc_measurements_cop.length; i++) {
			this.adc_measurements_cop[i] = payload.getUnsignedShort();
		}

		this.telecommand_cnt = payload.getUnsignedByte();
		this.state_cur = payload.getUnsignedByte();
		this.iridium_RSSI = payload.getUnsignedByte();
		this.lidar_cover_open = payload.getUnsignedByte();
		this.bat_heater_fault = payload.getUnsignedByte();
		this.bat_status = payload.getUnsignedByte();
		this.gps_quality = payload.getUnsignedByte();
		this.gps_satsUsed = payload.getUnsignedByte();
		this.controller_ids = payload.getUnsignedByte();
		this.available_status = payload.getUnsignedByte();

		if (isMavlink2) {

		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_seed_system_t() {
		this.msgid = MAVLINK_MSG_ID_SEED_SYSTEM_T;
	}

	/**
	 * Constructor for a new message, initializes msgid and all payload variables
	 */
	public msg_seed_system_t(long time_local, long d2time, long mainloop_itr_cnt, long mainloop_itr_time, float imu_acc_x, float imu_acc_y, float imu_acc_z, float imu_gyro_x, float imu_gyro_y, float imu_gyro_z, float baro_press, float baro_temp, float vacuum_baro_press, float tacho_rot_rate, float gps_lat, float gps_long, float gps_hdop, float gps_alt, float filter_vel_vertical, float filter_height_ground, float filter_rotor_rot_rate, float fiter_body_rot_rate, float controller_blade_pitch, float controller_fin_angle, int[] adc_measurements_sbc, int[] adc_measurements_cop, short telecommand_cnt, short state_cur, short iridium_RSSI, short lidar_cover_open, short bat_heater_fault, short bat_status, short gps_quality, short gps_satsUsed, short controller_ids, short available_status) {
		this.msgid = MAVLINK_MSG_ID_SEED_SYSTEM_T;

		this.time_local = time_local;
		this.d2time = d2time;
		this.mainloop_itr_cnt = mainloop_itr_cnt;
		this.mainloop_itr_time = mainloop_itr_time;
		this.imu_acc_x = imu_acc_x;
		this.imu_acc_y = imu_acc_y;
		this.imu_acc_z = imu_acc_z;
		this.imu_gyro_x = imu_gyro_x;
		this.imu_gyro_y = imu_gyro_y;
		this.imu_gyro_z = imu_gyro_z;
		this.baro_press = baro_press;
		this.baro_temp = baro_temp;
		this.vacuum_baro_press = vacuum_baro_press;
		this.tacho_rot_rate = tacho_rot_rate;
		this.gps_lat = gps_lat;
		this.gps_long = gps_long;
		this.gps_hdop = gps_hdop;
		this.gps_alt = gps_alt;
		this.filter_vel_vertical = filter_vel_vertical;
		this.filter_height_ground = filter_height_ground;
		this.filter_rotor_rot_rate = filter_rotor_rot_rate;
		this.fiter_body_rot_rate = fiter_body_rot_rate;
		this.controller_blade_pitch = controller_blade_pitch;
		this.controller_fin_angle = controller_fin_angle;
		this.adc_measurements_sbc = adc_measurements_sbc;
		this.adc_measurements_cop = adc_measurements_cop;
		this.telecommand_cnt = telecommand_cnt;
		this.state_cur = state_cur;
		this.iridium_RSSI = iridium_RSSI;
		this.lidar_cover_open = lidar_cover_open;
		this.bat_heater_fault = bat_heater_fault;
		this.bat_status = bat_status;
		this.gps_quality = gps_quality;
		this.gps_satsUsed = gps_satsUsed;
		this.controller_ids = controller_ids;
		this.available_status = available_status;

	}

	/**
	 * Constructor for a new message, initializes everything
	 */
	public msg_seed_system_t(long time_local, long d2time, long mainloop_itr_cnt, long mainloop_itr_time, float imu_acc_x, float imu_acc_y, float imu_acc_z, float imu_gyro_x, float imu_gyro_y, float imu_gyro_z, float baro_press, float baro_temp, float vacuum_baro_press, float tacho_rot_rate, float gps_lat, float gps_long, float gps_hdop, float gps_alt, float filter_vel_vertical, float filter_height_ground, float filter_rotor_rot_rate, float fiter_body_rot_rate, float controller_blade_pitch, float controller_fin_angle, int[] adc_measurements_sbc, int[] adc_measurements_cop, short telecommand_cnt, short state_cur, short iridium_RSSI, short lidar_cover_open, short bat_heater_fault, short bat_status, short gps_quality, short gps_satsUsed, short controller_ids, short available_status, int sysid, int compid, boolean isMavlink2) {
		this.msgid = MAVLINK_MSG_ID_SEED_SYSTEM_T;
		this.sysid = sysid;
		this.compid = compid;
		this.isMavlink2 = isMavlink2;

		this.time_local = time_local;
		this.d2time = d2time;
		this.mainloop_itr_cnt = mainloop_itr_cnt;
		this.mainloop_itr_time = mainloop_itr_time;
		this.imu_acc_x = imu_acc_x;
		this.imu_acc_y = imu_acc_y;
		this.imu_acc_z = imu_acc_z;
		this.imu_gyro_x = imu_gyro_x;
		this.imu_gyro_y = imu_gyro_y;
		this.imu_gyro_z = imu_gyro_z;
		this.baro_press = baro_press;
		this.baro_temp = baro_temp;
		this.vacuum_baro_press = vacuum_baro_press;
		this.tacho_rot_rate = tacho_rot_rate;
		this.gps_lat = gps_lat;
		this.gps_long = gps_long;
		this.gps_hdop = gps_hdop;
		this.gps_alt = gps_alt;
		this.filter_vel_vertical = filter_vel_vertical;
		this.filter_height_ground = filter_height_ground;
		this.filter_rotor_rot_rate = filter_rotor_rot_rate;
		this.fiter_body_rot_rate = fiter_body_rot_rate;
		this.controller_blade_pitch = controller_blade_pitch;
		this.controller_fin_angle = controller_fin_angle;
		this.adc_measurements_sbc = adc_measurements_sbc;
		this.adc_measurements_cop = adc_measurements_cop;
		this.telecommand_cnt = telecommand_cnt;
		this.state_cur = state_cur;
		this.iridium_RSSI = iridium_RSSI;
		this.lidar_cover_open = lidar_cover_open;
		this.bat_heater_fault = bat_heater_fault;
		this.bat_status = bat_status;
		this.gps_quality = gps_quality;
		this.gps_satsUsed = gps_satsUsed;
		this.controller_ids = controller_ids;
		this.available_status = available_status;

	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a mavlink packet
	 */
	public msg_seed_system_t(MAVLinkPacket mavLinkPacket) {
		this.msgid = MAVLINK_MSG_ID_SEED_SYSTEM_T;

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
		return "MAVLINK_MSG_ID_SEED_SYSTEM_T - sysid:" + sysid + " compid:" + compid + " time_local:" + time_local + " d2time:" + d2time + " mainloop_itr_cnt:" + mainloop_itr_cnt + " mainloop_itr_time:" + mainloop_itr_time + " imu_acc_x:" + imu_acc_x + " imu_acc_y:" + imu_acc_y + " imu_acc_z:" + imu_acc_z + " imu_gyro_x:" + imu_gyro_x + " imu_gyro_y:" + imu_gyro_y + " imu_gyro_z:" + imu_gyro_z + " baro_press:" + baro_press + " baro_temp:" + baro_temp + " vacuum_baro_press:" + vacuum_baro_press + " tacho_rot_rate:" + tacho_rot_rate + " gps_lat:" + gps_lat + " gps_long:" + gps_long + " gps_hdop:" + gps_hdop + " gps_alt:" + gps_alt + " filter_vel_vertical:" + filter_vel_vertical + " filter_height_ground:" + filter_height_ground + " filter_rotor_rot_rate:" + filter_rotor_rot_rate + " fiter_body_rot_rate:" + fiter_body_rot_rate + " controller_blade_pitch:" + controller_blade_pitch + " controller_fin_angle:" + controller_fin_angle + " adc_measurements_sbc:" + adc_measurements_sbc + " adc_measurements_cop:" + adc_measurements_cop + " telecommand_cnt:" + telecommand_cnt + " state_cur:" + state_cur + " iridium_RSSI:" + iridium_RSSI + " lidar_cover_open:" + lidar_cover_open + " bat_heater_fault:" + bat_heater_fault + " bat_status:" + bat_status + " gps_quality:" + gps_quality + " gps_satsUsed:" + gps_satsUsed + " controller_ids:" + controller_ids + " available_status:" + available_status + "";
	}

	/**
	 * Returns a human-readable string of the name of the message
	 */
	@Override
	public String name() {
		return "MAVLINK_MSG_ID_SEED_SYSTEM_T";
	}
}
