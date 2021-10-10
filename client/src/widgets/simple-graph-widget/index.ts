import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';
import { ConfigControls } from './config-controls';
import { WidgetProps } from './widget-props';

export const widget: Widget<WidgetProps> = {
	name: 'simpleGraphWidget',
	title: 'simple-graph-widget',
	version: '0.0.0',
	Widget: WidgetRenderer,
	ConfigControls: ConfigControls
};
