import { GenericProps } from '@wuespace/telestion-client-types';
import { Options } from './options';
import { PredefinedOptions } from './predefined-options';

export interface WidgetProps extends GenericProps, Options, PredefinedOptions {
	/**
	 * The title of the widget.
	 */
	title: string;
}
