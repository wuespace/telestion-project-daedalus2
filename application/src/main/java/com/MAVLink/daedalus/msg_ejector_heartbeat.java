/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE EJECTOR_HEARTBEAT PACKING
package com.MAVLink.daedalus;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
        
/**
 *  Contains information about the current state and local time.
 */
public class msg_ejector_heartbeat extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_EJECTOR_HEARTBEAT = 66957;
    public static final int MAVLINK_MSG_LENGTH = 17;
    private static final long serialVersionUID = MAVLINK_MSG_ID_EJECTOR_HEARTBEAT;

      
    /**
     * Ejector local time
     */
    public long time_local;
      
    /**
     * system time
     */
    public long d2time;
      
    /**
     * number of received and executed telecommands
     */
    public short telecommand_cnt;
      
    /**
     * current System state
     */
    public short state_cur;
      
    /**
     * LED status
     */
    public short led_enabled;
      
    /**
     * Cam status
     */
    public short cam_enabled;
      
    /**
     * Seed Power status
     */
    public short seed_power_enabled;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_EJECTOR_HEARTBEAT;
        
        packet.payload.putLong(time_local);
        packet.payload.putUnsignedInt(d2time);
        packet.payload.putUnsignedByte(telecommand_cnt);
        packet.payload.putUnsignedByte(state_cur);
        packet.payload.putUnsignedByte(led_enabled);
        packet.payload.putUnsignedByte(cam_enabled);
        packet.payload.putUnsignedByte(seed_power_enabled);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a ejector_heartbeat message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        
        this.time_local = payload.getLong();
        this.d2time = payload.getUnsignedInt();
        this.telecommand_cnt = payload.getUnsignedByte();
        this.state_cur = payload.getUnsignedByte();
        this.led_enabled = payload.getUnsignedByte();
        this.cam_enabled = payload.getUnsignedByte();
        this.seed_power_enabled = payload.getUnsignedByte();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_ejector_heartbeat() {
        this.msgid = MAVLINK_MSG_ID_EJECTOR_HEARTBEAT;
    }
    
    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_ejector_heartbeat( long time_local, long d2time, short telecommand_cnt, short state_cur, short led_enabled, short cam_enabled, short seed_power_enabled) {
        this.msgid = MAVLINK_MSG_ID_EJECTOR_HEARTBEAT;

        this.time_local = time_local;
        this.d2time = d2time;
        this.telecommand_cnt = telecommand_cnt;
        this.state_cur = state_cur;
        this.led_enabled = led_enabled;
        this.cam_enabled = cam_enabled;
        this.seed_power_enabled = seed_power_enabled;
        
    }
    
    /**
     * Constructor for a new message, initializes everything
     */
    public msg_ejector_heartbeat( long time_local, long d2time, short telecommand_cnt, short state_cur, short led_enabled, short cam_enabled, short seed_power_enabled, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_EJECTOR_HEARTBEAT;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.time_local = time_local;
        this.d2time = d2time;
        this.telecommand_cnt = telecommand_cnt;
        this.state_cur = state_cur;
        this.led_enabled = led_enabled;
        this.cam_enabled = cam_enabled;
        this.seed_power_enabled = seed_power_enabled;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_ejector_heartbeat(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_EJECTOR_HEARTBEAT;
        
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
        return "MAVLINK_MSG_ID_EJECTOR_HEARTBEAT - sysid:"+sysid+" compid:"+compid+" time_local:"+time_local+" d2time:"+d2time+" telecommand_cnt:"+telecommand_cnt+" state_cur:"+state_cur+" led_enabled:"+led_enabled+" cam_enabled:"+cam_enabled+" seed_power_enabled:"+seed_power_enabled+"";
    }
    
    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_EJECTOR_HEARTBEAT";
    }
}
        