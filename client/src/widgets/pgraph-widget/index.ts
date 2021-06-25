import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';
import { WidgetProps } from './model';
import { ConfigControls } from './components/config-controls';

export const widget: Widget<WidgetProps> = {
	name: 'pgraphWidget',
	title: 'P-Graph Widget',
	version: '0.1.0',
	Widget: WidgetRenderer,
	ConfigControls
};

export type { WidgetProps as PGraphWidgetProps } from './model';
