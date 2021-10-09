import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';
import { StateWidgetConfig } from './state-widget-config';
import { ConfigControls } from './config-controls';

export const widget: Widget<StateWidgetConfig> = {
	name: 'stateWidget',
	title: 'State Widget',
	version: '0.1.0',
	Widget: WidgetRenderer,
	ConfigControls: ConfigControls
};
