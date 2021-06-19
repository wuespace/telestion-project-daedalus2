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
	stateSeed: State;
	stateEjector: State;
}

export function StateDisplay({ stateSeed, stateEjector }: StateDisplayProps) {
	return (
		<View padding="size-200">
			<Flex direction="column" alignItems="start">
				<Heading level={3} marginTop={0}>
					State Machine State
				</Heading>

				<Divider size="M" />

				<Heading level={2}>Seed State</Heading>
				<Text marginTop="size-200">Current State:</Text>

				<Flex gap="size-200" marginTop="size-100" alignItems="center">
					<Content UNSAFE_style={{ fontSize: '1.5em' }}>
						{stateSeed.currentState}
					</Content>
					<Badge special={stateSeed.isSpecial}>{stateSeed.tagName}</Badge>
				</Flex>

				<Divider size="M" marginY="size-200" />

				<Text>Next State:</Text>
				<Content marginTop="size-100" UNSAFE_style={{ fontSize: '1.5em' }}>
					{stateSeed.nextState}
				</Content>
			</Flex>

			<Divider size="M" />

			<Heading level={2}>Ejector State</Heading>
			<Text marginTop="size-200">Current State:</Text>

			<Flex gap="size-200" marginTop="size-100" alignItems="center">
				<Content UNSAFE_style={{ fontSize: '1.5em' }}>
					{stateEjector.currentState}
				</Content>
				<Badge special={stateEjector.isSpecial}>{stateEjector.tagName}</Badge>
			</Flex>

			<Divider size="M" marginY="size-200" />

			<Text>Next State:</Text>
			<Content marginTop="size-100" UNSAFE_style={{ fontSize: '1.5em' }}>
				{stateEjector.nextState}
			</Content>
		</View>
	);
}
