import { JavaMessage, MessagePick } from '../java-message';

/**
 * Type for a message that is stored in a mongo collection
 * and received from or sent to the Java backend.
 */
export interface MongoCollection extends JavaMessage {
	_id: string;
}

/**
 * An extended version of {@link MessagePick}
 * to preserve necessary parts of the message.
 */
export type MongoPick<
	M extends MongoCollection,
	P extends keyof M
> = MessagePick<M, P | '_id'>;
