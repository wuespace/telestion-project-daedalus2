/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

package com.MAVLink.enums;

/**
 * Generalized UAVCAN node mode
 */
public class UAVCAN_NODE_MODE {
	public static final int UAVCAN_NODE_MODE_OPERATIONAL = 0; /* The node is performing its primary functions. | */
	public static final int UAVCAN_NODE_MODE_INITIALIZATION = 1; /* The node is initializing; this mode is entered
	immediately after startup. | */
	public static final int UAVCAN_NODE_MODE_MAINTENANCE = 2; /* The node is under maintenance. | */
	public static final int UAVCAN_NODE_MODE_SOFTWARE_UPDATE = 3; /* The node is in the process of updating its
	software. | */
	public static final int UAVCAN_NODE_MODE_OFFLINE = 7; /* The node is no longer available online. | */
	public static final int UAVCAN_NODE_MODE_ENUM_END = 8; /*  | */
}
