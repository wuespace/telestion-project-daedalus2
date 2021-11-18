import { Heading, View, Divider, Flex } from '@adobe/react-spectrum';
import { State } from '../../../model/state';
import { StateRenderer } from './state-renderer';

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
						<StateRenderer title="Seed A" state={seedAState} />
						<StateRenderer title="Seed B" state={seedBState} />
						<StateRenderer title="Ejector State" state={ejectorState} />
					</Flex>
				</View>
			</Flex>
		</View>
	);
}
