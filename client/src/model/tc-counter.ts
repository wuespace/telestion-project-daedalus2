import { JavaMessage } from './java-message';

export interface TCSent extends JavaMessage {
	target: string;
	className: 'de.wuespace.telestion.project.daedalus2.mavlink.message.TCSent';
}

export interface TCReset extends JavaMessage {
	target: string;
	className: 'de.wuespace.telestion.project.daedalus2.mavlink.message.TCSent';
}
