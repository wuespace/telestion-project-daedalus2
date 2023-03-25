import { JsonSerializable } from '@wuespace/telestion-client-types';

export const requestChannel = 'csv-data-exporter#request';

export interface CSVDataRequest extends Record<string, JsonSerializable> {
	target: string;
	className: 'de.wuespace.telestion.project.daedalus2.mongodb.message.CSVDataRequest';
}

export interface CSVData extends Record<string, JsonSerializable> {
	count: number;
	content: string;
	className: 'de.wuespace.telestion.project.daedalus2.mongodb.message.CSVData';
}
