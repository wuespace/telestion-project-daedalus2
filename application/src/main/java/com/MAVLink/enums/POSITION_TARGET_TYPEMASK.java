/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

package com.MAVLink.enums;

/**
 * Bitmap to indicate which dimensions should be ignored by the vehicle: a value of 0b0000000000000000 or 0b0000001000000000 indicates that none of the setpoint dimensions should be ignored. If bit 9 is set the floats afx afy afz should be interpreted as force instead of acceleration.
 */
public class POSITION_TARGET_TYPEMASK {
	public static final int POSITION_TARGET_TYPEMASK_X_IGNORE = 1; /* Ignore position x | */
	public static final int POSITION_TARGET_TYPEMASK_Y_IGNORE = 2; /* Ignore position y | */
	public static final int POSITION_TARGET_TYPEMASK_Z_IGNORE = 4; /* Ignore position z | */
	public static final int POSITION_TARGET_TYPEMASK_VX_IGNORE = 8; /* Ignore velocity x | */
	public static final int POSITION_TARGET_TYPEMASK_VY_IGNORE = 16; /* Ignore velocity y | */
	public static final int POSITION_TARGET_TYPEMASK_VZ_IGNORE = 32; /* Ignore velocity z | */
	public static final int POSITION_TARGET_TYPEMASK_AX_IGNORE = 64; /* Ignore acceleration x | */
	public static final int POSITION_TARGET_TYPEMASK_AY_IGNORE = 128; /* Ignore acceleration y | */
	public static final int POSITION_TARGET_TYPEMASK_AZ_IGNORE = 256; /* Ignore acceleration z | */
	public static final int POSITION_TARGET_TYPEMASK_FORCE_SET = 512; /* Use force instead of acceleration | */
	public static final int POSITION_TARGET_TYPEMASK_YAW_IGNORE = 1024; /* Ignore yaw | */
	public static final int POSITION_TARGET_TYPEMASK_YAW_RATE_IGNORE = 2048; /* Ignore yaw rate | */
	public static final int POSITION_TARGET_TYPEMASK_ENUM_END = 2049; /*  | */
}
