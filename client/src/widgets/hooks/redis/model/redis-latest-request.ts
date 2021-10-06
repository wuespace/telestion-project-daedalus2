import { JavaMessage } from '../../../../model/java-message';

/**
 * Counterpart to `de.wuespace.telestion.project.daedalus2.redis.RedisLatestRequest` Java class
 */
export interface RedisLatestRequest extends JavaMessage {
	fields: string[];
	className: 'de.wuespace.telestion.project.daedalus2.redis.RedisLatestRequest';
}
