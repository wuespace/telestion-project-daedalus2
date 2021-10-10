import { useCallback, useMemo } from 'react';
import { useEventBus } from '@wuespace/telestion-client-core';
import { RedisTimeSeriesRequest, RedisTimeSeriesSpecification } from './model';

/**
 * Available RedisTimeSeries aggregation types (in short: everything but `'range'`).
 *
 * @see https://oss.redis.com/redistimeseries/commands/#tsrangetsrevrange
 */
export type AggregationType =
	| 'avg'
	| 'max'
	| 'min'
	| 'last'
	| 'first'
	| 'std.s'
	| 'std.p'
	| 'var.s'
	| 'var.p'
	| 'count';

/**
 * A specification for an aggregated data request.
 */
export interface AggregationSpecification {
	/**
	 * The key that gets requested in Redis.
	 *
	 * By convention, keys should start with `'/ts/'`.
	 */
	key: string;
	/**
	 * The start timestamp for the aggregation.
	 *
	 * Can be a function that gets evaluated once the request gets made, i.e., once `fetch()` gets called.
	 */
	from: number | (() => number);
	/**
	 * The end timestamp for the aggregation.
	 *
	 * Can be a function that gets evaluated once the request gets made, i.e., once `fetch()` gets called.
	 */
	to: number | (() => number);
	/**
	 * The bucket size (in time units, typically ms).
	 *
	 * The number of returned time-value-pairs will be `(to - from) / bucketSize`.
	 */
	bucketSize: number | (() => number);
}

/**
 * The format of a single time-values pair returned by aggregations.
 *
 * A whole aggregation result will
 */
export type AggregationResult<T extends readonly AggregationType[]> = [
	number,
	{
		[key in T[number]]: number;
	}
][];

/**
 * Type for the callback function passed into {@link useRequestTimeSeries}
 *
 * @param result an array of {@link AggregationResult}s corresponding to the `aggregationSpecifications` passed into
 * {@link useRequestTimeSeries}
 */
export type OnChangeFunction<T extends AggregationType[]> = (
	result: (AggregationResult<T> | null)[]
) => void;

const requestTimeSeriesAddress = 'request-time-series';

/**
 * A wrapper for requesting time series data from the Redis DB.
 *
 * For a more high-level API that already sets a React state for you, use {@link useRequestTimeSeriesState}
 *
 * @param aggregationSpecifications - specifications for the aggregations you want to receive
 * @param aggregations - the aggregation types that get included in all aggregation specifications
 * @param onChange - a callback that gets called once `fetch()` gets called and new values are received
 *
 * @returns
 * a tuple containing `[0]` a `fetch()` function which, when called, loads new data from the server and calls `onChange`
 *
 * @example
 * ```tsx
 * const [fetch] = useRequestTimeSeries([
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
 * ], ['avg', 'min', 'max', 'count'], newData => console.log(newData));
 *
 * useEffect(() => {
 *     fetch();
 * }, [fetch]);
 * ```
 *
 * @see {@link useRequestTimeSeriesState}
 * @see {@link OnChangeFunction}
 */
export function useRequestTimeSeries<T extends AggregationType[]>(
	aggregationSpecifications: AggregationSpecification[],
	aggregations: T,
	onChange: OnChangeFunction<T>
): [() => void] {
	const eb = useEventBus();

	const getRequestObject: () => RedisTimeSeriesRequest = useCallback(
		() => ({
			className:
				'de.wuespace.telestion.project.daedalus2.redis.RedisTimeSeriesRequest',
			fields: aggregationSpecifications.map<RedisTimeSeriesSpecification>(
				r => ({
					...r,
					to: (typeof r.to === 'function' ? r.to() : r.to).toString(),
					from: (typeof r.from === 'function' ? r.from() : r.from).toString(),
					bucketSize:
						typeof r.bucketSize === 'function' ? r.bucketSize() : r.bucketSize,
					aggregations,
					className:
						'de.wuespace.telestion.project.daedalus2.redis.RedisTimeSeriesSpecification'
				})
			)
		}),
		[aggregations, aggregationSpecifications]
	);

	const fetch = useCallback(() => {
		eb.eventBus?.send<(AggregationResult<T> | null)[]>(
			requestTimeSeriesAddress,
			getRequestObject(),
			res => {
				onChange(Array.isArray(res) ? res : []);
			}
		);
	}, [eb.eventBus, getRequestObject, onChange]);

	return useMemo(() => [fetch], [fetch]);
}
