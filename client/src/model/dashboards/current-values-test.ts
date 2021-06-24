import { Dashboard } from '@wuespace/telestion-client-types';
import { fakeProps as currentValuesProps } from './widget-props/current-values-props';
import { fakeProps as pgraphWidgetProps } from './widget-props/pgraph-props';

export const CurrentValuesTest: Dashboard = {
	title: 'Latest Data Test',
	columns: 12,
	rows: 12,
	widgets: [
		{
			id: '0-0',
			widgetName: 'currentValuesWidget',
			width: 12,
			height: 3,
			initialProps: currentValuesProps
		},
		{
			id: '0-1',
			widgetName: 'pgraphWidget',
			width: 12,
			height: 4,
			initialProps: pgraphWidgetProps
		},
		{
			id: '0-2',
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
			id: '0-3',
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
			id: '0-4',
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
