import { useState } from 'react';
import {
	ButtonGroup,
	Button,
	Divider,
	Flex,
	Heading,
	Item,
	TabList,
	TabPanels,
	Tabs,
	View
} from '@adobe/react-spectrum';
import { ConsoleDefinition, definitions } from './model';
import { ConsoleRenderer } from './console-renderer';
import { TcCounterBar } from './tc-counter-bar';
import { TelecommandBar } from './telecommand-bar';
import { TCState } from '../../model/tc-state';
import { useRequest } from '@wuespace/telestion-client-core';
import {
	requestChannel,
	requestClearAllMessage,
	requestClearMessage
} from '../../model/tc-console';

export function Widget() {
	const [target, setTarget] = useState(definitions[0].name);
	const [tcState, setTcState] = useState(TCState.IDLE);
	const request = useRequest(requestChannel);

	return (
		<View padding="size-200" height="100%">
			<Flex direction="column" height="100%">
				<Heading level={3} margin={0} marginBottom="size-200">
					TC Console
				</Heading>
				<Divider size="S" />

				<>
					{tcState === TCState.SENT && (
						<View
							padding={'size-200'}
							borderRadius={'regular'}
							marginTop={'size-200'}
							backgroundColor={'positive'}
						>
							Command sent successfully.
						</View>
					)}
					{tcState === TCState.ERROR && (
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

				<TcCounterBar definitions={definitions} />
				<View flexGrow={1}>
					<Tabs<ConsoleDefinition>
						aria-label="TC Console Selection"
						items={definitions}
						selectedKey={target}
						isDisabled={tcState === TCState.SENDING}
						// Tabs not properly typed lol
						// @ts-ignore
						onSelectionChange={setTarget}
						height="100%"
					>
						<TabList>
							{(item: ConsoleDefinition) => (
								<Item key={item.name}>{item.title}</Item>
							)}
						</TabList>
						<TabPanels height="100%">
							{(item: ConsoleDefinition) => (
								<Item key={item.name}>
									<ConsoleRenderer source={item.name} />
								</Item>
							)}
						</TabPanels>
					</Tabs>
				</View>
				<View>
					<ButtonGroup width="100%" orientation="horizontal" align="end">
						<Button
							variant="primary"
							onPress={() => request(requestClearMessage(target), () => {})}
						>
							Clear
						</Button>
						<Button
							variant="negative"
							onPress={() => request(requestClearAllMessage(), () => {})}
						>
							Clear All
						</Button>
					</ButtonGroup>
				</View>
				<TelecommandBar
					definitions={definitions}
					target={target}
					tcState={tcState}
					setTarget={setTarget}
					setTcState={setTcState}
				/>
			</Flex>
		</View>
	);
}
