/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE ASSIST_NOW_UPLOAD PACKING
package com.MAVLink.daedalus;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;
import com.MAVLink.Messages.Description;

/**
 *  contains ublox_msg
 */
public class msg_assist_now_upload extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_ASSIST_NOW_UPLOAD = 21513;
    public static final int MAVLINK_MSG_LENGTH = 255;
    private static final long serialVersionUID = MAVLINK_MSG_ID_ASSIST_NOW_UPLOAD;

    
    /**
     * ublox_msg
     */
    @Description("ublox_msg")
    @Units("")
    public short ublox_msg[] = new short[255];
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_ASSIST_NOW_UPLOAD;

        
        for (int i = 0; i < ublox_msg.length; i++) {
            packet.payload.putUnsignedByte(ublox_msg[i]);
        }
                    
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a assist_now_upload message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        
        for (int i = 0; i < this.ublox_msg.length; i++) {
            this.ublox_msg[i] = payload.getUnsignedByte();
        }
                
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_assist_now_upload() {
        this.msgid = MAVLINK_MSG_ID_ASSIST_NOW_UPLOAD;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_assist_now_upload( short[] ublox_msg) {
        this.msgid = MAVLINK_MSG_ID_ASSIST_NOW_UPLOAD;

        this.ublox_msg = ublox_msg;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_assist_now_upload( short[] ublox_msg, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_ASSIST_NOW_UPLOAD;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.ublox_msg = ublox_msg;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_assist_now_upload(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_ASSIST_NOW_UPLOAD;

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
        return "MAVLINK_MSG_ID_ASSIST_NOW_UPLOAD - sysid:"+sysid+" compid:"+compid+" ublox_msg:"+ublox_msg+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_ASSIST_NOW_UPLOAD";
    }
}
        