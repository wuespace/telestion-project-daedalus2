import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';

export const widget: Widget = {
	name: 'radioSilenceWidget',
	title: 'radio-silence-widget',
	version: '0.0.0',
	Widget: WidgetRenderer
};
