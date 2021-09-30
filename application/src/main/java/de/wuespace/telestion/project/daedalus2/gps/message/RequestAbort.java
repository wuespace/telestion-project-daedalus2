package de.wuespace.telestion.project.daedalus2.gps.message;

/**
 * Requests the abort of a transmission of the stored A-GPS data
 * in the verticle shared data space.
 */
public record RequestAbort() implements AGpsRequest {
}
