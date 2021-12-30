package de.wuespace.telestion.project.daedalus2.util;

import de.wuespace.telestion.services.connection.rework.RawMessage;

public record SplitData(byte[] chunk, byte[] remainder) {
	public RawMessage toRawMessage() {
		return new RawMessage(this.chunk());
	}
}
