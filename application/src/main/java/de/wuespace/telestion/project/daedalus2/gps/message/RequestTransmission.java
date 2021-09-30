package de.wuespace.telestion.project.daedalus2.gps.message;

/**
 * Requests the transmission of the stored A-GPS data
 * in the verticle shared data space.
 */
public record RequestTransmission() implements AGpsRequest {
}
