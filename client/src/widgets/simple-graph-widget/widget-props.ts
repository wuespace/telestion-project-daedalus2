import {
	GenericProps,
	JsonSerializable
} from '@wuespace/telestion-client-types';

export interface Series extends Record<string, JsonSerializable> {
	key: string;
	color: `hsl(${string}, 100%, 50%)`;
	minMax: boolean;
}

export interface WidgetProps extends GenericProps {
	series: Series[];
	title: string;
	seconds: number;
	maxSamples: number;
	maxY: number;
	minY: number;
}
