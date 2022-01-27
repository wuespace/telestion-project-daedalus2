import { useState } from 'react';
import {
	ActionButton,
	Checkbox,
	Flex,
	TextField,
	View
} from '@adobe/react-spectrum';
import { showDialog } from '@wuespace/telestion-client-common';

interface DeliveryOptions {
	/**
	 * Request a transmission receipt from the target.
	 */
	requestReceipt: boolean;
	/**
	 * Resend the telecommand if a failure has happened during transmission.
	 */
	resendOnFailure: boolean;
	/**
	 * Force the telecommand to be executed on the target.
	 */
	forceExecution: boolean;
}

const defaultOptions: DeliveryOptions = {
	requestReceipt: false,
	resendOnFailure: false,
	forceExecution: false
};

function sendTC(command: string, options: DeliveryOptions): void {
	console.log('Send telecommand:', command, ', with options:', options);
}

export function Widget() {
	const [options, setOptions] = useState(defaultOptions);
	const [command, setCommand] = useState('default-command');

	const onOptions = () => {
		showDialog('options-tc', {
			title: 'Telecommand options',
			content: (state, setState) => (
				<Flex direction="column" gap="size-100">
					<Checkbox
						isSelected={state.requestReceipt}
						onChange={requestReceipt => setState({ requestReceipt })}
					>
						Request a transmission receipt from the target
					</Checkbox>
					<Checkbox
						isSelected={state.resendOnFailure}
						onChange={resendOnFailure => setState({ resendOnFailure })}
					>
						Resend the telecommand if a failure has happened during transmission
					</Checkbox>
					<Checkbox
						isSelected={state.forceExecution}
						onChange={forceExecution => setState({ forceExecution })}
					>
						Force the telecommand to be executed on the target
					</Checkbox>
				</Flex>
			),
			initialState: options
		}).then(setOptions);
	};

	const onSend = () => {
		showDialog('custom-tc', {
			title: 'Send telecommand',
			content:
				'Are you sure you want to send the telecommand "' + command + '"?',
			initialState: undefined
		}).then(() => sendTC(command, options));
	};

	return (
		<View padding="size-200">
			<Flex direction="column" gap="size-100">
				<Flex direction="row" gap="size-100" alignItems="end">
					<TextField
						label="Telecommand"
						width="100%"
						value={command}
						onChange={setCommand}
					/>
					<ActionButton flexShrink={0} onPress={onOptions}>
						Options
					</ActionButton>
				</Flex>
				<ActionButton onPress={onSend}>Send</ActionButton>
			</Flex>
		</View>
	);
}
