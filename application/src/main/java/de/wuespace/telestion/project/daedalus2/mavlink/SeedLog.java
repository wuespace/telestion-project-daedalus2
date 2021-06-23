package de.wuespace.telestion.project.daedalus2.mavlink;
import de.wuespace.telestion.extension.mavlink.annotation.MavField;
import de.wuespace.telestion.extension.mavlink.annotation.MavInfo;
import de.wuespace.telestion.extension.mavlink.annotation.NativeType;
import de.wuespace.telestion.extension.mavlink.annotation.MavArray;
import de.wuespace.telestion.extension.mavlink.message.MavlinkMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Contains log data of the Seed<br>
 * <br>
 * <i>Autogenerated by XML2Record-Tool v1.3.11</i>
 * 
 * @author Autogenerated by XML2Record-Tool (by Cedric Boes)
 * @version 1.0 (autogenerated)
 */
@MavInfo(id = 10002, crc = 154)
public record SeedLog(
	/**
	 * Seed local time<br>
	 * <br>
	 * <i>Autogenerated by XML2Record-Tool v1.3.11</i>
	 */
	@MavField(nativeType = NativeType.INT_64)
	@JsonProperty long timeLocal, 
	/**
	 * Logging data<br>
	 * <br>
	 * <i>Autogenerated by XML2Record-Tool v1.3.11</i>
	 */
	@MavArray(length = 247)
	@MavField(nativeType = NativeType.CHAR)
	@JsonProperty char[] logData) implements MavlinkMessage {
	/**
	 * There shall be no default-constructor for normal developers.
	 */
	@SuppressWarnings("unused")
	private SeedLog() {
		this((long) 0, null);
	}
}