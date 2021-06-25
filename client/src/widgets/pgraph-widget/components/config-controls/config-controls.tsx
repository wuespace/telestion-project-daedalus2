import { BaseConfigControlsProps } from '@wuespace/telestion-client-types';
import { WidgetProps } from '../../model';
import { Flex, View } from '@adobe/react-spectrum';
import { ConnectionTable } from './connection-table';

export function ConfigControls({
	currentProps,
	onUpdate
}: BaseConfigControlsProps<WidgetProps>) {
	const { connections } = currentProps;

	return (
		<View paddingX="size-200" paddingBottom="size-200">
			<Flex direction="column" gap="size-200">
				<ConnectionTable connections={connections} />
			</Flex>
		</View>
	);
}
