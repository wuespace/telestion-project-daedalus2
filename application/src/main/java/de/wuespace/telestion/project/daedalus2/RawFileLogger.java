package de.wuespace.telestion.project.daedalus2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;

/**
 * It writes the raw mavlink messages to a file
 */
public class RawFileLogger extends TelestionVerticle<RawFileLogger.Configuration> implements WithEventBus {
	private AsyncFile file;

	public record Configuration(
		@JsonProperty String inAddress,
		@JsonProperty String filename
	) implements TelestionConfiguration {
	}

	@Override
	public void onStart() throws Exception {
		// It creates a file (if it doesn't exist) and opens it
		FileSystem fs = getVertx().fileSystem();
		file = fs.openBlocking(getConfig().filename(), new OpenOptions()
				.setAppend(true)
				.setCreate(true)
				.setSync(true)
		);

		// It registers a handler for the given address.
		register(getConfig().inAddress(), body -> {
			// It writes the raw message to the file.
			Buffer newContent = Buffer.buffer("%n%d,%s".formatted(System.currentTimeMillis(), body.body().toString()));
			file.write(newContent);
		});
	}

	@Override
	public void onStop(Promise<Void> stopPromise) {
		file.flush().compose(event -> file.close()).onComplete(stopPromise);
	}
}
