import { useState } from 'react';
import {
	View,
	Flex,
	Form,
	NumberField,
	Slider,
	Checkbox
} from '@adobe/react-spectrum';
import { Options } from '../../model';

export interface OptionConfigurationProps {
	options: Options;
}

export function OptionConfigurations({ options }: OptionConfigurationProps) {
	const [isMinimumEnabled, setMinimumState] = useState(false);
	const [isMaximumEnabled, setMaximumState] = useState(false);

	return (
		<Form maxWidth="size-1000">
			<NumberField label="Period (s)" minValue={0} maxValue={1200} />

			<Checkbox isSelected={isMinimumEnabled} onChange={setMinimumState}>
				Enable minimum border
			</Checkbox>
			<NumberField label="Minimum border" isDisabled={!isMinimumEnabled} />

			<Checkbox isSelected={isMaximumEnabled} onChange={setMaximumState}>
				Enable maximum border
			</Checkbox>
			<NumberField label="Maximum border" isDisabled={isMaximumEnabled} />

			<Slider label="Renders per second" minValue={1} maxValue={20} />
		</Form>
	);
}
