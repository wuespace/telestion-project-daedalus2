import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';

export const widget: Widget = {
	name: 'csvDataExportWidget',
	title: 'CSV data export widget',
	version: '0.0.0',
	Widget: WidgetRenderer
};
