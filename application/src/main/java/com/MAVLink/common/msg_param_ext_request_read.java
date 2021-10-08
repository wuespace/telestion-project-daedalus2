/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE PARAM_EXT_REQUEST_READ PACKING
package com.MAVLink.common;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

/**
 * Request to read the value of a parameter with either the param_id string id or param_index. PARAM_EXT_VALUE should be emitted in response.
 */
public class msg_param_ext_request_read extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_PARAM_EXT_REQUEST_READ = 320;
    public static final int MAVLINK_MSG_LENGTH = 20;
    private static final long serialVersionUID = MAVLINK_MSG_ID_PARAM_EXT_REQUEST_READ;


    /**
     * Parameter index. Set to -1 to use the Parameter ID field as identifier (else param_id will be ignored)
     */
    public short param_index;

    /**
     * System ID
     */
    public short target_system;

    /**
     * Component ID
     */
    public short target_component;

    /**
     * Parameter id, terminated by NULL if the length is less than 16 human-readable chars and WITHOUT null termination (NULL) byte if the length is exactly 16 chars - applications have to provide 16+1 bytes storage if the ID is stored as string
     */
    public byte param_id[] = new byte[16];


    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_PARAM_EXT_REQUEST_READ;

        packet.payload.putShort(param_index);
        packet.payload.putUnsignedByte(target_system);
        packet.payload.putUnsignedByte(target_component);

        for (int i = 0; i < param_id.length; i++) {
            packet.payload.putByte(param_id[i]);
        }


        if (isMavlink2) {

        }
        return packet;
    }

    /**
     * Decode a param_ext_request_read message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.param_index = payload.getShort();
        this.target_system = payload.getUnsignedByte();
        this.target_component = payload.getUnsignedByte();

        for (int i = 0; i < this.param_id.length; i++) {
            this.param_id[i] = payload.getByte();
        }


        if (isMavlink2) {

        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_param_ext_request_read() {
        this.msgid = MAVLINK_MSG_ID_PARAM_EXT_REQUEST_READ;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_param_ext_request_read( short param_index, short target_system, short target_component, byte[] param_id) {
        this.msgid = MAVLINK_MSG_ID_PARAM_EXT_REQUEST_READ;

        this.param_index = param_index;
        this.target_system = target_system;
        this.target_component = target_component;
        this.param_id = param_id;

    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_param_ext_request_read( short param_index, short target_system, short target_component, byte[] param_id, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_PARAM_EXT_REQUEST_READ;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.param_index = param_index;
        this.target_system = target_system;
        this.target_component = target_component;
        this.param_id = param_id;

    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_param_ext_request_read(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_PARAM_EXT_REQUEST_READ;

        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.isMavlink2 = mavLinkPacket.isMavlink2;
        unpack(mavLinkPacket.payload);
    }


    /**
    * Sets the buffer of this message with a string, adds the necessary padding
    */
    public void setParam_Id(String str) {
        int len = Math.min(str.length(), 16);
        for (int i=0; i<len; i++) {
            param_id[i] = (byte) str.charAt(i);
        }

        for (int i=len; i<16; i++) {            // padding for the rest of the buffer
            param_id[i] = 0;
        }
    }

    /**
    * Gets the message, formated as a string
    */
    public String getParam_Id() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            if (param_id[i] != 0)
                buf.append((char) param_id[i]);
            else
                break;
        }
        return buf.toString();

    }

    /**
     * Returns a string with the MSG name and data
     */
    @Override
    public String toString() {
        return "MAVLINK_MSG_ID_PARAM_EXT_REQUEST_READ - sysid:"+sysid+" compid:"+compid+" param_index:"+param_index+" target_system:"+target_system+" target_component:"+target_component+" param_id:"+param_id+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_PARAM_EXT_REQUEST_READ";
    }
}
