package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AppendixBlade(
		@JsonProperty int blade_pitch
) implements PayloadAppendix {
	private AppendixBlade() {
		this(-1);
	}
}
