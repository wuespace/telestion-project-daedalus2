import {
	View,
	Flex,
	Heading,
	Divider,
	Grid,
	repeat,
	minmax
} from '@adobe/react-spectrum';
import { OverflowFix } from '@wuespace/telestion-client-common';
import {
	BaseRendererProps,
	JsonSerializable
} from '@wuespace/telestion-client-types';

import { Connection, WidgetProps } from './model';
import { LiveWidget } from './components/live-widget';
import { useCachedLatest } from '../hooks';

type FilledConnections = ({
	value?: JsonSerializable;
	time?: number;
} & Connection)[];

export function Widget({ title, connections }: BaseRendererProps<WidgetProps>) {
	const values = useCachedLatest(connections.map(conn => conn.address));
	const times = useCachedLatest<number[]>(
		connections.map(conn => conn.address.replace('latest', 'latest-time'))
	);

	const niceConnections = connections.reduce<Record<string, FilledConnections>>(
		(previousValue, currentValue, index) => {
			const previousValueElement =
				previousValue[currentValue.address.substr(7).split('/')[0] || ''];

			const connections1: FilledConnections = [
				...(previousValueElement || []),
				{
					...currentValue,
					value: values[index],
					time: times[index]
				}
			].sort((a, b) => a.title.localeCompare(b.title));

			return {
				...previousValue,
				[currentValue.address.substr(7).split('/')[0]]: connections1
			};
		},
		{}
	);

	return (
		<View width="100%" height="100%">
			<Flex direction="column" width="100%" height="100%">
				<Heading
					flexGrow={0}
					level={3}
					margin="size-200"
					marginBottom="size-100"
				>
					{title}
				</Heading>
				<Divider size="S" />
				<OverflowFix>
					{Object.entries(niceConnections).map(([title, connections]) => (
						<div key={title}>
							<Heading level={4} marginX={'size-200'}>
								{title}
							</Heading>
							<Grid
								columns={repeat('auto-fit', minmax('size-4600', '1fr'))}
								justifyContent="center"
								autoRows={'auto'}
								gap="size-100"
								margin="size-100"
							>
								{connections.map((connection, index) => (
									<LiveWidget
										key={`${index}-${connection.title}`}
										value={connection.value}
										title={connection.title}
										time={connection.time}
									/>
								))}
							</Grid>
						</div>
					))}
				</OverflowFix>
			</Flex>
		</View>
	);
}
