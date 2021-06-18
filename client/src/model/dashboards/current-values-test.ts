import { Dashboard } from '@wuespace/telestion-client-types';
import { fakeProps as currentValuesProps } from './widget-props/current-values-props';
import { fakeProps as pgraphWidgetProps } from './widget-props/pgraph-props';

export const CurrentValuesTest: Dashboard = {
	title: 'Latest Data Test',
	columns: 12,
	rows: 12,
	widgets: [
		{
			widgetName: 'currentValuesWidget',
			width: 12,
			height: 3,
			title: 'Latest fake data',
			initialProps: currentValuesProps
		},
		{
			widgetName: 'pgraphWidget',
			width: 12,
			height: 4,
			title: 'Fake Data',
			initialProps: pgraphWidgetProps
		},
		{
			widgetName: 'cameraWidget',
			width: 4,
			height: 4,
			title: 'Camera Widget Seed 1',
			initialProps: {
				tcChannel: 'seed1-tc'
			}
		},
		{
			widgetName: 'cameraWidget',
			width: 4,
			height: 4,
			title: 'Camera Widget Seed 2',
			initialProps: {
				tcChannel: 'seed2-tc'
			}
		},
		{
			widgetName: 'freitextTc',
			width: 4,
			height: 4,
			title: 'Freitext Telecommand Widget',
			initialProps: {
				targetLabels: {
					seedA: 'Seed A',
					seedB: 'Seed B',
					ejector: 'Ejector'
				}
			}
		}
	]
};
