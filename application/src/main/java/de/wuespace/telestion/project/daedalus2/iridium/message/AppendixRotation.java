package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AppendixRotation(
		@JsonProperty int rotation_rate
) implements PayloadAppendix {
	private AppendixRotation() {
		this(-1);
	}
}
