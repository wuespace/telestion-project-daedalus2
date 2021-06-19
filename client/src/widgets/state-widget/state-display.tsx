import {
	Heading,
	View,
	Content,
	Divider,
	Flex,
	Text
} from '@adobe/react-spectrum';
import { Badge } from './badge';
import { State } from './model';

export interface StateDisplayProps {
	state: State;
}

export function StateDisplay({ state }: StateDisplayProps) {
	return (
		<View padding="size-200">
			<Flex direction="column" alignItems="start">
				<Heading level={3} marginTop={0}>
					State Machine State
				</Heading>

				<Divider size="M" />

				<Text marginTop="size-200">Current State:</Text>

				<Flex gap="size-200" marginTop="size-100" alignItems="center">
					<Content UNSAFE_style={{ fontSize: '1.5em' }}>
						{state.currentState}
					</Content>
					<Badge special={state.isSpecial}>{state.tagName}</Badge>
				</Flex>

				<Divider size="M" marginY="size-200" />

				<Text>Next State:</Text>
				<Content marginTop="size-100" UNSAFE_style={{ fontSize: '1.5em' }}>
					{state.nextState}
				</Content>
			</Flex>
		</View>
	);
}
