import { Widget } from '@wuespace/telestion-client-types';
import { WidgetProps } from './model';
import { Widget as WidgetRenderer } from './widget';
import { ConfigControls } from './config-controls';

export const widget: Widget<WidgetProps> = {
	name: 'noteWidget',
	title: 'Note Widget',
	version: '0.1.0',
	Widget: WidgetRenderer,
	ConfigControls
};
