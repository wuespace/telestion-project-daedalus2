import { Dashboard } from '@wuespace/telestion-client-types';
import { fakeProps } from './widget-props/pgraph-props';

export const PGraphTest: Dashboard = {
	title: 'Overview',
	columns: 2,
	rows: 3,
	widgets: [
		{
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			title: 'PGraph',
			initialProps: fakeProps
		},
		{
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			title: 'PGraph',
			initialProps: fakeProps
		},
		{
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			title: 'PGraph',
			initialProps: fakeProps
		},
		{
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			title: 'PGraph',
			initialProps: fakeProps
		},
		{
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			title: 'PGraph',
			initialProps: fakeProps
		},
		{
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			title: 'PGraph',
			initialProps: fakeProps
		}
	]
};
