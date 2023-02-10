import { useCachedLatest } from '../../hooks';

export type ExperimentStates = readonly [
	seedAState: number,
	seedBState: number
];

export function useExperimentStates(): ExperimentStates {
	let [seedAState, seedBState] = useCachedLatest<[number, number]>([
		'latest/seedA/iridium/payload/seed_state',
		'latest/seedB/iridium/payload/seed_state'
	]);

	return [seedAState || 0, seedBState || 0];
}
