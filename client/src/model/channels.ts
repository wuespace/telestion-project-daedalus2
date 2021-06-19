import { JsonSerializable } from '@wuespace/telestion-client-types';

/**
 * Channel to save data to the database
 */
export const MONGODB_SAVE =
	'org.telestion.core.database.MongoDatabaseService/in#save';

/**
 * Channel where the database publishes the recently saved data
 */
export const MONGODB_NEW =
	'org.telestion.core.database.MongoDatabaseService/out#save';

export const MONGODB_FIND =
	'org.telestion.core.database.MongoDatabaseService/in#find';

export const NineDOF =
	MONGODB_NEW + '/de.jvpichowski.rocketsound.messages.base.NineDofData';
export const FlightState =
	MONGODB_NEW + '/de.jvpichowski.rocketsound.messages.base.FlightState';
export const GpsData =
	MONGODB_NEW + '/de.jvpichowski.rocketsound.messages.base.GpsData';
export const Amplitude =
	MONGODB_NEW + '/de.jvpichowski.rocketsound.messages.sound.Amplitude';
export const Spectrum =
	MONGODB_NEW + '/de.jvpichowski.rocketsound.messages.sound.Spectrum';
export const BaroData =
	MONGODB_NEW + '/de.jvpichowski.rocketsound.messages.base.BaroData';
export const Velocity =
	MONGODB_NEW + '/de.jvpichowski.rocketsound.messages.base.Velocity';

export interface DataMessage<T extends JsonSerializable, C extends string>
	extends Record<string, JsonSerializable> {
	dataType: C;
	result: T[];
	className: 'org.telestion.core.database.DbResponse';
}
