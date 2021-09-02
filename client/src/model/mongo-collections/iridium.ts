import { JsonSerializable } from '@wuespace/telestion-client-types';
import { JavaMessage } from '../java-message';
import { MongoCollection } from './base';

export interface IEPayloadData extends Record<string, JsonSerializable> {}

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
	data: IEPayloadData;
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
