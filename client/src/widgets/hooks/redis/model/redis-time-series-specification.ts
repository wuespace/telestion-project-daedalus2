import { AggregationType } from '../use-request-time-series';
import { JavaMessage } from '../../../../model/java-message';

/**
 * Counterpart to `de.wuespace.telestion.project.daedalus2.redis.RedisTimeSeriesSpecification` Java class
 */
export interface RedisTimeSeriesSpecification extends JavaMessage {
	key: string;
	from: string;
	to: string;
	bucketSize: number;
	aggregations: AggregationType[];
	className: 'de.wuespace.telestion.project.daedalus2.redis.RedisTimeSeriesSpecification';
}
