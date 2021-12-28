import { LoadingIndicator } from '@wuespace/telestion-client-common';

import { useCachedLatest } from '../hooks';
import { WidgetProps } from './model';
import { StateDisplay } from './components/state-display';
import { StateRenderer } from './components/state-renderer';

export function Widget({
	seedASource,
	seedBSource,
	ejectorSource
}: WidgetProps) {
	const currentStates = useCachedLatest<
		[number, number, number, number, number, number]
	>([
		`latest/seedA/${seedASource}/state_cur`,
		`latest-time/seedA/${seedASource}/state_cur`,
		`latest/seedB/${seedBSource}/state_cur`,
		`latest-time/seedB/${seedBSource}/state_cur`,
		`latest/ejector/${ejectorSource}/state_cur`,
		`latest-time/ejector/${ejectorSource}/state_cur`
	]);

	return (
		// @ts-ignore
		<LoadingIndicator timeout={0} dependencies={currentStates}>
			{(
				seedAState,
				seedATime,
				seedBState,
				seedBTime,
				ejectorState,
				ejectorTime
			) => (
				<StateDisplay>
					<StateRenderer
						title="Seed A"
						subsystem="seed"
						stateId={seedAState}
						time={seedATime}
					/>
					<StateRenderer
						title="Seed B"
						subsystem="seed"
						stateId={seedBState}
						time={seedBTime}
					/>
					<StateRenderer
						title="Ejector State"
						subsystem="ejector"
						stateId={ejectorState}
						time={ejectorTime}
					/>
				</StateDisplay>
			)}
		</LoadingIndicator>
	);
}
