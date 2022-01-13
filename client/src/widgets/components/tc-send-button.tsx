import { useEffect } from 'react';
import { useStateBasedTCSend } from '../hooks/use-state-based-tc-send';
import {
	ActionButton,
	AlertDialog,
	Button,
	DialogTrigger
} from '@adobe/react-spectrum';
import { TCState } from '../../model/tc-state';
import { TcSendButtonProps } from './tc-send-button-props';

/**
 * A button that handles sending tele commands
 */
export function TCSendButton({
	variant = 'action',
	target,
	command,
	isQuiet = false,
	isDisabled = false,
	children,
	onStateChange
}: TcSendButtonProps): JSX.Element {
	const { state, setState, onSend } = useStateBasedTCSend(
		'tc-sender',
		target,
		command
	);

	const TCButton: any = (props: any) =>
		variant === 'action' ? (
			<ActionButton {...props} />
		) : (
			<Button variant={variant} {...props} />
		);

	useEffect(() => {
		if (onStateChange) onStateChange(state, setState);
	}, [state, onStateChange, setState]);

	return (
		<DialogTrigger>
			<TCButton
				isDisabled={
					isDisabled ||
					(state !== TCState.IDLE && state !== TCState.SENT) ||
					!command ||
					!target
				}
				isQuiet={isQuiet}
			>
				{children}
				{state === TCState.SENDING && ' (sending)'}
			</TCButton>
			<AlertDialog
				title={'Send Telecommand'}
				variant={'confirmation'}
				primaryActionLabel={'Send Telecommand now'}
				onPrimaryAction={onSend}
				isPrimaryActionDisabled={isDisabled}
				secondaryActionLabel={'Cancel'}
			>
				<p>Are you sure you want to send this command to {target}?</p>
				<pre>
					<code>{command}</code>
				</pre>
			</AlertDialog>
		</DialogTrigger>
	);
}
