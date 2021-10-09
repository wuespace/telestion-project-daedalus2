/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

package com.MAVLink.enums;

/**
 * Camera tracking target data (shows where tracked target is within image)
 */
public class CAMERA_TRACKING_TARGET_DATA {
	public static final int CAMERA_TRACKING_TARGET_DATA_NONE = 0; /* No target data | */
	public static final int CAMERA_TRACKING_TARGET_DATA_EMBEDDED = 1; /* Target data embedded in image data
	(proprietary) | */
	public static final int CAMERA_TRACKING_TARGET_DATA_RENDERED = 2; /* Target data rendered in image | */
	public static final int CAMERA_TRACKING_TARGET_DATA_IN_STATUS = 4; /* Target data within status message (Point or
	Rectangle) | */
	public static final int CAMERA_TRACKING_TARGET_DATA_ENUM_END = 5; /*  | */
}
