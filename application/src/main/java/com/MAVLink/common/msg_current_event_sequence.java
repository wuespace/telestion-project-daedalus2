/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE CURRENT_EVENT_SEQUENCE PACKING
package com.MAVLink.common;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Regular broadcast for the current latest event sequence number for a component. This is used to check for dropped events.
 */
public class msg_current_event_sequence extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE = 411;
    public static final int MAVLINK_MSG_LENGTH = 3;
    private static final long serialVersionUID = MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE;


    /**
     * Sequence number.
     */
    public int sequence;

    /**
     * Flag bitset.
     */
    public short flags;


    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE;

        packet.payload.putUnsignedShort(sequence);
        packet.payload.putUnsignedByte(flags);

        if (isMavlink2) {

        }
        return packet;
    }

    /**
     * Decode a current_event_sequence message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.sequence = payload.getUnsignedShort();
        this.flags = payload.getUnsignedByte();

        if (isMavlink2) {

        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_current_event_sequence() {
        this.msgid = MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_current_event_sequence( int sequence, short flags) {
        this.msgid = MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE;

        this.sequence = sequence;
        this.flags = flags;

    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_current_event_sequence( int sequence, short flags, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.sequence = sequence;
        this.flags = flags;

    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_current_event_sequence(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE;

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
        return "MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE - sysid:"+sysid+" compid:"+compid+" sequence:"+sequence+" flags:"+flags+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_CURRENT_EVENT_SEQUENCE";
    }
}
