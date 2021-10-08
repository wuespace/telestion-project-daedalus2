/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE WINCH_STATUS PACKING
package com.MAVLink.common;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Winch status.
 */
public class msg_winch_status extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_WINCH_STATUS = 9005;
    public static final int MAVLINK_MSG_LENGTH = 34;
    private static final long serialVersionUID = MAVLINK_MSG_ID_WINCH_STATUS;


    /**
     * Timestamp (synced to UNIX time or since system boot).
     */
    public long time_usec;

    /**
     * Length of line released. NaN if unknown
     */
    public float line_length;

    /**
     * Speed line is being released or retracted. Positive values if being released, negative values if being retracted, NaN if unknown
     */
    public float speed;

    /**
     * Tension on the line. NaN if unknown
     */
    public float tension;

    /**
     * Voltage of the battery supplying the winch. NaN if unknown
     */
    public float voltage;

    /**
     * Current draw from the winch. NaN if unknown
     */
    public float current;

    /**
     * Status flags
     */
    public long status;

    /**
     * Temperature of the motor. INT16_MAX if unknown
     */
    public short temperature;


    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_WINCH_STATUS;

        packet.payload.putUnsignedLong(time_usec);
        packet.payload.putFloat(line_length);
        packet.payload.putFloat(speed);
        packet.payload.putFloat(tension);
        packet.payload.putFloat(voltage);
        packet.payload.putFloat(current);
        packet.payload.putUnsignedInt(status);
        packet.payload.putShort(temperature);

        if (isMavlink2) {

        }
        return packet;
    }

    /**
     * Decode a winch_status message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.time_usec = payload.getUnsignedLong();
        this.line_length = payload.getFloat();
        this.speed = payload.getFloat();
        this.tension = payload.getFloat();
        this.voltage = payload.getFloat();
        this.current = payload.getFloat();
        this.status = payload.getUnsignedInt();
        this.temperature = payload.getShort();

        if (isMavlink2) {

        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_winch_status() {
        this.msgid = MAVLINK_MSG_ID_WINCH_STATUS;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_winch_status( long time_usec, float line_length, float speed, float tension, float voltage, float current, long status, short temperature) {
        this.msgid = MAVLINK_MSG_ID_WINCH_STATUS;

        this.time_usec = time_usec;
        this.line_length = line_length;
        this.speed = speed;
        this.tension = tension;
        this.voltage = voltage;
        this.current = current;
        this.status = status;
        this.temperature = temperature;

    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_winch_status( long time_usec, float line_length, float speed, float tension, float voltage, float current, long status, short temperature, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_WINCH_STATUS;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.time_usec = time_usec;
        this.line_length = line_length;
        this.speed = speed;
        this.tension = tension;
        this.voltage = voltage;
        this.current = current;
        this.status = status;
        this.temperature = temperature;

    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_winch_status(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_WINCH_STATUS;

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
        return "MAVLINK_MSG_ID_WINCH_STATUS - sysid:"+sysid+" compid:"+compid+" time_usec:"+time_usec+" line_length:"+line_length+" speed:"+speed+" tension:"+tension+" voltage:"+voltage+" current:"+current+" status:"+status+" temperature:"+temperature+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_WINCH_STATUS";
    }
}
