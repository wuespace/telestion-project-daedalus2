import { JsonSerializable } from '@wuespace/telestion-client-types';
import { ChartConnection } from './chart-connection';

export interface Options extends Record<string, JsonSerializable | undefined> {
	/**
	 * Connections to get data from.
	 */
	connections: ChartConnection[];

	/**
	 * The viewable period in milliseconds.
	 */
	period: number;

	/**
	 * The minimum possible value that should be displayed in the diagram.
	 * Optional. If not set, the maximum value in the current set is used.
	 */
	minimum?: number;

	/**
	 * The maximum possible value that should be displayed in the diagram.
	 * Optional. If not set, the minimum value in the current set is used.
	 */
	maximum?: number;

	/**
	 * Renders per second.
	 */
	rps: number;
}
