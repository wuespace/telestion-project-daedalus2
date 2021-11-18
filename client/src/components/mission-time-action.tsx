import { useState } from 'react';
import {
	Text,
	TooltipTrigger,
	Tooltip,
	ActionButton
} from '@adobe/react-spectrum';
import useInterval from '../hooks/use-interval';

export function MissionTimeAction() {
	const [currentTime, setCurrentTime] = useState(
		Math.floor(Date.now() / 1000 - 1420070400)
	);

	useInterval(
		() => setCurrentTime(Math.floor(Date.now() / 1000 - 1420070400)),
		1000
	);
	return (
		<TooltipTrigger>
			<ActionButton>
				<Text>
					Time: <code>{currentTime.toLocaleString('de')}</code>
				</Text>
			</ActionButton>
			<Tooltip>
				Deadlus 2 Mission time (number of seconds since 1/1/2015)
			</Tooltip>
		</TooltipTrigger>
	);
}
