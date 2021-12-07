import { Divider, Flex, Heading, Text, View } from '@adobe/react-spectrum';
import { useOuterTcSendButtonState } from '../hooks/use-outer-tc-send-button-state';
import { TCSendButton } from '../components/tc-send-button';
import PlayIcon from '@spectrum-icons/workflow/Play';
import StopIcon from '@spectrum-icons/workflow/Stop';
import FlashlightOn from '@spectrum-icons/workflow/FlashlightOn';
import FlashlightOff from '@spectrum-icons/workflow/FlashlightOff';

export function Widget() {
	const { messageState, onStateChange } = useOuterTcSendButtonState();

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading margin={0} level={3}>
					Ejector Camera Controls
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
				<View marginY={'size-200'}>Control the camera recording state:</View>
				<Flex>
					<div>
						<TCSendButton
							command={'camrec 0'}
							target={'ejector'}
							onStateChange={onStateChange}
							variant={'action'}
						>
							<StopIcon color="negative" />
							<Text>Stop</Text>
						</TCSendButton>
						<TCSendButton
							command={'camrec 1'}
							target={'ejector'}
							onStateChange={onStateChange}
							variant={'action'}
						>
							<PlayIcon color="positive" />
							<Text>Start</Text>
						</TCSendButton>
					</div>
				</Flex>
				<View marginY={'size-200'}>Turn camera LED on or off:</View>
				<Flex>
					<div>
						<TCSendButton
							command={'camled 0'}
							target={'ejector'}
							variant={'action'}
							onStateChange={onStateChange}
						>
							<FlashlightOff color={'negative'} />
							<Text>Off</Text>
						</TCSendButton>
						<TCSendButton
							command={'camled 1'}
							target={'ejector'}
							variant={'action'}
							onStateChange={onStateChange}
						>
							<FlashlightOn color={'positive'} />
							<Text>On</Text>
						</TCSendButton>
					</div>
				</Flex>
			</Flex>
		</View>
	);
}
