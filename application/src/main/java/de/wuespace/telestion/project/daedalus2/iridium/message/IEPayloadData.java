package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.wuespace.telestion.api.message.JsonMessage;

/**
 * The base for the payload data in an Iridium payload information element.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public interface IEPayloadData extends JsonMessage {
}
