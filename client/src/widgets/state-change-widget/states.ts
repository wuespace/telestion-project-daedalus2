import { ejectorStates, seedStates, State } from '../../model/state';

const nonVisibleStateNames = [
	'N/A',
	'Invalid',
	'*',
	'Post',
	'STATIC_TEST_ENTRY',
	'STATIC_TEST'
];

function createStateIds(states: Record<number, State>): number[] {
	return Object.keys(states) as unknown as number[];
}

function createStatesList(states: Record<number, State>) {
	return Object.entries(states)
		.map(([id, state]) => ({
			id,
			...state
		}))
		.filter(
			entry => !entry.name.endsWith('Entry') && !entry.name.endsWith('Exit')
		)
		.filter(entry => !nonVisibleStateNames.includes(entry.name));
}

export const ejectorStatesList = createStatesList(ejectorStates);
export const ejectorStateIds = createStateIds(ejectorStates);

export const seedStatesList = createStatesList(seedStates);
export const seedStateIds = createStateIds(seedStates);
