import { useMemo } from 'react';
import { Divider, Flex, Text, View } from '@adobe/react-spectrum';
import { useCachedLatest } from '../hooks';
import { ConsoleDefinition } from './model';

const seedTcCounterPostfix = 'SEED_HEARTBEAT/telecommand_cnt';
const ejectorTcCounterPostfix = 'EJECTOR_HEARTBEAT/telecommand_cnt';

export interface TcCounterProps {
	definitions: ConsoleDefinition[];
}

export function TcCounterBar({ definitions }: TcCounterProps) {
	const keys = useMemo(
		() =>
			definitions.map(definition =>
				definition.name === 'ejector'
					? `latest/${definition.name}/${ejectorTcCounterPostfix}`
					: `latest/${definition.name}/${seedTcCounterPostfix}`
			),
		[definitions]
	);

	const values = useCachedLatest<number[]>(keys);

	return (
		<View paddingY="size-100">
			<Flex direction="row" gap="size-100">
				{definitions.map((definition, index) => (
					<>
						<Text>
							{definition.title}:{' '}
							{values[index] === null ? 'N/A' : values[index]}
						</Text>
						{/* add divider for all but last entry */}
						{index < definitions.length - 1 && (
							<Divider orientation="vertical" size="S" />
						)}
					</>
				))}
			</Flex>
		</View>
	);
}
