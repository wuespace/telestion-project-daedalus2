import { Divider, Flex, Heading, Text, View } from '@adobe/react-spectrum';
import { BaseRendererProps } from '@wuespace/telestion-client-types';

import CameraIcon from '@spectrum-icons/workflow/Camera';
import CircleIcon from '@spectrum-icons/workflow/Circle';
import StopIcon from '@spectrum-icons/workflow/Stop';
import LightIcon from '@spectrum-icons/workflow/Light';
import TemperatureIcon from '@spectrum-icons/workflow/Temperature';

import { WidgetProps } from './model';
import { TCSendButton } from '../components/tc-send-button';
import { useOuterTcSendButtonState } from '../hooks/use-outer-tc-send-button-state';

export function Widget({ title, target }: BaseRendererProps<WidgetProps>) {
	const { messageState, onStateChange } = useOuterTcSendButtonState();

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading margin={0} level={3}>
					{title}
				</Heading>
				<Divider size="M" marginTop="size-100" />
				<>
					{messageState === 'success' && (
						<View
							padding={'size-200'}
							borderRadius={'regular'}
							marginTop={'size-200'}
							backgroundColor={'positive'}
						>
							Command sent successfully.
						</View>
					)}
					{messageState === 'error' && (
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
				<Heading level={4}>
					<Flex gap="size-100" alignItems="center">
						<CameraIcon size="S" />
						<Text>Camera Recording</Text>
					</Flex>
				</Heading>
				<Flex>
					<div>
						<TCSendButton
							command={'camrec 1'}
							target={target as any}
							onStateChange={onStateChange}
						>
							<CircleIcon color="negative" />
							<Text>Start</Text>
						</TCSendButton>
						<TCSendButton
							command={'camrec 0'}
							target={target as any}
							onStateChange={onStateChange}
						>
							<StopIcon />
							<Text>Stop</Text>
						</TCSendButton>
					</div>
				</Flex>
				<Heading level={4}>
					<Flex gap="size-100" alignItems="center">
						<LightIcon size="S" />
						<Text>Ping</Text>
					</Flex>
				</Heading>
				<Flex>
					<TCSendButton
						command={'pinglora'}
						target={target as any}
						onStateChange={onStateChange}
					>
						Ping LoRa
					</TCSendButton>
					<TCSendButton
						command={'pingiridium'}
						target={target as any}
						onStateChange={onStateChange}
					>
						Ping Iridium
					</TCSendButton>
				</Flex>
				<Heading level={4}>
					<Flex gap="size-100" alignItems="center">
						<TemperatureIcon size="S" />
						<Text>Battery heating</Text>
					</Flex>
				</Heading>
				<Flex>
					<TCSendButton
						command={'batheaten 0'}
						target={target as any}
						onStateChange={onStateChange}
					>
						Turn off
					</TCSendButton>
					<TCSendButton
						command={'batheaten -1'}
						target={target as any}
						onStateChange={onStateChange}
					>
						Enable Automatic
					</TCSendButton>
					<TCSendButton
						command={'batheaten 1'}
						target={target as any}
						onStateChange={onStateChange}
					>
						Force on
					</TCSendButton>
				</Flex>
			</Flex>
		</View>
	);
}
