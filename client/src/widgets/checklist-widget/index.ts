import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';
import { WidgetProps } from './model';

export const widget: Widget<WidgetProps> = {
	name: 'checklistWidget',
	title: 'Checklist Widget',
	version: '0.0.0',
	Widget: WidgetRenderer
};
