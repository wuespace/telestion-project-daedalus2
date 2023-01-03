/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE CON_CMD PACKING
package com.MAVLink.daedalus;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;
import com.MAVLink.Messages.Description;

/**
 *  Freetext command input, one command per message.
		
 */
public class msg_con_cmd extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_CON_CMD = 72498;
    public static final int MAVLINK_MSG_LENGTH = 255;
    private static final long serialVersionUID = MAVLINK_MSG_ID_CON_CMD;

    
    /**
     * CMD content
     */
    @Description("CMD content")
    @Units("")
    public byte con_cmd[] = new byte[255];
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_CON_CMD;

        
        for (int i = 0; i < con_cmd.length; i++) {
            packet.payload.putByte(con_cmd[i]);
        }
                    
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a con_cmd message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        
        for (int i = 0; i < this.con_cmd.length; i++) {
            this.con_cmd[i] = payload.getByte();
        }
                
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_con_cmd() {
        this.msgid = MAVLINK_MSG_ID_CON_CMD;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_con_cmd( byte[] con_cmd) {
        this.msgid = MAVLINK_MSG_ID_CON_CMD;

        this.con_cmd = con_cmd;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_con_cmd( byte[] con_cmd, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_CON_CMD;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.con_cmd = con_cmd;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_con_cmd(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_CON_CMD;

        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.isMavlink2 = mavLinkPacket.isMavlink2;
        unpack(mavLinkPacket.payload);
    }

     
    /**
    * Sets the buffer of this message with a string, adds the necessary padding
    */
    public void setCon_Cmd(String str) {
        int len = Math.min(str.length(), 255);
        for (int i=0; i<len; i++) {
            con_cmd[i] = (byte) str.charAt(i);
        }

        for (int i=len; i<255; i++) {            // padding for the rest of the buffer
            con_cmd[i] = 0;
        }
    }

    /**
    * Gets the message, formatted as a string
    */
    public String getCon_Cmd() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 255; i++) {
            if (con_cmd[i] != 0)
                buf.append((char) con_cmd[i]);
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
        return "MAVLINK_MSG_ID_CON_CMD - sysid:"+sysid+" compid:"+compid+" con_cmd:"+con_cmd+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_CON_CMD";
    }
}
        