import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';

export const widget: Widget = {
	name: 'iridiumTimelineWidget',
	title: 'Iridium Timeline Widget',
	version: '0.0.0',
	Widget: WidgetRenderer
};
