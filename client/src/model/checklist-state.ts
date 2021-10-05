import { JavaMessage } from './java-message';

// common types
export interface ChecklistPoint extends JavaMessage {
	name: string;
	className: 'de.wuespace.telestion.project.daedalus2.checklist.ChecklistPoint';
}

export interface ChecklistState extends JavaMessage {
	points: ChecklistPoint[];
	states: boolean[];
	className: 'de.wuespace.telestion.project.daedalus2.checklist.ChecklistState';
}

// requests
interface ChecklistRequestBase extends JavaMessage {
	type: 'state' | 'new-state';
}

export interface RequestState extends ChecklistRequestBase {
	type: 'state';
	className: 'de.wuespace.telestion.project.daedalus2.checklist.message.RequestState';
}

export interface RequestNewState extends ChecklistRequestBase {
	type: 'new-state';
	index: number;
	newState: boolean;
	className: 'de.wuespace.telestion.project.daedalus2.checklist.message.RequestNewState';
}

export type ChecklistRequest = RequestState | RequestNewState;

// responses
interface ChecklistResponseBase extends JavaMessage {
	type: 'state' | 'new-state';
}

export interface ResponseState extends ChecklistResponseBase {
	type: 'state';
	state: ChecklistState;
	className: 'de.wuespace.telestion.project.daedalus2.checklist.message.ResponseState';
}

export interface ResponseNewState extends ChecklistResponseBase {
	type: 'new-state';
	result: number;
	className: 'de.wuespace.telestion.project.daedalus2.checklist.message.ResponseNewState';
}

export type ChecklistResponse = ResponseState | ResponseNewState;
