import { JavaMessage } from './java-message';

/// common types
export interface AGpsData extends JavaMessage {
	name: string;
	encodedData: string;
	className: 'de.wuespace.telestion.project.daedalus2.gps.AGpsData';
}

export interface AGpsState extends JavaMessage {
	state: 'idle' | 'transmitting';
	dataName: string | null;
	target: string | null;
	progress: number;
	className: 'de.wuespace.telestion.project.daedalus2.gps.AGpsState';
}

/// requests
interface AGpsRequestBase extends JavaMessage {
	type: 'state' | 'new-data' | 'new-target' | 'transmission' | 'abort';
}

export interface RequestState extends AGpsRequestBase {
	type: 'state';
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.RequestState';
}

export interface RequestNewData extends AGpsRequestBase {
	type: 'new-data';
	data: AGpsData;
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.RequestNewData';
}

export interface RequestNewTarget extends AGpsRequestBase {
	type: 'new-target';
	target: string;
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.RequestNewTarget';
}

export interface RequestTransmission extends AGpsRequestBase {
	type: 'transmission';
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.RequestTransmission';
}

export interface RequestAbort extends AGpsRequestBase {
	type: 'abort';
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.RequestAbort';
}

export type AGpsRequest =
	| RequestState
	| RequestNewData
	| RequestNewTarget
	| RequestTransmission
	| RequestAbort;

/// responses
interface AGpsResponseBase extends JavaMessage {
	type: 'state' | 'new-data' | 'new-target' | 'transmission' | 'abort';
}

export interface ResponseState extends AGpsResponseBase {
	type: 'state';
	state: AGpsState;
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.ResponseState';
}

export interface ResponseNewData extends AGpsResponseBase {
	type: 'new-data';
	result: 0 | 1;
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.ResponseNewData';
}

export interface ResponseNewTarget extends AGpsResponseBase {
	type: 'new-target';
	result: 0 | 1;
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.ResponseNewTarget';
}

export interface ResponseTransmission extends AGpsResponseBase {
	type: 'transmission';
	result: 0 | 1 | 2 | 3 | 4;
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.ResponseTransmission';
}

export interface ResponseAbort extends AGpsResponseBase {
	type: 'abort';
	result: 0 | 1;
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.ResponseAbort';
}

export type AGpsResponse =
	| ResponseState
	| ResponseNewData
	| ResponseNewTarget
	| ResponseTransmission
	| ResponseAbort;

export const requestChannel = 'a-gps-transmitter#request';
export const notifyChannel = 'a-gps-transmitter#notify';
