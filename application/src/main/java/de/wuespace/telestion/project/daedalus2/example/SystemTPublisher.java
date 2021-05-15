package de.wuespace.telestion.project.daedalus2.example;

import de.wuespace.telestion.services.database.DataListener;
// import de.wuespace.telestion.services.database.PeriodicDataAggregator;
import de.wuespace.telestion.project.daedalus2.messages.SystemT;
import de.wuespace.telestion.services.database.MongoDatabaseService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

import java.time.Duration;
import java.util.List;

public class SystemTPublisher extends AbstractVerticle {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MongoDatabaseService("daedalus2", "d2Pool"));
		/*vertx.deployVerticle(new PeriodicDataAggregator(
				"de.wuespace.telestion.project.daedalus2.messages.SystemT",
				"imu.acc.x",
				1,
				"aggregated-imu.acc.x"
		));*/
		vertx.deployVerticle(new DataListener(List.of("SystemT#out")));
		// vertx.deployVerticle(new MessageLogger());
		vertx.deployVerticle(new SystemTPublisher());
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		vertx.setPeriodic(Duration.ofMillis(200).toMillis(), id -> {
			for (int i = 0; i <= 9; i++) {
				vertx.eventBus().publish("SystemT#out", new SystemT().json());
			}
		});
		startPromise.complete();
	}
}
