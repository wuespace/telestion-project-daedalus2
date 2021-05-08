import { DataSample } from '../model/data-sample';
import { randNum } from './random';

export class FakeDataGenerator {
	/**
	 * The default minimum allowed value of the generated average value.
	 */
	static readonly DEFAULT_MIN = 0;
	/**
	 * The default maximum allowed value of the generated average value.
	 */
	static readonly DEFAULT_MAX = 10;
	/**
	 * The default maximum slope in percent
	 */
	static readonly DEFAULT_MAX_SLOPE_PERCENT = 10; /* % */

	readonly min: number;
	readonly max: number;
	readonly maxSlope: number;
	readonly middle: number;
	readonly percentile: number;

	private lastCall: number | undefined = undefined;
	private lastAvg: number;
	private lastMin: number;
	private lastMax: number;
	private direction = Math.random() < 0.5;
	private tippingPoint = Math.random();

	constructor(
		min = FakeDataGenerator.DEFAULT_MIN,
		max = FakeDataGenerator.DEFAULT_MAX,
		maxSlopePercent = FakeDataGenerator.DEFAULT_MAX_SLOPE_PERCENT
	) {
		this.min = min;
		this.max = max;
		this.middle = (max - min) / 2;

		this.percentile = (max - min) / 100;
		this.maxSlope = maxSlopePercent * this.percentile;

		this.lastAvg = this.middle;
		this.lastMin = this.middle - this.middle / 4;
		this.lastMax = this.middle + this.middle / 4;
	}

	getDataSample(): DataSample {
		const thisCall = Date.now();
		const timeStep = this.lastCall ? (thisCall - this.lastCall) / 1_000 : 0; // seconds
		this.lastCall = thisCall;
		// calculate new slope
		const slope = randNum(0, this.maxSlope);

		// calculate new direction
		const probability =
			((this.lastAvg - this.middle) / this.middle) * (this.direction ? +1 : -1); // range: [-1, +1] or [+1, -1]
		if (probability > this.tippingPoint) {
			this.direction = !this.direction;
			this.tippingPoint = Math.random();
		}

		//console.log({ slope, probability, tippingPoint, direction });

		// calculate new
		this.lastAvg += slope * (this.direction ? +1 : -1) * timeStep;
		this.lastMin += slope * (this.direction ? +1 : -1) * timeStep;
		this.lastMax += slope * (this.direction ? +1 : -1) * timeStep;

		//console.log({ avg, min, max, minDiff: avg - min, maxDiff: max - avg });

		// static noise
		const avgNoise = randNum(-this.percentile, +this.percentile); // randNum(avg - PERCENTILE, avg + PERCENTILE);
		const minNoise = randNum(-this.percentile, +this.percentile);
		const maxNoise = randNum(-this.percentile, +this.percentile);

		//console.log({avgNoise, minNoise, maxNoise});

		return {
			avg: this.lastAvg + avgNoise,
			min: this.lastMin + minNoise,
			max: this.lastMax + maxNoise,
			timeStamp: thisCall
		};
	}
}
