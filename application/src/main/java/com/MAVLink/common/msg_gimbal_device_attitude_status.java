/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE GIMBAL_DEVICE_ATTITUDE_STATUS PACKING
package com.MAVLink.common;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Message reporting the status of a gimbal device. This message should be broadcasted by a gimbal device component. The angles encoded in the quaternion are in the global frame (roll: positive is rolling to the right, pitch: positive is pitching up, yaw is turn to the right). This message should be broadcast at a low regular rate (e.g. 10Hz).
 */
public class msg_gimbal_device_attitude_status extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS = 285;
    public static final int MAVLINK_MSG_LENGTH = 40;
    private static final long serialVersionUID = MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS;


    /**
     * Timestamp (time since system boot).
     */
    public long time_boot_ms;

    /**
     * Quaternion components, w, x, y, z (1 0 0 0 is the null-rotation, the frame is depends on whether the flag GIMBAL_DEVICE_FLAGS_YAW_LOCK is set)
     */
    public float q[] = new float[4];

    /**
     * X component of angular velocity (NaN if unknown)
     */
    public float angular_velocity_x;

    /**
     * Y component of angular velocity (NaN if unknown)
     */
    public float angular_velocity_y;

    /**
     * Z component of angular velocity (NaN if unknown)
     */
    public float angular_velocity_z;

    /**
     * Failure flags (0 for no failure)
     */
    public long failure_flags;

    /**
     * Current gimbal flags set.
     */
    public int flags;

    /**
     * System ID
     */
    public short target_system;

    /**
     * Component ID
     */
    public short target_component;


    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS;

        packet.payload.putUnsignedInt(time_boot_ms);

        for (int i = 0; i < q.length; i++) {
            packet.payload.putFloat(q[i]);
        }

        packet.payload.putFloat(angular_velocity_x);
        packet.payload.putFloat(angular_velocity_y);
        packet.payload.putFloat(angular_velocity_z);
        packet.payload.putUnsignedInt(failure_flags);
        packet.payload.putUnsignedShort(flags);
        packet.payload.putUnsignedByte(target_system);
        packet.payload.putUnsignedByte(target_component);

        if (isMavlink2) {

        }
        return packet;
    }

    /**
     * Decode a gimbal_device_attitude_status message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.time_boot_ms = payload.getUnsignedInt();

        for (int i = 0; i < this.q.length; i++) {
            this.q[i] = payload.getFloat();
        }

        this.angular_velocity_x = payload.getFloat();
        this.angular_velocity_y = payload.getFloat();
        this.angular_velocity_z = payload.getFloat();
        this.failure_flags = payload.getUnsignedInt();
        this.flags = payload.getUnsignedShort();
        this.target_system = payload.getUnsignedByte();
        this.target_component = payload.getUnsignedByte();

        if (isMavlink2) {

        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_gimbal_device_attitude_status() {
        this.msgid = MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_gimbal_device_attitude_status( long time_boot_ms, float[] q, float angular_velocity_x, float angular_velocity_y, float angular_velocity_z, long failure_flags, int flags, short target_system, short target_component) {
        this.msgid = MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS;

        this.time_boot_ms = time_boot_ms;
        this.q = q;
        this.angular_velocity_x = angular_velocity_x;
        this.angular_velocity_y = angular_velocity_y;
        this.angular_velocity_z = angular_velocity_z;
        this.failure_flags = failure_flags;
        this.flags = flags;
        this.target_system = target_system;
        this.target_component = target_component;

    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_gimbal_device_attitude_status( long time_boot_ms, float[] q, float angular_velocity_x, float angular_velocity_y, float angular_velocity_z, long failure_flags, int flags, short target_system, short target_component, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.time_boot_ms = time_boot_ms;
        this.q = q;
        this.angular_velocity_x = angular_velocity_x;
        this.angular_velocity_y = angular_velocity_y;
        this.angular_velocity_z = angular_velocity_z;
        this.failure_flags = failure_flags;
        this.flags = flags;
        this.target_system = target_system;
        this.target_component = target_component;

    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_gimbal_device_attitude_status(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS;

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
        return "MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS - sysid:"+sysid+" compid:"+compid+" time_boot_ms:"+time_boot_ms+" q:"+q+" angular_velocity_x:"+angular_velocity_x+" angular_velocity_y:"+angular_velocity_y+" angular_velocity_z:"+angular_velocity_z+" failure_flags:"+failure_flags+" flags:"+flags+" target_system:"+target_system+" target_component:"+target_component+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_GIMBAL_DEVICE_ATTITUDE_STATUS";
    }
}
