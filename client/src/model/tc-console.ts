import { JavaMessage } from './java-message';
import { hasOwnProperty, isObj } from '../lib/core-utils';

export interface Log extends JavaMessage {
	/**
	 * The source of the log messages.
	 */
	source: string;

	/**
	 * All messages received from the source separated by a newline '\n'.
	 */
	messages: string[];

	className: 'de.wuespace.telestion.project.daedalus2.mavlink.telecommand_console.Log';
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
	state: Log;

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

export function isLogMessage(value: unknown): value is Log {
	return (
		isObj(value) &&
		hasOwnProperty(value, 'source') &&
		hasOwnProperty(value, 'messages') &&
		typeof value.source === 'string' &&
		Array.isArray(value.messages)
	);
}

export function isResponseState(value: unknown): value is ResponseState {
	return (
		isObj(value) &&
		hasOwnProperty(value, 'type') &&
		typeof value.type === 'string' &&
		value.type === 'state' &&
		hasOwnProperty(value, 'state') &&
		isLogMessage(value.state)
	);
}
