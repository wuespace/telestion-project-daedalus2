import { JsonSerializable } from '@wuespace/telestion-client-types';

/**
 * Type for a message that is received from or sent to the Java backend.
 */
export interface JavaMessage extends Record<string, JsonSerializable> {
	className: string;
}

/**
 * An extended version of {@link Pick}
 * to preserve necessary parts of the message.
 */
export type MessagePick<M extends JavaMessage, P extends keyof M> = Pick<
	M,
	P | 'className'
>;
