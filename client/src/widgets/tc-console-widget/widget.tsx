import { useState } from 'react';
import {
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

export function Widget() {
	const [target, setTarget] = useState(definitions[0].name);

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading level={3} margin={0} marginBottom="size-200">
					TC Console
				</Heading>
				<Divider size="S" />
				<View>
					<Tabs<ConsoleDefinition>
						aria-label="TC Console Selection"
						items={definitions}
						selectedKey={target}
						// Tabs not properly typed lol
						// @ts-ignore
						onSelectionChange={setTarget}
					>
						<TabList>
							{(item: ConsoleDefinition) => (
								<Item key={item.name}>{item.title}</Item>
							)}
						</TabList>
						<TabPanels>
							{(item: ConsoleDefinition) => (
								<Item key={item.name}>
									<ConsoleRenderer target={item.name} />
								</Item>
							)}
						</TabPanels>
					</Tabs>
				</View>
			</Flex>
		</View>
	);
}
