import { LoadingIndicator } from '@wuespace/telestion-client-common';

import { useCachedLatest } from '../hooks';
import { StateDisplay } from './components/state-display';
import { StateRenderer } from './components/state-renderer';

export function Widget() {
	const currentStates = useCachedLatest<
		[
			seedAState: number,
			seedATime: number,
			seedBState: number,
			seedBTime: number
		]
	>([
		`latest/seedA/iridium/payload/seed_state`,
		`latest-time/seedA/iridium/payload/seed_state`,
		`latest/seedB/iridium/payload/seed_state`,
		`latest-time/seedB/iridium/payload/seed_state`
	]);

	return (
		// @ts-ignore
		<LoadingIndicator timeout={0} dependencies={currentStates}>
			{(seedAState, seedATime, seedBState, seedBTime) => (
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
				</StateDisplay>
			)}
		</LoadingIndicator>
	);
}
