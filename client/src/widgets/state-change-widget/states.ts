import { states } from '../../model/state';

const nonVisibleStateNames = ['N/A', 'kInvalid'];

export const statesList = Object.entries(states)
	.map(([id, state]) => ({
		id,
		...state
	}))
	.filter(
		entry => !entry.name.endsWith('Entry') && !entry.name.endsWith('Exit')
	)
	.filter(entry => !nonVisibleStateNames.includes(entry.name));

// @ts-ignore
window['statesList'] = statesList;
