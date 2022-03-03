package de.wuespace.telestion.project.daedalus2;

import de.wuespace.telestion.api.verticle.GenericConfiguration;
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
public class RawFileLogger extends TelestionVerticle<GenericConfiguration> implements WithEventBus {
	AsyncFile file;

	@Override
	public void onStart() throws Exception {
		// It creates a file and opens it in blocking mode.
		FileSystem fs = getVertx().fileSystem();
		file = fs.openBlocking("data/out.bin", new OpenOptions()
				.setAppend(true)
				.setCreate(true)
				.setSync(true)
		);

		// It registers a handler for the `raw-mavlink` event.
		register("raw-mavlink", body -> {
			// It writes the raw mavlink message to the file.
			Buffer newContent = Buffer.buffer("%n%d,%s".formatted(System.currentTimeMillis(), body.body().toString()));
			file.write(newContent);
		});
	}

	@Override
	public void onStop(Promise<Void> stopPromise) {
		file.flush().onSuccess(evt -> file.close(stopPromise));
	}
}
