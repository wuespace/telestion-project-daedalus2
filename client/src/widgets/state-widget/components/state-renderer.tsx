import { Content, Divider, Flex, Heading, Text } from '@adobe/react-spectrum';
import { getState } from '../../../model/state';
import { stages } from '../../../model/stage';
import { Badge } from './badge';
import { formatDeltaTime } from '../../lib/format-delta-time';

export interface StateRendererProps {
	title: string;
	subsystem: 'ejector' | 'seed';
	stateId: number;
	time: number;
}

function renderState(id: number | string, name: string): string {
	return id < 0 || id === 1 ? name : `${name} (${id})`;
}

export function StateRenderer({
	title,
	subsystem,
	stateId,
	time
}: StateRendererProps) {
	const state = getState(subsystem, stateId);
	const stage = stages[state.stage];

	const nextStateId = state.next;
	const nextMajorStateId = state.major === undefined ? state.next : state.major;
	const isSame = nextStateId === nextMajorStateId;

	const nextState = getState(subsystem, nextStateId);
	const nextMajorState = getState(subsystem, nextMajorStateId);

	return (
		<Flex width="100%" direction="column" alignItems="start">
			<Flex
				width="100%"
				direction="row"
				alignItems="center"
				justifyContent="space-between"
			>
				<Heading level={2}>{title}</Heading>
				<code>{time ? formatDeltaTime(Date.now() - time) : 'Waiting'}</code>
			</Flex>
			<Text>Current State:</Text>

			<Flex gap="size-200" alignItems="center">
				<Content UNSAFE_style={{ fontSize: '1.5em' }}>
					{renderState(stateId, state.name)}
				</Content>
				<Badge isSpecial={stage.isSpecial || state.isSpecial}>
					{stage.name}
				</Badge>
			</Flex>

			<Divider size="M" marginY="size-200" />

			<Text>Next State:</Text>
			<Content UNSAFE_style={{ fontSize: '1.5em' }}>
				{renderState(nextMajorStateId, nextMajorState.name)}
			</Content>
			{isSame ? (
				<></>
			) : (
				<Content UNSAFE_style={{ fontSize: '0.75em' }}>
					{renderState(nextStateId, nextState.name)}
				</Content>
			)}
		</Flex>
	);
}
