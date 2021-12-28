import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';
import { ConfigControls } from './config-controls';

export const widget: Widget = {
	name: 'stateChangeWidget',
	title: 'State Change Widget',
	version: '0.0.0',
	Widget: WidgetRenderer,
	ConfigControls
};
