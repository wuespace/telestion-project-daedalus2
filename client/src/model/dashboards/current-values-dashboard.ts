import { Dashboard } from '@wuespace/telestion-client-types';

export const currentValuesDashboard: Dashboard = {
	title: 'Current Values',
	columns: 1,
	rows: 21,
	widgets: [
		{
			id: 'current-values',
			widgetName: 'currentValuesWidget',
			width: 1,
			height: 20,
			initialProps: {
				title: 'Current values',
				connections: []
			}
		},
		{
			id: 'current-values-1',
			widgetName: 'timelineWidget',
			width: 1,
			height: 1,
			initialProps: {}
		}
	]
};
