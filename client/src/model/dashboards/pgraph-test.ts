import { Dashboard } from '@wuespace/telestion-client-types';
import { fakeProps } from './widget-props/pgraph-props';

export const PGraphTest: Dashboard = {
	title: 'Overview',
	columns: 2,
	rows: 3,
	widgets: [
		{
			id: '1-0',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		},
		{
			id: '1-1',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		},
		{
			id: '1-2',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		},
		{
			id: '1-3',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		},
		{
			id: '1-4',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		},
		{
			id: '1-5',
			widgetName: 'pgraphWidget',
			width: 1,
			height: 1,
			initialProps: fakeProps
		}
	]
};
