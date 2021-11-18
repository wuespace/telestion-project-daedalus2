import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';
import { ConfigControls } from './config-controls';
import { WidgetProps } from './model';

export const widget: Widget<WidgetProps> = {
	name: 'stateWidget',
	title: 'State Widget',
	version: '0.1.0',
	Widget: WidgetRenderer,
	ConfigControls: ConfigControls
};
