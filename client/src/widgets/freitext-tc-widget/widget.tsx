import { useMemo, useState } from 'react';
import {
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
import { WidgetProps } from './model';
import { TCState } from '../../model/tc-state';
import { TCSendButton } from '../components/tc-send-button';

export function Widget({ targets, title }: BaseRendererProps<WidgetProps>) {
	const targetKeys = useMemo(() => Object.keys(targets), [targets]);
	const [target, setTarget] = useState(targetKeys[0]);
	const [cmd, setCmd] = useState('');
	const [state, setState] = useState(TCState.IDLE);

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
					<TCSendButton
						onStateChange={s => setState(s)}
						command={cmd}
						target={target as any}
						variant={'cta'}
					>
						Send TC&hellip;
					</TCSendButton>
				</Form>
			</Flex>
		</View>
	);
}
