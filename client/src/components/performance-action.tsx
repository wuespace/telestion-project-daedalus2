import { useCallback, useState } from 'react';
import { TooltipTrigger, Tooltip, ActionButton } from '@adobe/react-spectrum';

import Gauge1 from '@spectrum-icons/workflow/Gauge1';
import Gauge2 from '@spectrum-icons/workflow/Gauge2';
import Gauge3 from '@spectrum-icons/workflow/Gauge3';
import Gauge4 from '@spectrum-icons/workflow/Gauge4';
import Gauge5 from '@spectrum-icons/workflow/Gauge5';

const tooltips: Record<number, string> = {
	1: 'Increase performance',
	2: 'Increase performance further',
	3: 'Overclock screen',
	4: 'Enable hyperdrive',
	5: 'Slowdown!'
};

export function PerformanceAction() {
	const [state, setState] = useState(1);

	const handle = useCallback(
		() => setState(prevState => (prevState < 5 ? prevState + 1 : 1)),
		[]
	);

	return (
		<TooltipTrigger>
			<ActionButton onPress={handle}>
				{state === 1 ? (
					<Gauge1 />
				) : state === 2 ? (
					<Gauge2 />
				) : state === 3 ? (
					<Gauge3 />
				) : state === 4 ? (
					<Gauge4 />
				) : (
					<Gauge5 />
				)}
			</ActionButton>
			<Tooltip>{tooltips[state]}</Tooltip>
		</TooltipTrigger>
	);
}
