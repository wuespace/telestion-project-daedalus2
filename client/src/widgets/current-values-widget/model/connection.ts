import {
	ChannelAddress,
	JsonSerializable
} from '@wuespace/telestion-client-types';

export interface Connection
	extends Record<string, JsonSerializable | undefined> {
	/**
	 * The address the datasets come from.
	 */
	address: ChannelAddress;

	/**
	 * The title of the dataset.
	 */
	title: string;

	/**
	 * The precision to display in digits.
	 * Defaults to `2`.
	 */
	precision?: number;

	/**
	 * Renders per second.
	 */
	rps: number;
}
