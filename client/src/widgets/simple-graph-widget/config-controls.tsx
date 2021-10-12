import {
	ActionButton,
	Button,
	ComboBox,
	Divider,
	Flex,
	Heading,
	Item,
	NumberField,
	Slider,
	Switch,
	TextField,
	View
} from '@adobe/react-spectrum';
import AddIcon from '@spectrum-icons/workflow/Add';
import DeleteIcon from '@spectrum-icons/workflow/Delete';
import { ColorSlider } from '@react-spectrum/color';
import { BaseConfigControlsProps } from '@wuespace/telestion-client-types';
import { useHljs } from '../hooks';
import { Series, WidgetProps } from './widget-props';
import { useRequest } from '@wuespace/telestion-client-core';
import { useEffect, useMemo, useState } from 'react';
import { LoadingIndicator } from '@wuespace/telestion-client-common';

function SeriesEditor({
	onDelete,
	onUpdate,
	value,
	availableKeys
}: {
	onDelete: () => void;
	onUpdate: (value: Series) => void;
	value: Series;
	availableKeys: string[];
}) {
	const items = useMemo(
		() => availableKeys.map(key => ({ id: key, name: key })),
		[availableKeys]
	);

	return (
		<>
			<Flex alignItems={'end'} gap={'size-200'}>
				<ComboBox
					label={'Field'}
					width={300}
					defaultItems={items}
					defaultInputValue={value.key}
					selectedKey={value.key}
					onSelectionChange={v => onUpdate({ ...value, key: v as any })}
				>
					{key => <Item key={key.id}>{key.name}</Item>}
				</ComboBox>
				<Switch
					margin={0}
					isSelected={value.minMax}
					onChange={v => onUpdate({ ...value, minMax: v })}
				>
					Min/Max
				</Switch>

				<ColorSlider
					label="Color"
					value={value.color}
					onChange={v =>
						onUpdate({ ...value, color: v.toString('hsl') as any })
					}
					channel="hue"
					flexGrow={4}
				/>

				<ActionButton onPress={onDelete} isQuiet={true} aria-label="Delete">
					<DeleteIcon />
				</ActionButton>
			</Flex>
			<Divider size="S" />
		</>
	);
}

function MultiSeriesEditor({
	currentProps,
	onUpdate,
	availableKeys
}: {
	currentProps: WidgetProps;
	onUpdate: (newProps: Partial<WidgetProps>) => void;
	availableKeys: string[];
}) {
	return (
		<>
			{
				<Flex direction={'column'} gap={'size-200'}>
					<>
						{currentProps.series.map((s, i) => (
							<SeriesEditor
								onDelete={() =>
									onUpdate({
										series: [
											...currentProps.series.slice(0, i),
											...currentProps.series.slice(i + 1)
										]
									})
								}
								value={s}
								onUpdate={(value: Series) =>
									onUpdate({
										series: [
											...currentProps.series.slice(0, i),
											value,
											...currentProps.series.slice(i + 1)
										]
									})
								}
								key={s.key}
								availableKeys={availableKeys}
							/>
						))}
					</>
					<View>
						<Button
							onPress={() =>
								onUpdate({
									series: [
										...currentProps.series,
										{
											minMax: true,
											color: 'hsl(0, 100%, 50%)',
											key: 'ts/seedA/SEED_SYSTEM_T/imu_gyro_z'
										}
									]
								})
							}
							variant="primary"
						>
							<AddIcon />
							Add series
						</Button>
					</View>
					<Divider size="S" />
				</Flex>
			}
		</>
	);
}

export function ConfigControls({
	currentProps,
	onUpdate
}: BaseConfigControlsProps<WidgetProps>) {
	useHljs();

	const value = JSON.stringify(currentProps, null, 2);

	const requestKeys = useRequest<string[]>('request-keys');

	const [availableKeys, setAvailableKeys] = useState<string[] | null>(null);

	useEffect(() => {
		requestKeys('ts/*', setAvailableKeys);
	}, [requestKeys]);

	return (
		<View paddingX="size-200" paddingBottom="size-200">
			<Heading level={3}>General Settings</Heading>
			<TextField
				value={currentProps.title}
				width={'100%'}
				onChange={title => onUpdate({ title })}
				label="Title"
			/>
			<Slider
				value={currentProps.seconds}
				onChange={v => onUpdate({ seconds: v })}
				minValue={10}
				maxValue={240}
				step={5}
				label="History (s)"
				width={'100%'}
			/>
			<Slider
				value={currentProps.maxSamples}
				onChange={v => onUpdate({ maxSamples: v })}
				minValue={5}
				maxValue={100}
				step={1}
				label="Maximum Samples (higher value = more fine-grained, but slower and potentially messy)"
				width={'100%'}
			/>
			<Flex>
				<NumberField
					value={currentProps.minY}
					width={'100%'}
					onChange={v => onUpdate({ minY: v })}
					isQuiet={true}
					step={0.1}
					label={'Default Y Axis Minimum'}
				/>
				<NumberField
					value={currentProps.maxY}
					width={'100%'}
					isQuiet={true}
					step={0.1}
					onChange={v => onUpdate({ maxY: v })}
					label={'Default Y Axis Maximum'}
				/>
			</Flex>
			<Heading level={3}>State Sources</Heading>
			{/* @ts-ignore */}
			<LoadingIndicator dependencies={[availableKeys]}>
				{keys => (
					<MultiSeriesEditor
						currentProps={currentProps}
						onUpdate={onUpdate}
						availableKeys={keys || []}
					/>
				)}
			</LoadingIndicator>
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
