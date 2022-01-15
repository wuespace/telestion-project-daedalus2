import { Dashboard } from '@wuespace/telestion-client-types';

export const graphDashboard: Dashboard = {
	title: 'Graphes',
	columns: 2,
	rows: 63,
	widgets: [
		{
			id: 'graphes-0',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 60,
			initialProps: {
				seconds: 60,
				maxSamples: 30,
				title: 'Some graph widget',
				minY: 0,
				maxY: 1,
				series: []
			}
		},
		{
			id: 'graphes-1',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 20,
			initialProps: {
				seconds: 60,
				maxSamples: 30,
				title: 'Some graph widget',
				minY: 0,
				maxY: 1,
				series: []
			}
		},
		{
			id: 'graphes-2',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 20,
			initialProps: {
				seconds: 60,
				maxSamples: 30,
				title: 'Some graph widget',
				minY: 0,
				maxY: 1,
				series: []
			}
		},
		{
			id: 'graphes-3',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 20,
			initialProps: {
				seconds: 60,
				maxSamples: 30,
				title: 'Some graph widget',
				minY: 0,
				maxY: 1,
				series: []
			}
		},
		{
			id: 'graphes-4',
			widgetName: 'timelineWidget',
			width: 2,
			height: 3,
			initialProps: {}
		}
	]
};
