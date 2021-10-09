import { Flex, Heading, Radio, RadioGroup, View } from '@adobe/react-spectrum';
import { BaseConfigControlsProps } from '@wuespace/telestion-client-types';
import { useHljs } from '../hooks';
import { StateWidgetConfig } from './state-widget-config';

export function ConfigControls({
	currentProps,
	onUpdate
}: BaseConfigControlsProps<StateWidgetConfig>) {
	useHljs();

	const value = JSON.stringify(currentProps, null, 2);

	return (
		<View paddingX="size-200" paddingBottom="size-200">
			<Heading level={3}>State Sources</Heading>
			<p>Please select which message should get used to determine the state.</p>
			<Flex width="100%" gap="size-200">
				<RadioGroup
					value={currentProps.seedASource}
					onChange={v => onUpdate({ seedASource: v as any })}
					label="Seed A State Source"
				>
					<Radio value={'SEED_HEARTBEAT'}>
						<code>SEED_HEARTBEAT</code>
					</Radio>
					<Radio value={'SEED_SYSTEM_T'}>
						<code>SEED_SYSTEM_T</code>
					</Radio>
				</RadioGroup>
				<RadioGroup
					value={currentProps.seedBSource}
					onChange={v => onUpdate({ seedBSource: v as any })}
					label="Seed B State Source"
				>
					<Radio value={'SEED_HEARTBEAT'}>
						<code>SEED_HEARTBEAT</code>
					</Radio>
					<Radio value={'SEED_SYSTEM_T'}>
						<code>SEED_SYSTEM_T</code>
					</Radio>
				</RadioGroup>
				<RadioGroup
					value={currentProps.ejectorSource}
					onChange={v => onUpdate({ ejectorSource: v as any })}
					label="Ejector State Source"
				>
					<Radio value={'EJECTOR_HEARTBEAT'}>
						<code>EJECTOR_HEARTBEAT</code>
					</Radio>
					<Radio value={'EJECTOR_SYSTEM_T'}>
						<code>EJECTOR_SYSTEM_T</code>
					</Radio>
				</RadioGroup>
			</Flex>
			<Heading level={3}>Raw</Heading>
			<details>
				<summary>Configuration JSON</summary>
				<pre>
					<code>{value}</code>
				</pre>
			</details>
		</View>
	);
}
