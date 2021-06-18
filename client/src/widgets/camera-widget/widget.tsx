import {
	ActionButton,
	Divider,
	Flex,
	Heading,
	Text
} from '@adobe/react-spectrum';
import { GenericProps } from '@wuespace/telestion-client-types';
import Camera from '@spectrum-icons/workflow/Camera';
import Circle from '@spectrum-icons/workflow/Circle';
import Stop from '@spectrum-icons/workflow/Stop';
import Light from '@spectrum-icons/workflow/Light';
import { useTcSendFunction } from '../hooks/use-tc-send-function';

export function Widget(props: GenericProps) {
	const sendTC = useTcSendFunction(props.tcChannel?.toString() || '');

	return (
		<Flex margin={'size-200'} direction={'column'}>
			<Heading margin={0} level={3}>
				{props.title}
			</Heading>
			<Divider size={'M'} marginTop={'size-100'} />
			<Heading level={4}>
				<Flex gap={'size-100'} alignItems={'center'}>
					<Camera size="S" />
					<Text>Camera Recording</Text>
				</Flex>
			</Heading>
			<Flex>
				<div>
					<ActionButton onPress={() => sendTC('camrec 1')}>
						<Circle color="negative" />
						<Text>Start</Text>
					</ActionButton>
					<ActionButton onPress={() => sendTC('camrec 0')}>
						<Stop />
						<Text>Stop</Text>
					</ActionButton>
				</div>
			</Flex>
			<Heading level={4}>
				<Flex gap={'size-100'} alignItems={'center'}>
					<Light size="S" />
					<Text>Test LED</Text>
				</Flex>
			</Heading>
			<Flex>
				<ActionButton onPress={() => sendTC('enableTestLED')}>
					Enable
				</ActionButton>
				<ActionButton onPress={() => sendTC('disableTestLED')}>
					Disable
				</ActionButton>
			</Flex>
		</Flex>
	);
}
