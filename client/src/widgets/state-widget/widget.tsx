import { useChannelLatest } from '@wuespace/telestion-client-core';
import { LoadingIndicator } from '@wuespace/telestion-client-common';

import { LatestState, latestStateChannel } from '../../model/channels';
import { fallbackState, states } from './model';
import { StateDisplay } from './state-display';

export function Widget() {
	const current = useChannelLatest<LatestState>(latestStateChannel);

	if (current) console.log('Flight state:', current.currentState);

	return (
		// @ts-ignore
		<LoadingIndicator timeout={0} dependencies={[current]}>
			{({ currentState }) => (
				<StateDisplay
					stateSeed={states[currentState] || fallbackState}
					stateEjector={states[currentState] || fallbackState}
				/>
			)}
		</LoadingIndicator>
	);
}
