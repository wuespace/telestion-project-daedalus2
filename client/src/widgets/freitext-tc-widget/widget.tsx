import { useCallback, useState } from 'react';
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
import { useLogger } from '@wuespace/telestion-client-core';
import { BaseRendererProps } from '@wuespace/telestion-client-types';

import { useTcSendFunction } from '../hooks/use-tc-send-function';
import { WidgetProps } from './model';

export function Widget({
	targetLabels,
	title
}: BaseRendererProps<WidgetProps>) {
	const logger = useLogger('Freitext TC Widget');

	const [target, setTarget] = useState<string>(Object.keys(targetLabels)[0]);
	const [cmd, setCmd] = useState<string>('');

	const sendTC = useTcSendFunction(target);

	const onSubmit = useCallback(() => {
		logger.debug('Sending TC', cmd, 'to', target);
		sendTC(cmd);
	}, [sendTC, cmd, logger, target]);

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading margin={0} level={3}>
					{title}
				</Heading>
				<Divider size="M" marginTop="size-100" />
				<Flex>
					<Form
						maxWidth="size-4600"
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
							{Object.keys(targetLabels).map(key => (
								<Radio key={key} value={key}>
									{targetLabels[key]}
								</Radio>
							))}
						</RadioGroup>
						<Button variant="cta" onPress={onSubmit}>
							Send TC
						</Button>
					</Form>
				</Flex>
			</Flex>
		</View>
	);
}
