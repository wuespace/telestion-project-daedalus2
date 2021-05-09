import { DataSample } from '../model/data-sample';
import { randNum } from './random';

// edit to your preference
const MIN = 0; // absolute value
const MAX = 10; // absolute value
const AREA = MAX - MIN;

export function genRandomData(): DataSample {
	const avg = randNum(MIN, MAX);

	const maxAdd = randNum(0, AREA / 4);
	const minAdd = randNum(0, AREA / 4);
	const min = avg - minAdd < MIN ? MIN : avg - minAdd;
	const max = avg + maxAdd > MAX ? MAX : avg + maxAdd;

	return {
		avg,
		min,
		max,
		last: max,
		time: Date.now()
	};
}
