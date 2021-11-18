import { Heading, View } from '@adobe/react-spectrum';
import { BaseConfigControlsProps } from '@wuespace/telestion-client-types';
import { WidgetProps } from './model';
import { useHljs } from '../hooks';

export function ConfigControls({
	currentProps
}: BaseConfigControlsProps<WidgetProps>) {
	useHljs();

	const value = JSON.stringify(currentProps, null, 2);

	return (
		<View paddingX="size-200" paddingBottom="size-200">
			<Heading level={5} margin={0} marginTop="size-200">
				Configuration
			</Heading>
			<pre>
				<code>{value}</code>
			</pre>
		</View>
	);
}
