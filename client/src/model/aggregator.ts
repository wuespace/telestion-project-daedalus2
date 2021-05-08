import { JsonSerializable } from '@wuespace/telestion-client-types';

export interface Aggregation {
	min: number;
	avg: number;
	max: number;
	last: number;
}

export type Aggregated<T extends JsonSerializable> = {
	[key in keyof T]: T[key] extends JsonSerializable
		? Aggregated<T[key]>
		: T[key] extends number
		? Aggregation
		: T[key];
};
