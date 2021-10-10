import { Widget } from '@wuespace/telestion-client-types';
import { ConfigControls } from './config-controls';
import { Widget as WidgetRenderer } from './widget';
import { WidgetProps } from './model';

export const widget: Widget<WidgetProps> = {
	name: 'seedEcam',
	Widget: WidgetRenderer,
	version: 'v0.0.1',
	ConfigControls
};
