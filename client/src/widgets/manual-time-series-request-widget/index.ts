import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';

export const widget: Widget = {
	name: 'manualTimeSeriesRequestWidget',
	title: 'manual-time-series-request-widget',
	version: '0.0.0',
	Widget: WidgetRenderer
};
