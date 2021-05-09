import { DataSample } from '../../../model/data-sample';

/**
 * Removes old samples from all connections in the store.
 * @param store - the store which holds all connection samples
 * @param min - the minimum valid timestamp
 */
export function removeOldSamples(
	store: DataSample[][],
	min: number
): DataSample[][] {
	return store.map(connection =>
		connection.filter(sample => sample.time > min)
	);
}
