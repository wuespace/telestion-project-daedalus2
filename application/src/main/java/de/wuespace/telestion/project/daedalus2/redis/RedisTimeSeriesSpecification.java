package de.wuespace.telestion.project.daedalus2.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

import java.util.List;

@SuppressWarnings("unused")
public record RedisTimeSeriesSpecification(
		@JsonProperty String key,
		@JsonProperty String from,
		@JsonProperty String to,
		@JsonProperty int bucketSize,
		@JsonProperty List<String> aggregations
) implements JsonMessage {
	public RedisTimeSeriesSpecification() {
		this(null, null, null, 500, null);
	}
}
