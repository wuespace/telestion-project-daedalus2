package de.wuespace.telestion.project.daedalus2.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.project.daedalus2.mavlink.SeedSystemT;

/**
 * A beautiful version of the Drehtest message
 *
 * @param timeLocal Seed local time
 */
public record SystemT(
		@JsonProperty
		long timeLocal,

		@JsonProperty
		Imu imu,

		@JsonProperty
		Baro baro,

		@JsonProperty
		float tachoRotRate,

		@JsonProperty
		ServoAmps servoAmps,

		@JsonProperty
		float batTemp,

		@JsonProperty
		float batHeatingAmps,

		@JsonProperty
		Volts volts,

		@JsonProperty
		Gps gps,

		@JsonProperty
		Filter filter,

		@JsonProperty
		Controller controller,

		@JsonProperty int currentState,

		@JsonProperty
		LidarServo lidarServo,

		@JsonProperty
		SystemConfig systemConfig,

		@JsonProperty
		AvailableStatus availableStatus
) implements JsonMessage {
	public SystemT() {
		this(
				System.currentTimeMillis(),
				new Imu(),
				new Baro(),
				0,
				new ServoAmps(),
				0,
				0,
				new Volts(),
				new Gps(),
				new Filter(),
				new Controller(),
				0,
				new LidarServo(),
				new SystemConfig(),
				new AvailableStatus()
		);
	}

	public SystemT(SeedSystemT rawSeedSystemT) {
		this(

				rawSeedSystemT.timeLocal(),
				new Imu(
						new Accelerometer(
								rawSeedSystemT.imuAccX(),
								rawSeedSystemT.imuAccY(),
								rawSeedSystemT.imuAccZ()
						),
						new Gyro(
								rawSeedSystemT.imuGyroX(),
								rawSeedSystemT.imuGyroY(),
								rawSeedSystemT.imuGyroZ()
						)
				),
				new Baro(rawSeedSystemT.baroPress(), rawSeedSystemT.baroTemp(), rawSeedSystemT.vacuumBaroPress()),
				rawSeedSystemT.tachoRotRate(),
				new ServoAmps(
						rawSeedSystemT.swashplateServo1Amps(),
						rawSeedSystemT.swashplateServo2Amps(),
						rawSeedSystemT.swashplateServo3Amps(),
						rawSeedSystemT.finServoAmps()
				),
				rawSeedSystemT.batTemp(),
				rawSeedSystemT.batHeatingAmps(),
				new Volts(
						rawSeedSystemT.rxsmVolts(),
						rawSeedSystemT.bat1Volts(),
						rawSeedSystemT.bat2Volts(),
						rawSeedSystemT.rail3V3Volts()
				),
				new Gps(
						rawSeedSystemT.gpsLat(),
						rawSeedSystemT.gpsLong(),
						rawSeedSystemT.gpsHdop(),
						rawSeedSystemT.gpsAlt(),
						rawSeedSystemT.gpsQuality(),
						rawSeedSystemT.gpsSatsused()
				),
				new Filter(
						rawSeedSystemT.filterVelVertical(),
						rawSeedSystemT.filterVelVerticalInd(),
						rawSeedSystemT.filterHeightGround(),
						rawSeedSystemT.filterRotorRotRate(),
						rawSeedSystemT.fiterBodyRotRate()
				),
				new Controller(
						rawSeedSystemT.controllerId(),
						rawSeedSystemT.controllerBladePitch(),
						rawSeedSystemT.controllerFinAngle()
				),
				rawSeedSystemT.stateCur(),
				new LidarServo(rawSeedSystemT.lidarServo()),
				new SystemConfig(rawSeedSystemT.systemConfig()),
				new AvailableStatus(rawSeedSystemT.availableStatus())
		);
	}
}
