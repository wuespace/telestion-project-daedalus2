import { useState } from 'react';
import {
	AggregationResult,
	AggregationSpecification,
	AggregationType,
	useRequestTimeSeries
} from './use-request-time-series';

/**
 * A higher-level wrapper for {@link useRequestTimeSeries} that already returns a react state containing fetched values.
 *
 * @param aggregationSpecifications - specifications for the aggregations you want to receive
 * @param aggregations - the aggregation types that get included in all aggregation specifications
 *
 * @returns
 * a tuple containing `[0]` a `fetch()` function which, when called, loads new data from the server and `[1]` and array
 * containing {@link AggregationResult}s corresponding to the `aggregationSpecifications` array
 *
 * @example
 * ```tsx
 * const [fetch, [ts1, ts2]] = useRequestTimeSeriesState([
 *     {
 *         key: 'ts/ts1Key',
 *         from: () => Date.now() - 5000,
 *         to: () => Date.now()
 *         timeBucket: 1000
 *     },
 *     {
 *         key: 'ts/ts2Key',
 *         from: () => Date.now() - 5000,
 *         to: () => Date.now()
 *         timeBucket: 1000
 *     }
 * ], ['avg', 'min', 'max', 'count']);
 *
 * useEffect(() => {
 *     fetch();
 * }, [fetch]);
 * ```
 *
 * @see {@link useRequestTimeSeries}
 */
export function useRequestTimeSeriesState<T extends AggregationType[]>(
	aggregationSpecifications: AggregationSpecification[],
	aggregations: T
): [() => void, (AggregationResult<T> | null)[]] {
	const [result, setResult] = useState<(AggregationResult<T> | null)[]>([]);
	const [fetch] = useRequestTimeSeries<T>(
		aggregationSpecifications,
		aggregations,
		setResult
	);
	return [fetch, result];
}
