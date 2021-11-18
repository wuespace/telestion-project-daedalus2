import { FormEvent, useMemo, useState } from 'react';
import {
	Divider,
	Flex,
	Form,
	Heading,
	Item,
	Picker,
	Radio,
	RadioGroup,
	View
} from '@adobe/react-spectrum';
import { BaseRendererProps } from '@wuespace/telestion-client-types';

import { TCState } from '../../model/tc-state';
import { TCSendButton } from '../components/tc-send-button';
import { WidgetProps } from './model';
import { states } from '../../model/state';

const cmdPrefix = 'statetrans';
const initialStateId = 0;
const statesList = Object.entries(states).map(([id, state]) => ({
	id,
	...state
}));

function preventDefault(event: FormEvent) {
	event.preventDefault();
}

export function Widget({ title, targets }: BaseRendererProps<WidgetProps>) {
	const targetKeys = useMemo(() => Object.keys(targets), [targets]);
	const [target, setTarget] = useState(targetKeys[0]);
	const [tcState, setTCState] = useState(TCState.IDLE);
	const [stateId, setStateId] = useState(initialStateId);

	return (
		<View padding="size-200" width="100%">
			<Flex direction="column" width="100%">
				<Heading margin={0} level={3}>
					{title}
				</Heading>
				<Divider size="M" marginTop="size-100" />
				<>
					{tcState === TCState.SENT && (
						<View
							padding={'size-200'}
							borderRadius={'regular'}
							marginTop={'size-200'}
							backgroundColor={'positive'}
						>
							Command sent successfully.
						</View>
					)}
					{tcState === TCState.ERROR && (
						<View
							padding={'size-200'}
							borderRadius={'regular'}
							marginTop={'size-200'}
							backgroundColor={'negative'}
						>
							Received no confirmation about message being sent. This doesn't
							necessarily mean it wasn't sent!
						</View>
					)}
				</>
				<Form
					maxWidth="100%"
					isDisabled={tcState === TCState.SENDING}
					onSubmit={preventDefault}
					isRequired={true}
				>
					<Picker
						label="New state"
						items={statesList}
						selectedKey={stateId}
						defaultSelectedKey={initialStateId}
						disallowEmptySelection
						onSelectionChange={setStateId as any}
					>
						{state => <Item key={state.id}>{state.name}</Item>}
					</Picker>

					<RadioGroup
						label="Target"
						value={target}
						onChange={setTarget}
						isRequired={true}
					>
						{targetKeys.map(key => (
							<Radio key={key} value={key}>
								{targets[key]}
							</Radio>
						))}
					</RadioGroup>

					<TCSendButton
						onStateChange={setTCState}
						command={`${cmdPrefix} ${stateId}`}
						target={target as any}
						variant="cta"
					>
						Change state
					</TCSendButton>
				</Form>
			</Flex>
		</View>
	);
}
