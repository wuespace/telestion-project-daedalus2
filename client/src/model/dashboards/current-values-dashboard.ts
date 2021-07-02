import { Dashboard } from '@wuespace/telestion-client-types';
import { fakeProps as currentValuesProps } from './widget-props/current-values-props';
import { fakeProps as pgraphWidgetProps } from './widget-props/pgraph-props';

export const currentValuesDashboard: Dashboard = {
	title: 'Current Values',
	columns: 12,
	rows: 12,
	widgets: [
		{
			id: 'current-values-0',
			widgetName: 'currentValuesWidget',
			width: 12,
			height: 3,
			initialProps: currentValuesProps
		},
		{
			id: 'current-values-1',
			widgetName: 'pgraphWidget',
			width: 12,
			height: 4,
			initialProps: pgraphWidgetProps
		},
		{
			id: 'current-values-2',
			widgetName: 'cameraWidget',
			width: 4,
			height: 5,
			initialProps: {
				title: 'Camera Widget Seed A',
				channel: 'tc-sender',
				target: 'seedA'
			}
		},
		{
			id: 'current-values-3',
			widgetName: 'cameraWidget',
			width: 4,
			height: 5,
			initialProps: {
				title: 'Camera Widget Seed B',
				channel: 'tc-sender',
				target: 'seedB'
			}
		},
		{
			id: 'current-values-4',
			widgetName: 'freitextTcWidget',
			width: 4,
			height: 5,
			initialProps: {
				title: 'Freitext Telecommand Widget',
				channel: 'tc-sender',
				targets: {
					seedA: 'Seed A',
					seedB: 'Seed B',
					ejector: 'Ejector'
				}
			}
		}
	]
};