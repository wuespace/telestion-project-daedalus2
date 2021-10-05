import { Divider, Flex, Heading, View } from '@adobe/react-spectrum';
import { BaseRendererProps } from '@wuespace/telestion-client-types';
import { LoadingIndicator } from '@wuespace/telestion-client-common';

import { useChecklistState } from './use-checklist-state';
import { useChecklistReducers } from './use-checklist-reducers';
import { WidgetProps } from './model';
import { Checklist } from './checklist';

export function Widget({
	title,
	requestChannel,
	notifyChannel
}: BaseRendererProps<WidgetProps>) {
	const { points } = useChecklistState(requestChannel, notifyChannel);
	const { newState } = useChecklistReducers(title, requestChannel);

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading margin={0} level={3}>
					{title}
				</Heading>
			</Flex>
			<Divider size="M" marginTop="size-100" />
			{/* @ts-ignore */}
			<LoadingIndicator timeout={0} dependencies={[points]}>
				{defined => <Checklist points={defined} onChange={newState} />}
			</LoadingIndicator>
		</View>
	);
}
