package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

/**
 * The base for all Iridium information elements.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public interface InformationElement extends JsonMessage {
}
