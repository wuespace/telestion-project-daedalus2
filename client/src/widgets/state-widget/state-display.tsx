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
	seedAState: State;
	seedBState: State;
	ejectorState: State;
}

export function StateDisplay({
	seedAState,
	seedBState,
	ejectorState
}: StateDisplayProps) {
	return (
		<View padding="size-200" width="100%">
			<Flex direction="column" alignItems="start">
				<Heading level={3} marginTop={0}>
					State Machine State
				</Heading>
				<Divider size="M" />

				<View width="100%" overflow={'auto'}>
					<Flex direction="row" width="100%" minWidth={500} gap={'size-200'}>
						<Flex direction="column" alignItems="start">
							<Heading level={2}>Seed A</Heading>
							<Text>Current State:</Text>

							<Flex gap="size-200" alignItems="center">
								<Content UNSAFE_style={{ fontSize: '1.5em' }}>
									{seedAState.currentState}
								</Content>
								<Badge special={seedAState.isSpecial}>
									{seedAState.tagName}
								</Badge>
							</Flex>

							<Divider size="M" marginY="size-200" />

							<Text>Next State:</Text>
							<Content UNSAFE_style={{ fontSize: '1.5em' }}>
								{seedAState.nextState}
							</Content>
						</Flex>

						<Flex direction="column" alignItems="start">
							<Heading level={2}>Seed B</Heading>
							<Text>Current State:</Text>

							<Flex gap="size-200" alignItems="center">
								<Content UNSAFE_style={{ fontSize: '1.5em' }}>
									{seedBState.currentState}
								</Content>
								<Badge special={seedBState.isSpecial}>
									{seedAState.tagName}
								</Badge>
							</Flex>

							<Divider size="M" marginY="size-200" />

							<Text>Next State:</Text>
							<Content UNSAFE_style={{ fontSize: '1.5em' }}>
								{seedBState.nextState}
							</Content>
						</Flex>

						<Flex direction="column" alignItems="start">
							<Heading level={2}>Ejector State</Heading>
							<Text>Current State:</Text>

							<Flex gap="size-200" alignItems="center">
								<Content UNSAFE_style={{ fontSize: '1.5em' }}>
									{ejectorState.currentState}
								</Content>
								<Badge special={ejectorState.isSpecial}>
									{ejectorState.tagName}
								</Badge>
							</Flex>

							<Divider size="M" marginY="size-200" />

							<Text>Next State:</Text>
							<Content UNSAFE_style={{ fontSize: '1.5em' }}>
								{ejectorState.nextState}
							</Content>
						</Flex>
					</Flex>
				</View>
			</Flex>
		</View>
	);
}
