import { LoadingIndicator } from '@wuespace/telestion-client-common';

import { fallbackState, states } from '../../model/state';
import { useCachedLatest } from '../hooks';
import { WidgetProps } from './model';
import { StateDisplay } from './components/state-display';

export function Widget({
	seedASource,
	seedBSource,
	ejectorSource
}: WidgetProps) {
	const currentStates = useCachedLatest<[number, number, number]>([
		`latest/seedA/${seedASource}/state_cur`,
		`latest/seedB/${seedBSource}/state_cur`,
		`latest/ejector/${ejectorSource}/state_cur`
	]);

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
