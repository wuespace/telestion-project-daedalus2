package de.wuespace.telestion.project.daedalus2.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;
import de.wuespace.telestion.api.message.JsonMessage;

import java.util.List;

@SuppressWarnings("unused")
public record RedisLatestRequest(
		@NonNull
		@JsonProperty List<String> fields
) implements JsonMessage {
	public RedisLatestRequest() {
		this(List.of());
	}
}
