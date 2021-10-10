import {
	Divider,
	Flex,
	Heading,
	NumberField,
	View
} from '@adobe/react-spectrum';
import { useEffect, useMemo, useState } from 'react';
import {
	XAxis,
	YAxis,
	FlexibleXYPlot,
	AreaSeries,
	LineMarkSeries,
	DiscreteColorLegend
} from 'react-vis';
import {
	AggregationResult,
	AggregationType,
	useRequestTimeSeriesState
} from '../hooks';
import { Series, WidgetProps } from './widget-props';

function plot(ts: AggregationResult<['min', 'max', 'avg']>, specs: Series) {
	const prettyTS = ts
		?.map(ts => ({ x: ts[0], y: ts[1].avg }))
		.sort((a, b) => b.x - a.x)
		.slice(1, -1); // trim first and last value to avoid "floating averages"

	const prettyAreaTS = ts
		?.map(ts => ({ x: ts[0], y: ts[1].max, y0: ts[1].min }))
		.sort((a, b) => b.x - a.x)
		.slice(1, -1); // trim first and last value to avoid "floating averages"

	return [
		<LineMarkSeries
			data={prettyTS}
			color={specs.color}
			// @ts-ignore
			size={3}
		/>,
		specs.minMax && (
			<AreaSeries data={prettyAreaTS} color={specs.color} opacity={0.1} />
		)
	];
}

const aggregations: AggregationType[] = ['avg', 'min', 'max'];

export function Widget({
	series,
	title,
	maxY,
	minY,
	seconds,
	maxSamples
}: WidgetProps) {
	const [currentMaxY, setCurrentMaxY] = useState(maxY);
	const [currentMinY, setCurrentMinY] = useState(minY);
	const [fetch, ts] = useRequestTimeSeriesState(
		useMemo(
			() =>
				series.map(s => {
					return {
						from: () => Date.now() - (seconds + 2) * 1000,
						to: () => Date.now(),
						bucketSize: (seconds * 1000) / (maxSamples + 2),
						key: s.key
					};
				}),
			[maxSamples, seconds, series]
		),
		aggregations
	);

	useEffect(() => fetch(), [fetch]);

	// useEffect(() => console.log(ts1), [ts1]);
	useEffect(() => {
		const interval = setInterval(() => {
			fetch();
		}, 500);

		return () => clearInterval(interval);
	}, [fetch]);

	return (
		<View padding={'size-200'} height={'100%'}>
			<style>
				.rv-discrete-color-legend-item {'{'}
				color: inherit
				{'}'}
			</style>
			<Flex direction={'column'} height={'100%'}>
				<View paddingBottom={'size-200'}>
					<Heading level={3} marginTop={0}>
						{title || 'Simple Graph Widget'}
					</Heading>
					<Divider size="M" />
				</View>
				<Flex flexGrow={1}>
					<View flexGrow={1}>
						<FlexibleXYPlot
							xDomain={[Date.now() - (seconds + 1) * 1000, Date.now()]}
							yDomain={[currentMinY, currentMaxY]}
							xType={'time'}
						>
							{/*<HorizontalGridLines />*/}
							{series.map((s, i) => plot(ts[i] || [], s))}
							<XAxis
								title={'Time'}
								on0={
									(currentMinY <= 0 && 0 <= currentMaxY) ||
									(currentMaxY <= 0 && 0 <= currentMinY)
								}
								// tickFormat={value => new Date(value).toLocaleTimeString()}
								tickTotal={5}
							/>
							<YAxis />
						</FlexibleXYPlot>
					</View>
					<View flexShrink={0}>
						<DiscreteColorLegend
							items={series.map(s => ({
								title: s.key,
								color: s.color
							}))}
						/>
						<Flex>
							<NumberField
								value={currentMinY}
								onChange={setCurrentMinY}
								label={'Y Axis Minimum'}
								isQuiet={true}
								step={0.1}
							/>
							<NumberField
								value={currentMaxY}
								onChange={setCurrentMaxY}
								label={'Y Axis Maximum'}
								isQuiet={true}
								step={0.1}
							/>
						</Flex>
					</View>
				</Flex>
			</Flex>
		</View>
	);
}
