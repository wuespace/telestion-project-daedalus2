package de.wuespace.telestion.project.daedalus2.redis.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public interface RedisBaseConfiguration extends JsonMessage {
	@JsonProperty String connectionString();
	@JsonProperty int reconnectAttempts();
}
