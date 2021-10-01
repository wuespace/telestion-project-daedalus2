import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';
import { WidgetProps } from './model';

export const widget: Widget<WidgetProps> = {
	name: 'aGpsWidget',
	title: 'A-GPS Widget',
	version: '0.0.0',
	Widget: WidgetRenderer
};

export type { WidgetProps as AGpsWidgetProps } from './model';
