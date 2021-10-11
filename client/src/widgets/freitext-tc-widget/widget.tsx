import { useCallback, useEffect, useMemo, useState } from 'react';
import {
	Button,
	Divider,
	Flex,
	Form,
	Heading,
	Radio,
	RadioGroup,
	TextField,
	View
} from '@adobe/react-spectrum';
import { BaseRendererProps } from '@wuespace/telestion-client-types';

import { useTcSendFunction } from '../hooks';
import { WidgetProps } from './model';

enum TCState {
	IDLE,
	SENDING,
	SENT,
	ERROR
}

export function Widget({
	targets,
	title,
	channel
}: BaseRendererProps<WidgetProps>) {
	const targetKeys = useMemo(() => Object.keys(targets), [targets]);
	const [target, setTarget] = useState(targetKeys[0]);
	const [cmd, setCmd] = useState('');
	const [state, setState] = useState(TCState.IDLE);

	const sendTC = useTcSendFunction(channel);

	// Reset state to idle on input change
	useEffect(() => {
		setState(TCState.IDLE);
	}, [target, cmd]);

	// Warn if no confirmation was received after 5 seconds
	useEffect(() => {
		if (state === TCState.SENDING) {
			const warningTrigger = setTimeout(() => setState(TCState.ERROR), 5000);

			return () => clearTimeout(warningTrigger);
		}
	}, [state]);

	const onSubmit = useCallback(() => {
		setState(TCState.SENDING);
		sendTC(target, cmd, () => setState(TCState.SENT));
	}, [sendTC, cmd, target]);

	return (
		<View padding="size-200" width="100%">
			<Flex direction="column" width="100%">
				<Heading margin={0} level={3}>
					{title}
				</Heading>
				<Divider size="M" marginTop="size-100" />
				<>
					{state === TCState.SENT && (
						<View
							padding={'size-200'}
							borderRadius={'regular'}
							marginTop={'size-200'}
							backgroundColor={'positive'}
						>
							Command sent successfully.
						</View>
					)}
					{state === TCState.ERROR && (
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
					isDisabled={state === TCState.SENDING}
					onSubmit={e => {
						e.preventDefault();
						onSubmit();
					}}
					isRequired={true}
				>
					<TextField
						maxLength={255}
						isRequired={true}
						placeholder="camrec 1"
						label="Console Command"
						value={cmd}
						onChange={setCmd}
					/>
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
					<Button
						variant="cta"
						isDisabled={state !== TCState.IDLE || !cmd || !target}
						onPress={onSubmit}
					>
						{state === TCState.SENDING ? 'Sending' : 'Send TC'}
					</Button>
				</Form>
			</Flex>
		</View>
	);
}
