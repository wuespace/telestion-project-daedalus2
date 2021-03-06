import { GenericProps } from '@wuespace/telestion-client-types';
import { Connection } from './connection';

export interface WidgetProps extends GenericProps {
	title: string;
	connections: Connection[];
}
