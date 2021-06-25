import {
	ChannelAddress,
	JsonSerializable
} from '@wuespace/telestion-client-types';

export interface PredefinedOptions
	extends Record<string, JsonSerializable | undefined> {
	/**
	 * The channels the user can use to setup the connections of the widgets.
	 */
	channels: Record<ChannelAddress, string>;
}
