import { Widget } from '@wuespace/telestion-client-types';
import { Widget as WidgetRenderer } from './widget';
import { WidgetProps } from './widgetProps';

export const widget: Widget<WidgetProps> = {
	name: 'freitextTc',
	Widget: WidgetRenderer
};
