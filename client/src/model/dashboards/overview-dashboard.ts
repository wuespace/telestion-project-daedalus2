import { Dashboard } from '@wuespace/telestion-client-types';

export const overviewDashboard: Dashboard = {
	title: 'Overview',
	columns: 2,
	rows: 21,
	widgets: [
		{
			id: 'overview-0',
			widgetName: 'stateWidget',
			width: 1,
			height: 10,
			initialProps: {
				seedASource: 'SEED_HEARTBEAT',
				seedBSource: 'SEED_HEARTBEAT',
				ejectorSource: 'EJECTOR_HEARTBEAT'
			}
		},
		{
			id: 'overview-2',
			widgetName: 'currentValuesWidget',
			width: 1,
			height: 20,
			initialProps: {
				title: 'Current values',
				connections: []
			}
		},
		{
			id: 'overview-3',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 10,
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
			id: 'overview-4',
			widgetName: 'timelineWidget',
			width: 2,
			height: 1,
			initialProps: {}
		}
	]
};
