import AutoSizer from 'react-virtualized-auto-sizer';
import { View, Flex, Heading } from '@adobe/react-spectrum';
import { BaseRendererProps } from '@wuespace/telestion-client-types';

import { WidgetProps } from './model';
import { PGraph, Legend } from './components';

export function Widget(options: BaseRendererProps<WidgetProps>) {
	return (
		<View width="100%" height="100%">
			<Flex direction="column" width="100%" height="100%">
				<Heading
					flexGrow={0}
					level={3}
					margin="size-200"
					marginBottom="size-100"
				>
					{options.title}
				</Heading>
				<View flexGrow={1} paddingEnd="size-200" overflow="hidden">
					<AutoSizer>
						{({ width, height }) => (
							<PGraph width={width} height={height} options={options} />
						)}
					</AutoSizer>
				</View>
				<Legend connections={options.connections} />
			</Flex>
		</View>
	);
}
