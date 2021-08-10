import { MongoCollection } from './base';
import { JavaMessage } from '../java-message';

export interface Accelerometer extends JavaMessage {
	x: number;
	y: number;
	z: number;
	className: 'de.wuespace.telestion.project.daedalus2.messages.Accelerometer';
}

export interface Gyroscope extends JavaMessage {
	x: number;
	y: number;
	z: number;
	className: 'de.wuespace.telestion.project.daedalus2.messages.Gyro';
}

export interface IMU extends JavaMessage {
	acc: Accelerometer;
	gyro: Gyroscope;
	className: 'de.wuespace.telestion.project.daedalus2.messages.Imu';
}

export interface Barometer extends JavaMessage {
	press: number;
	temp: number;
	vacuumPress: number;
	className: 'de.wuespace.telestion.project.daedalus2.messages.Baro';
}

export interface ServoAmps extends JavaMessage {
	swashplate_servo1_amps: number;
	swashplate_servo2_amps: number;
	swashplate_servo3_amps: number;
	fin_servo_amps: number;
	className: 'de.wuespace.telestion.project.daedalus2.messages.ServoAmps';
}

export interface Volts extends JavaMessage {
	rxsmVolts: number;
	bat1_volts: number;
	bat2_volts: number;
	rail3V3Volts: number;
	className: 'de.wuespace.telestion.project.daedalus2.messages.Volts';
}

export interface GPS extends JavaMessage {
	latitude: number;
	longitude: number;
	horizontalDilutionOfPrecision: number;
	altitude: number;
	quality: number;
	numberOfSatsUsed: number;
	className: 'de.wuespace.telestion.project.daedalus2.messages.Gps';
}

export interface Filter extends JavaMessage {
	velVertical: number;
	velVerticalInd: number;
	heightGround: number;
	rotorRotRate: number;
	bodyRotRate: number;
	className: 'de.wuespace.telestion.project.daedalus2.messages.Filter';
}

export interface Controller extends JavaMessage {
	id: number;
	bladePitch: number;
	finAngle: number;
	className: 'de.wuespace.telestion.project.daedalus2.messages.Controller';
}

export interface LidarServo extends JavaMessage {
	lidarServoOn: boolean;
	lidarServoOpen: boolean;
	className: 'de.wuespace.telestion.project.daedalus2.messages.LidarServo';
}

export interface SystemConfig extends JavaMessage {
	rxsmAllowed: boolean;
	bat1Allowed: boolean;
	bat2Allowed: boolean;
	className: 'de.wuespace.telestion.project.daedalus2.messages.SystemConfig';
}

export interface AvailableStatus extends JavaMessage {
	imuAccAvail: boolean;
	imuGyroAvail: boolean;
	baroAvail: boolean;
	vacuumBaroAvail: boolean;
	tachoRotAvail: boolean;
	servoAmpsAvail: boolean;
	batTempAvail: boolean;
	voltsAvail: boolean;
	className: 'de.wuespace.telestion.project.daedalus2.messages.AvailableStatus';
}

export interface SystemT extends MongoCollection {
	timeLocal: number;
	imu: IMU;
	baro: Barometer;
	tachoRotRate: number;
	servoAmps: ServoAmps;
	batTemp: number;
	batHeatingAmps: number;
	volts: Volts;
	gps: GPS;
	filter: Filter;
	Controller: Controller;
	currentState: number;
	lidarServo: LidarServo;
	systemConfig: SystemConfig;
	availableStatus: AvailableStatus;
	className: 'de.wuespace.telestion.project.daedalus2.messages.SystemT';
	datetime: {
		$date: string;
	};
}
