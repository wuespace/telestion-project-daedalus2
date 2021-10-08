/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

package com.MAVLink.enums;

/**
 * Gimbal manager high level capability flags (bitmap). The first 16 bits are identical to the GIMBAL_DEVICE_CAP_FLAGS which are identical with GIMBAL_DEVICE_FLAGS. However, the gimbal manager does not need to copy the flags from the gimbal but can also enhance the capabilities and thus add flags.
 */
public class GIMBAL_MANAGER_CAP_FLAGS {
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_RETRACT = 1; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_RETRACT. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_NEUTRAL = 2; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_NEUTRAL. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_ROLL_AXIS = 4; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_ROLL_AXIS. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_ROLL_FOLLOW = 8; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_ROLL_FOLLOW. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_ROLL_LOCK = 16; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_ROLL_LOCK. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_PITCH_AXIS = 32; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_PITCH_AXIS. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_PITCH_FOLLOW = 64; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_PITCH_FOLLOW. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_PITCH_LOCK = 128; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_PITCH_LOCK. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_YAW_AXIS = 256; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_YAW_AXIS. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_YAW_FOLLOW = 512; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_YAW_FOLLOW. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_HAS_YAW_LOCK = 1024; /* Based on GIMBAL_DEVICE_CAP_FLAGS_HAS_YAW_LOCK. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_SUPPORTS_INFINITE_YAW = 2048; /* Based on GIMBAL_DEVICE_CAP_FLAGS_SUPPORTS_INFINITE_YAW. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_CAN_POINT_LOCATION_LOCAL = 65536; /* Gimbal manager supports to point to a local position. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_CAN_POINT_LOCATION_GLOBAL = 131072; /* Gimbal manager supports to point to a global latitude, longitude, altitude position. | */
   public static final int GIMBAL_MANAGER_CAP_FLAGS_ENUM_END = 131073; /*  | */
}
