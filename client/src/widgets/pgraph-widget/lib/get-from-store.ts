import { DataSample } from '../../../model/data-sample';

export function getMinFromStore(
	store: DataSample[][],
	initialValue: number
): number {
	return store.reduce(
		(outer, connection) =>
			connection.reduce((inner, sample) => Math.min(sample.min, inner), outer),
		initialValue
	);
}

export function getMaxFromStore(
	store: DataSample[][],
	initialValue: number
): number {
	return store.reduce(
		(outer, connection) =>
			connection.reduce((inner, sample) => Math.max(sample.max, inner), outer),
		initialValue
	);
}
