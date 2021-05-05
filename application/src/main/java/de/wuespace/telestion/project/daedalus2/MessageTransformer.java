package de.wuespace.telestion.project.daedalus2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.mavlink.Drehtest;
import de.wuespace.telestion.project.daedalus2.messages.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MessageTransformer extends AbstractVerticle {
	private Configuration config;

	@SuppressWarnings("unused")
	public MessageTransformer() {
		this(null);
	}

	public MessageTransformer(Configuration config) {
		this.config = config;
	}

	@Override
	public void start(Promise<Void> startPromise) {
		var eb = vertx.eventBus();

		this.config = Config.get(this.config, config(), Configuration.class);

		eb.consumer(this.config.inAddress(), raw -> JsonMessage.on(Drehtest.class, raw, message -> {
			var beautifulMessage = new SystemT(
					message.timeLocal(),
					new Imu(new Accelerometer(message.imuAccX(), message.imuAccY(), message.imuAccZ()), new Gyro(message.imuGyroX(), message.imuGyroY(), message.imuGyroZ())),
					message.tachoRotRate(),
					new ServoAmps(message.servoAmps()[0], message.servoAmps()[1], message.servoAmps()[2], message.servoAmps()[3]),
					new BatVolts(message.batVolts()[0], message.batVolts()[1]),
					new Filter(message.filterVelVertical(), message.filterVelVerticalInd(), message.filterHeightGround(), message.filterRotorRotRate(), message.filterBodyRotRate()),
					new Controller(message.controllerBladePitch(), message.controllerFinAngle()),
					message.controllerId(),
					new Status(
							(message.availableStatus() & 0b00000001) > 0,
							(message.availableStatus() & 0b00000010) > 0,
							(message.availableStatus() & 0b00000100) > 0,
							(message.availableStatus() & 0b00001000) > 0,
							(message.availableStatus() & 0b00010000) > 0,
							(message.availableStatus() & 0b00100000) > 0,
							(message.availableStatus() & 0b01000000) > 0,
							(message.availableStatus() & 0b10000000) > 0
					)
			);
			eb.publish(this.config.outAddress(), beautifulMessage.json());
		}));


		startPromise.complete();
	}

	public record Configuration(
			@JsonProperty
			String inAddress,
			@JsonProperty
			String outAddress
	) implements JsonMessage {
		@SuppressWarnings("unused")
		private Configuration() {
			this(null, null);
		}
	}
}
