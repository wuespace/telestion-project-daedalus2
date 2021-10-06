import { RedisTimeSeriesSpecification } from './redis-time-series-specification';

/**
 * Counterpart to `de.wuespace.telestion.project.daedalus2.redis.RedisTimeSeriesRequest` Java class
 */
export interface RedisTimeSeriesRequest extends Record<string, any> {
	fields: readonly RedisTimeSeriesSpecification[];
	className: 'de.wuespace.telestion.project.daedalus2.redis.RedisTimeSeriesRequest';
}
