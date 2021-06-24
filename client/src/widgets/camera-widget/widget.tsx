import {
	ActionButton,
	Divider,
	Flex,
	Heading,
	Text,
	View
} from '@adobe/react-spectrum';
import { BaseRendererProps } from '@wuespace/telestion-client-types';

import Camera from '@spectrum-icons/workflow/Camera';
import Circle from '@spectrum-icons/workflow/Circle';
import Stop from '@spectrum-icons/workflow/Stop';
import Light from '@spectrum-icons/workflow/Light';

import { useTcSendFunction } from '../hooks';
import { WidgetProps } from './model';

export function Widget({
	channel,
	title,
	target
}: BaseRendererProps<WidgetProps>) {
	const sendTC = useTcSendFunction(channel);

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading margin={0} level={3}>
					{title}
				</Heading>
				<Divider size="M" marginTop="size-100" />
				<Heading level={4}>
					<Flex gap="size-100" alignItems="center">
						<Camera size="S" />
						<Text>Camera Recording</Text>
					</Flex>
				</Heading>
				<Flex>
					<div>
						<ActionButton onPress={() => sendTC(target, 'camrec 1')}>
							<Circle color="negative" />
							<Text>Start</Text>
						</ActionButton>
						<ActionButton onPress={() => sendTC(target, 'camrec 0')}>
							<Stop />
							<Text>Stop</Text>
						</ActionButton>
					</div>
				</Flex>
				<Heading level={4}>
					<Flex gap="size-100" alignItems="center">
						<Light size="S" />
						<Text>Test LED</Text>
					</Flex>
				</Heading>
				<Flex>
					<ActionButton onPress={() => sendTC(target, 'enableTestLED')}>
						Enable
					</ActionButton>
					<ActionButton onPress={() => sendTC(target, 'disableTestLED')}>
						Disable
					</ActionButton>
				</Flex>
			</Flex>
		</View>
	);
}
