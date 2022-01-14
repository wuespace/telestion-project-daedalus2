import { JavaMessage } from './java-message';
import { hasOwnProperty, isObj } from '../lib/core-utils';

export interface LogMessage extends JavaMessage {
	/**
	 * The source of the log message.
	 */
	source: string;

	/**
	 * The message received from the source in the given time.
	 */
	message: string;

	/**
	 * The time at which the message was received in milliseconds UTC.
	 */
	receiveTime: number;

	/**
	 * The RODOS local time in nanoseconds since last reboot.
	 */
	time_local: number;

	className: 'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.LogMessage';
}

export interface LogMessages extends JavaMessage {
	/**
	 * The source of the log messages.
	 */
	source: string;

	/**
	 * All stored messages for this source.
	 */
	messages: LogMessage[];

	className: 'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.LogMessages';
}

interface ConsoleRequestBase extends JavaMessage {
	/**
	 * The type of the request you want to send to the verticle.
	 */
	type: 'state' | 'clear' | 'clear-all';
}

export interface RequestState extends ConsoleRequestBase {
	type: 'state';
	/**
	 * The source of the log messages you want to request.
	 */
	source: string;

	className: 'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.RequestState';
}

export interface RequestClear extends ConsoleRequestBase {
	type: 'clear';

	source: string;

	className: 'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.RequestClear';
}

export interface RequestClearAll extends ConsoleRequestBase {
	type: 'clear-all';

	className: 'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.RequestClearAll';
}

export type ConsoleRequest = RequestState | RequestClear | RequestClearAll;

interface ConsoleResponseBase extends JavaMessage {
	/**
	 * The type of the response you receive
	 * when you send a request to the verticle.
	 */
	type: 'state' | 'clear' | 'clear-all';
}

export interface ResponseState extends ConsoleResponseBase {
	type: 'state';

	/**
	 * The current state of the requested source.
	 */
	messages: LogMessages;

	className: 'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.ResponseState';
}

export interface ResponseClear extends ConsoleResponseBase {
	type: 'clear';

	source: string;

	className: 'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.ResponseClear';
}

export interface ResponseClearAll extends ConsoleResponseBase {
	type: 'clear-all';

	className: 'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.ResponseClearAll';
}

export type ConsoleResponse = ResponseState | ResponseClear | ResponseClearAll;

export const requestChannel = 'tc-console-request';
export const notifyChannel = 'tc-console-notify';

export function requestStateMessage(source: string): RequestState {
	return {
		type: 'state',
		source,
		className:
			'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.RequestState'
	};
}

export function requestClearMessage(source: string): RequestClear {
	return {
		type: 'clear',
		source,
		className:
			'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.RequestClear'
	};
}

export function requestClearAllMessage(): RequestClearAll {
	return {
		type: 'clear-all',
		className:
			'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.message.RequestClearAll'
	};
}

export function isLogMessage(value: unknown): value is LogMessage {
	return (
		isObj(value) &&
		hasOwnProperty(value, 'source') &&
		hasOwnProperty(value, 'message') &&
		hasOwnProperty(value, 'receiveTime') &&
		hasOwnProperty(value, 'time_local') &&
		typeof value.source === 'string' &&
		typeof value.message === 'string' &&
		typeof value.receiveTime === 'number' &&
		typeof value.time_local === 'number'
	);
}

export function isLogMessages(value: unknown): value is LogMessages {
	if (
		isObj(value) &&
		hasOwnProperty(value, 'messages') &&
		Array.isArray(value.messages)
	) {
		return value.messages.every(message => isLogMessage(message));
	}

	return false;
}

export function isResponseState(value: unknown): value is ResponseState {
	return (
		isObj(value) &&
		hasOwnProperty(value, 'type') &&
		typeof value.type === 'string' &&
		value.type === 'state' &&
		hasOwnProperty(value, 'messages') &&
		isLogMessages(value.messages)
	);
}
