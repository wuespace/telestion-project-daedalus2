import { Content, Divider, Flex, Heading, Text } from '@adobe/react-spectrum';
import { State } from '../../../model/state';
import { Badge } from './badge';

export interface StateRendererProps {
	title: string;
	state: State;
}

export function StateRenderer({ title, state }: StateRendererProps) {
	return (
		<Flex width="100%" direction="column" alignItems="start">
			<Heading level={2}>{title}</Heading>
			<Text>Current State:</Text>

			<Flex gap="size-200" alignItems="center">
				<Content UNSAFE_style={{ fontSize: '1.5em' }}>{state.name}</Content>
				<Badge isSpecial={state.isSpecial}>{state.stage}</Badge>
			</Flex>

			<Divider size="M" marginY="size-200" />

			<Text>Next State:</Text>
			<Content UNSAFE_style={{ fontSize: '1.5em' }}>
				{state.major || state.next}
			</Content>
			<Content UNSAFE_style={{ fontSize: '0.75em' }}>{state.next}</Content>
		</Flex>
	);
}
