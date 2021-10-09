/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

package com.MAVLink.enums;

/**
 * Winch status flags used in WINCH_STATUS
 */
public class MAV_WINCH_STATUS_FLAG {
	public static final int MAV_WINCH_STATUS_HEALTHY = 1; /* Winch is healthy | */
	public static final int MAV_WINCH_STATUS_FULLY_RETRACTED = 2; /* Winch thread is fully retracted | */
	public static final int MAV_WINCH_STATUS_MOVING = 4; /* Winch motor is moving | */
	public static final int MAV_WINCH_STATUS_CLUTCH_ENGAGED = 8; /* Winch clutch is engaged allowing motor to move
	freely | */
	public static final int MAV_WINCH_STATUS_FLAG_ENUM_END = 9; /*  | */
}
