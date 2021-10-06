import { RedisTimeSeriesSpecification } from './redis-time-series-specification';
import { JavaMessage } from '../../../../model/java-message';

/**
 * Counterpart to `de.wuespace.telestion.project.daedalus2.redis.RedisTimeSeriesRequest` Java class
 */
export interface RedisTimeSeriesRequest extends JavaMessage {
	fields: RedisTimeSeriesSpecification[];
	className: 'de.wuespace.telestion.project.daedalus2.redis.RedisTimeSeriesRequest';
}
