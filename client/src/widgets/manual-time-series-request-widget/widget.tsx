import {
	Button,
	Checkbox,
	CheckboxGroup,
	Divider,
	Flex,
	Heading,
	TextField,
	View
} from '@adobe/react-spectrum';
import { AggregationType, useRequestTimeSeriesState } from '../hooks';
import { useMemo, useState } from 'react';

export function Widget() {
	const [key, setKey] = useState('ts/systemT/timeLocal');
	const [aggregations, setAggregations] = useState<AggregationType[]>([]);

	const requests = useMemo(
		() => [
			{
				key,
				from: 0,
				to: () => Date.now(),
				bucketSize: 5000
			}
		],
		[key]
	);

	const [request, [value]] = useRequestTimeSeriesState(requests, aggregations);

	return (
		<View padding={'size-200'}>
			<Heading level={3} marginTop={0}>
				manualTimeSeriesRequestWidget widget
			</Heading>
			<Divider size="S" />
			<Flex marginTop="size-200" gap="size-200" direction="column">
				<TextField label="Redis Key" value={key} onChange={setKey} />
				<CheckboxGroup
					value={aggregations}
					onChange={a => setAggregations(a as AggregationType[])}
				>
					<Checkbox value="avg">
						Average (<code>avg</code>)
					</Checkbox>
					<Checkbox value="min">
						Minimum (<code>min</code>)
					</Checkbox>
					<Checkbox value="max">
						Maximum (<code>max</code>)
					</Checkbox>
					<Checkbox value="count">
						Count (<code>count</code>)
					</Checkbox>
					<Checkbox value="first">
						First (<code>first</code>)
					</Checkbox>
					<Checkbox value="last">
						Last (<code>last</code>)
					</Checkbox>
				</CheckboxGroup>
				<Button alignSelf={'start'} variant="cta" onPress={() => request()}>
					Request
				</Button>
				<Heading level={4}>Result</Heading>
				<pre>{JSON.stringify(value, null, 2)}</pre>
			</Flex>
		</View>
	);
}
