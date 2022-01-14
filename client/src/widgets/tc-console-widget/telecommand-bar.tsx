import { Dispatch, SetStateAction, useState } from 'react';
import {
	Flex,
	Form,
	Item,
	Picker,
	TextField,
	View
} from '@adobe/react-spectrum';
import { TCSendButton } from '../components/tc-send-button';
import { ConsoleDefinition } from './model';
import { TCState } from '../../model/tc-state';

export interface TelecommandBarProps {
	definitions: ConsoleDefinition[];
	target: string;
	tcState: TCState;
	setTarget: Dispatch<SetStateAction<string>>;
	setTcState: Dispatch<SetStateAction<TCState>>;
}

export function TelecommandBar({
	definitions,
	target,
	tcState,
	setTarget,
	setTcState
}: TelecommandBarProps) {
	const [telecommand, setTelecommand] = useState('');

	return (
		<View>
			<Form
				maxWidth="100%"
				width="100%"
				isDisabled={tcState === TCState.SENDING}
				onSubmit={e => {
					e.preventDefault();
				}}
				isRequired={true}
			>
				<Flex
					direction="row"
					gap="size-100"
					justifyContent="space-between"
					alignItems="end"
				>
					<Picker
						label="Target (synced to tab)"
						items={definitions}
						selectedKey={target}
						isDisabled={tcState === TCState.SENDING}
						// @ts-ignore
						onSelectionChange={setTarget}
					>
						{item => <Item key={item.name}>{item.title}</Item>}
					</Picker>
					<TextField
						label="Send command"
						placeholder="test_led 1"
						isRequired
						isDisabled={tcState === TCState.SENDING}
						value={telecommand}
						onChange={setTelecommand}
						flexGrow={1}
					/>
					<TCSendButton
						onStateChange={setTcState}
						command={telecommand}
						target={target as any}
						variant="cta"
					>
						Send TC&hellip;
					</TCSendButton>
				</Flex>
			</Form>
		</View>
	);
}
