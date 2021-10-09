import { useLogger } from '@wuespace/telestion-client-core';
import { LoadingIndicator } from '@wuespace/telestion-client-common';

import { fallbackState, states } from './model';
import { StateDisplay } from './state-display';
import { useCachedLatest } from '../hooks';
import { StateWidgetConfig } from './state-widget-config';

export function Widget({
	seedASource,
	seedBSource,
	ejectorSource
}: StateWidgetConfig) {
	const logger = useLogger('state-machine-widget');
	const currentStates = useCachedLatest<[number, number, number]>([
		`latest/seedA/${seedASource}/state_cur`,
		`latest/seedB/${seedBSource}/state_cur`,
		`latest/ejector/${ejectorSource}/state_cur`
	]);

	if (states) logger.debug('Flight state:', currentStates);

	return (
		// @ts-ignore
		<LoadingIndicator timeout={0} dependencies={currentStates}>
			{(seedA, seedB, ejector) => (
				<StateDisplay
					seedAState={states[seedA] || fallbackState}
					seedBState={states[seedB] || fallbackState}
					ejectorState={states[ejector] || fallbackState}
				/>
			)}
		</LoadingIndicator>
	);
}
