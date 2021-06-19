import { JsonSerializable } from '@wuespace/telestion-client-types';
import { DataMessage } from '../channels';

export type FlightStateClassName =
	'de.jvpichowski.rocketsound.messages.base.FlightState';

export interface FlightStateData extends Record<string, JsonSerializable> {
	state: number;
	name: string;
	className: FlightStateClassName;
}

export type FlightStateMessage = DataMessage<
	FlightStateData,
	FlightStateClassName
>;
