import { useChannelLatest, useLogger } from '@wuespace/telestion-client-core';
import { LoadingIndicator } from '@wuespace/telestion-client-common';

import { LatestState, latestStateChannel } from '../../model/channels';
import { fallbackState, states } from './model';
import { StateDisplay } from './state-display';

export function Widget() {
	const logger = useLogger('state-machine-widget');
	const current = useChannelLatest<LatestState>(latestStateChannel);

	if (current) logger.debug('Flight state:', current.currentState);

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
