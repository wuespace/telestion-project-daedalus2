import { useCachedLatest } from '../../hooks';

export type ExperimentDelay = readonly [seedADelay: number, seedBDelay: number];

export function useExperimentDelay(): ExperimentDelay {
	const current = Date.now();

	let [seedATime, seedBTime] = useCachedLatest<[number, number]>([
		'latest-time/seedA/iridium/payload/seed_state',
		'latest-time/seedB/iridium/payload/seed_state'
	]);

	seedATime = seedATime || 0;
	seedBTime = seedBTime || 0;

	return [current - seedATime, current - seedBTime];
}
