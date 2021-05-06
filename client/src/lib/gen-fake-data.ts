import { randNum } from './random';
import { DataSample } from '../model/data-sample';

// edit to your preference
const MIN = 0; // absolute value
const MAX = 10; // absolute value
const MAX_SLOPE_PERCENT = 10; // 10% of range per call

// ------
const MIDDLE = (MAX - MIN) / 2;
const PERCENTILE = (MAX - MIN) / 100; // 1% of range
const MAX_SLOPE = MAX_SLOPE_PERCENT * PERCENTILE;

let direction = Math.random() < 0.5; // true -> up; false -> down
let tippingPoint = Math.random();

// initialization
let avg = MIDDLE; // 50%
let min = MIDDLE - MIDDLE / 2; // 25%
let max = MIDDLE + MIDDLE / 2; // 75%

let lastCall: number | undefined = undefined;

export function genFakeData(): DataSample {
	const thisCall = Date.now();
	const timeStep = lastCall ? (thisCall - lastCall) / 1_000 : 0; // seconds
	lastCall = thisCall;
	// calculate new slope
	const slope = randNum(0, MAX_SLOPE);

	// calculate new direction
	const probability = ((avg - MIDDLE) / MIDDLE) * (direction ? +1 : -1); // range: [-1, +1] or [+1, -1]
	if (probability > tippingPoint) {
		direction = !direction;
		tippingPoint = Math.random();
	}

	//console.log({ slope, probability, tippingPoint, direction });

	// calculate new
	avg += slope * (direction ? +1 : -1) * timeStep;
	min += slope * (direction ? +1 : -1) * timeStep;
	max += slope * (direction ? +1 : -1) * timeStep;

	//console.log({ avg, min, max, minDiff: avg - min, maxDiff: max - avg });

	// static noise
	const avgNoise = randNum(-PERCENTILE, +PERCENTILE); // randNum(avg - PERCENTILE, avg + PERCENTILE);
	const minNoise = randNum(-PERCENTILE, +PERCENTILE);
	const maxNoise = randNum(-PERCENTILE, +PERCENTILE);

	//console.log({avgNoise, minNoise, maxNoise});

	return {
		avg: avg + avgNoise,
		min: min + minNoise,
		max: max + maxNoise,
		timeStamp: thisCall
	};
}
