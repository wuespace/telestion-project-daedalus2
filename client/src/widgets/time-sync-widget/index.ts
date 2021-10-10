import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';

export const widget: Widget = {
	name: 'timeSyncWidget',
	title: 'time-sync-widget',
	version: '0.0.0',
	Widget: WidgetRenderer
};
