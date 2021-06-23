import { GenericProps } from '@wuespace/telestion-client-types';
import { Options } from './options';

export interface WidgetProps extends GenericProps, Options {
	title: string;
}
