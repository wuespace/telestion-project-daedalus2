import { useCallback } from 'react';
import {
	Form,
	NumberField,
	Radio,
	RadioGroup,
	View
} from '@adobe/react-spectrum';
import { BaseConfigControlsProps } from '@wuespace/telestion-client-types';
import { WidgetProps } from '../model';

export function ConfigControls({
	currentProps,
	onUpdate
}: BaseConfigControlsProps<WidgetProps>) {
	const { seed, voltThreshold, ampsThreshold } = currentProps;

	const updateVoltThreshold = useCallback(
		(value: number) => onUpdate({ voltThreshold: value }),
		[onUpdate]
	);

	const updateAmpsThreshold = useCallback(
		(value: number) => onUpdate({ ampsThreshold: value }),
		[onUpdate]
	);

	const updateSeed = useCallback(
		(value: string) =>
			value === 'seedA' || value === 'seedB' ? onUpdate({ seed: value }) : null,
		[onUpdate]
	);

	return (
		<View paddingX="size-200" paddingBottom="size-200">
			<Form maxWidth="100%" isRequired>
				<NumberField
					autoFocus={true}
					label="Voltage Threshold"
					value={voltThreshold}
					onChange={updateVoltThreshold}
					minValue={0}
					formatOptions={{
						minimumFractionDigits: 1,
						maximumFractionDigits: 4
					}}
					isRequired
				/>
				<NumberField
					width="100%"
					label="Ampere Threshold"
					value={ampsThreshold}
					onChange={updateAmpsThreshold}
					minValue={0}
					formatOptions={{
						minimumFractionDigits: 1,
						maximumFractionDigits: 4
					}}
					isRequired
				/>
				<RadioGroup
					value={seed}
					label="Seed"
					isRequired
					width="100%"
					onChange={updateSeed}
				>
					<Radio value={'seedA'}>Seed A</Radio>
					<Radio value={'seedB'}>Seed B</Radio>
				</RadioGroup>
			</Form>
		</View>
	);
}
