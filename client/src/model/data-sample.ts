import { JsonSerializable } from '@wuespace/telestion-client-types';

export interface DataSample extends Record<string, JsonSerializable> {
	/**
	 * The average value over the aggregated time span.
	 */
	avg: number;
	/**
	 * The minimum value over the aggregated time span.
	 */
	min: number;
	/**
	 * The maximum value over the aggregated time span.
	 */
	max: number;
	/**
	 * The latest (last) value in the aggregation
	 */
	last: number;
	/**
	 * The time of the last data sample in the aggregation.
	 */
	time: number;
}
