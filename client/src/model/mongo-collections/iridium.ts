import { JavaMessage } from '../java-message';
import { MongoCollection } from './base';

interface AppendixBase extends JavaMessage {
	type: 'none' | 'rotation' | 'blade' | 'velocity';
}

export interface AppendixNone extends AppendixBase {
	type: 'none';
	className: 'de.wuespace.telestion.project.daedalus2.iridium.message.AppendixNone';
}

export interface AppendixRotation extends AppendixBase {
	type: 'rotation';
	rotation_rate: number;
	className: 'de.wuespace.telestion.project.daedalus2.iridium.message.AppendixRotation';
}

export interface AppendixBlade extends AppendixBase {
	type: 'blade';
	blade_pitch: number;
	className: 'de.wuespace.telestion.project.daedalus2.iridium.message.AppendixBlade';
}

export interface AppendixVelocity extends AppendixBase {
	type: 'velocity';
	vertical_velocity: number;
	className: 'de.wuespace.telestion.project.daedalus2.iridium.message.AppendixVelocity';
}

export type PayloadAppendix =
	| AppendixNone
	| AppendixRotation
	| AppendixBlade
	| AppendixVelocity;

export interface IEHeader extends JavaMessage {
	type: 'header';
	cdr: number;
	imei: string;
	session_status: number;
	momsn: number;
	mtmsn: number;
	time: number;
	className: 'de.wuespace.telestion.project.daedalus2.iridium.message.IEHeader';
}

export interface IEPayload extends JavaMessage {
	type: 'payload';
	latitude: number;
	longitude: number;
	altitude: number;
	payload_appendix: PayloadAppendix;
	className: 'de.wuespace.telestion.project.daedalus2.iridium.message.IEPayload';
}

export interface IELocation extends JavaMessage {
	type: 'location';
	reserved: number;
	format_code: number;
	latitude: number;
	longitude: number;
	center_point_radius: number;
	className: 'de.wuespace.telestion.project.daedalus2.iridium.message.IELocation';
}

export interface IEConfirmation extends JavaMessage {
	type: 'confirmation';
	success: boolean;
	className: 'de.wuespace.telestion.project.daedalus2.iridium.message.IEConfirmation';
}

export type InformationElement =
	| IEHeader
	| IEPayload
	| IELocation
	| IEConfirmation;

export interface Iridium extends MongoCollection {
	elements: Array<InformationElement>;
	className: 'de.wuespace.telestion.project.daedalus2.iridium.message.IridiumMessage';
}
