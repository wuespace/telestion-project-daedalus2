package de.wuespace.telestion.project.daedalus2.mavlink.internal;

public record SplitData(byte[] chunk, byte[] remainder) {
}
