import { Dashboard } from '@wuespace/telestion-client-types';
import { fakeProps } from './widget-props/pgraph-props';

export const graphDashboard: Dashboard = {
	title: 'Graphes',
	columns: 2,
	rows: 3,
	widgets: [
		{
			id: 'graphes-0',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		},
		{
			id: 'graphes-1',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		},
		{
			id: 'graphes-2',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		},
		{
			id: 'graphes-3',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		},
		{
			id: 'graphes-4',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		},
		{
			id: 'graphes-5',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		}
	]
};
