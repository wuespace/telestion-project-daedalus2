import { Divider, Flex, Heading, Text, View } from '@adobe/react-spectrum';
import { useOuterTcSendButtonState } from '../hooks/use-outer-tc-send-button-state';
import { TCSendButton } from '../components/tc-send-button';
import PlayIcon from '@spectrum-icons/workflow/Play';
import StopIcon from '@spectrum-icons/workflow/Stop';

export function Widget() {
	const { messageState, onStateChange } = useOuterTcSendButtonState();

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading margin={0} level={3}>
					Radio Silence
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
				<View marginY={'size-200'}>Enter the radio silence sequence:</View>
				<Flex>
					<div>
						<TCSendButton
							command={'radiosilence enter'}
							target={'ejector'}
							onStateChange={onStateChange}
							variant={'primary'}
						>
							<PlayIcon color="positive" />
							<Text>Enter</Text>
						</TCSendButton>
					</div>
				</Flex>
				<View marginY={'size-200'}>Abort the radio silence sequence:</View>
				<Flex>
					<div>
						<TCSendButton
							command={'radiosilence abort'}
							target={'ejector'}
							variant={'secondary'}
							onStateChange={onStateChange}
						>
							<StopIcon color={'negative'} />
							<Text>Abort</Text>
						</TCSendButton>
					</div>
				</Flex>
			</Flex>
		</View>
	);
}
