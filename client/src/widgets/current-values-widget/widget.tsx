import {
	View,
	Flex,
	Heading,
	Divider,
	Grid,
	repeat,
	minmax
} from '@adobe/react-spectrum';
import { OverflowFix } from '@wuespace/telestion-client-common';
import { BaseRendererProps } from '@wuespace/telestion-client-types';

import { WidgetProps } from './model';
import { LiveWidget } from './components/live-widget';

export function Widget({ title, connections }: BaseRendererProps<WidgetProps>) {
	return (
		<View width="100%" height="100%">
			<Flex direction="column" width="100%" height="100%">
				<Heading
					flexGrow={0}
					level={3}
					margin="size-200"
					marginBottom="size-100"
				>
					{title}
				</Heading>
				<Divider size="S" />
				<OverflowFix>
					<Grid
						columns={repeat('auto-fit', minmax('size-2400', '1fr'))}
						justifyContent="center"
						gap="size-100"
						margin="size-100"
					>
						{connections.map((connection, index) => (
							<LiveWidget
								key={`${index}-${connection.title}`}
								connection={connection}
							/>
						))}
					</Grid>
				</OverflowFix>
			</Flex>
		</View>
	);
}
