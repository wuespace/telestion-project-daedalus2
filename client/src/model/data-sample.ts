import { JsonSerializable } from '@wuespace/telestion-client-types';

export interface DataSample extends Record<string, JsonSerializable> {
	avg: number;
	min: number;
	max: number;
	timeStamp: number;
}
