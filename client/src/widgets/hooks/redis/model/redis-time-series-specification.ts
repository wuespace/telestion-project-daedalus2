import { AggregationType } from '../use-request-time-series';

/**
 * Counterpart to `de.wuespace.telestion.project.daedalus2.redis.RedisTimeSeriesSpecification` Java class
 */
export interface RedisTimeSeriesSpecification
	extends Record<string, string | number | readonly string[]> {
	key: string;
	from: string;
	to: string;
	bucketSize: number;
	aggregations: readonly AggregationType[];
	className: 'de.wuespace.telestion.project.daedalus2.redis.RedisTimeSeriesSpecification';
}
