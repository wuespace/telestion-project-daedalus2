import { ChannelAddress } from '@wuespace/telestion-client-types';

export interface Options {
	/**
	 * The address the data samples come from.
	 */
	address: ChannelAddress;

	/**
	 * The color of average path.
	 */
	fill: string;

	/**
	 * The viewable period in milliseconds.
	 */
	period: number;

	/**
	 * The minimum possible value that should be displayed in the diagram.
	 */
	minimum: number;

	/**
	 * The maximum possible value that should be displayed in the diagram.
	 */
	maximum: number;
}
