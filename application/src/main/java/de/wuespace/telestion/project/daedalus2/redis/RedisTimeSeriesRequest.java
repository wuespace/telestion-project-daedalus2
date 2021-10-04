package de.wuespace.telestion.project.daedalus2.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;
import de.wuespace.telestion.api.message.JsonMessage;

public record RedisTimeSeriesRequest(
		@NonNull
		@JsonProperty RedisTimeSeriesSpecification[] fields
) implements JsonMessage {
	public RedisTimeSeriesRequest() {
		this(new RedisTimeSeriesSpecification[] {});
	}
}
