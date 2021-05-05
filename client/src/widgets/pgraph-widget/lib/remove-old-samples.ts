import { DataSample } from '../../../model/data-sample';

/**
 * Removes old samples from the store which no longer fit in the current time period.
 * @param store - the store to clean up
 * @param period - the time period for valid samples in milliseconds
 * @returns the cleaned store (as a new array)
 */
export function removeOldSamples(
	store: DataSample[],
	period: number
): DataSample[] {
	const minimumValidTime = Date.now() - period;
	return store.filter(sample => sample.timeStamp >= minimumValidTime);
}
