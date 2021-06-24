import { useCallback, useMemo, useState } from 'react';
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

export function Widget({
	targets,
	title,
	channel
}: BaseRendererProps<WidgetProps>) {
	const targetKeys = useMemo(() => Object.keys(targets), [targets]);
	const [target, setTarget] = useState(targetKeys[0]);
	const [cmd, setCmd] = useState('');

	const sendTC = useTcSendFunction(channel);

	const onSubmit = useCallback(
		() => sendTC(target, cmd),
		[sendTC, cmd, target]
	);

	return (
		<View padding="size-200" width="100%">
			<Flex direction="column" width="100%">
				<Heading margin={0} level={3}>
					{title}
				</Heading>
				<Divider size="M" marginTop="size-100" />
				<Form
					maxWidth="100%"
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
					<Button variant="cta" onPress={onSubmit}>
						Send TC
					</Button>
				</Form>
			</Flex>
		</View>
	);
}
