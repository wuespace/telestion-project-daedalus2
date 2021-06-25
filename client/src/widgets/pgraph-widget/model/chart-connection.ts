import {
	ChannelAddress,
	JsonSerializable,
	SpectrumColor
} from '@wuespace/telestion-client-types';

export interface ChartConnection extends Record<string, JsonSerializable> {
	/**
	 * The unique id of the chart connection.
	 * (should be automatically determined)
	 */
	id: number;

	/**
	 * The address the datasets come from.
	 */
	address: ChannelAddress;

	/**
	 * The title of the dataset.
	 */
	title: string;

	/**
	 * The color of average path.
	 */
	stroke: SpectrumColor;

	/**
	 * The color of the min-max background.
	 */
	fill: SpectrumColor;
}
