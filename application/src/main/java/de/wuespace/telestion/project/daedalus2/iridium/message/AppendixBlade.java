package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public record AppendixBlade(
		@JsonProperty int blade_pitch
) implements PayloadAppendix {
}
