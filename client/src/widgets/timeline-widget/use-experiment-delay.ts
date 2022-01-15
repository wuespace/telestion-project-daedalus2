import { useCachedLatest } from '../hooks';

export type ExperimentDelay = readonly [
	seedADelay: number,
	seedBDelay: number,
	ejectorDelay: number
];

export function useExperimentDelay(): ExperimentDelay {
	const current = Date.now();

	let [seedATime, seedBTime, ejectorTime] = useCachedLatest<
		[number, number, number]
	>([
		'latest-time/seedA/SEED_HEARTBEAT/state_cur',
		'latest-time/seedB/SEED_HEARTBEAT/state_cur',
		'latest-time/ejector/EJECTOR_HEARTBEAT/state_cur'
	]);

	seedATime = seedATime || 0;
	seedBTime = seedBTime || 0;
	ejectorTime = ejectorTime || 0;

	return [current - seedATime, current - seedBTime, current - ejectorTime];
}
