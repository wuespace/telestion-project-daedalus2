import {
	Divider,
	Flex,
	Heading,
	NumberField,
	View
} from '@adobe/react-spectrum';
import { TCSendButton } from '../components/tc-send-button';
import { useOuterTcSendButtonState } from '../hooks/use-outer-tc-send-button-state';
import { useState } from 'react';

export function Widget() {
	const [temp, setTemp] = useState(20);
	const { messageState, onStateChange } = useOuterTcSendButtonState();

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading margin={0} level={3}>
					Battery Heating TC
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
				<NumberField
					marginTop={'size-200'}
					minValue={-50}
					maxValue={127}
					step={1}
					value={temp}
					onChange={setTemp}
					label={'Target temperature'}
					formatOptions={{ unit: 'celsius', style: 'unit' }}
					width={'100%'}
				/>
				<Flex marginTop={'size-200'} justifyContent={'space-between'}>
					<TCSendButton
						command={`batheattarget ${temp}`}
						target={'seedA'}
						variant={'primary'}
						onStateChange={onStateChange}
					>
						Set for Seed A&hellip;
					</TCSendButton>
					<TCSendButton
						command={`batheattarget ${temp}`}
						variant={'primary'}
						target={'seedB'}
						onStateChange={onStateChange}
					>
						Set for Seed B&hellip;
					</TCSendButton>
				</Flex>
			</Flex>
		</View>
	);
}
