import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';
import { WidgetProps } from './model';
import { ConfigControls } from './config-controls';

export const widget: Widget<WidgetProps> = {
	name: 'stateChangeWidget',
	title: 'State Change Widget',
	version: '0.0.0',
	Widget: WidgetRenderer,
	ConfigControls
};
