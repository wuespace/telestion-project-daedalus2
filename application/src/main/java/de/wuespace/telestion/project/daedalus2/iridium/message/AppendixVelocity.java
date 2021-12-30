package de.wuespace.telestion.project.daedalus2.iridium.message;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public record AppendixVelocity(
		@JsonProperty int vertical_velocity
) implements PayloadAppendix {
}
