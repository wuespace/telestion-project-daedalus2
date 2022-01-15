import { useCachedLatest } from '../hooks';

export type ExperimentStates = readonly [
	seedAState: number,
	seedBState: number,
	ejectorState: number
];

export function useExperimentStates(): ExperimentStates {
	let [seedAState, seedBState, ejectorState] = useCachedLatest<
		[number, number, number]
	>([
		'latest/seedA/SEED_HEARTBEAT/state_cur',
		'latest/seedB/SEED_HEARTBEAT/state_cur',
		'latest/ejector/EJECTOR_HEARTBEAT/state_cur'
	]);

	return [seedAState || 0, seedBState || 0, ejectorState || 0];
}
