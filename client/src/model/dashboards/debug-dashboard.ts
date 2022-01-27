import { Dashboard } from '@wuespace/telestion-client-types';

export const debugDashboard: Dashboard = {
	title: 'Debug',
	columns: 4,
	rows: 21,
	widgets: [
		{
			id: 'debug-0',
			widgetName: 'eventbusDebugWidget',
			width: 2,
			height: 20
		},
		{
			id: 'debug-1',
			widgetName: 'modalWidget',
			width: 1,
			height: 5
		},
		{
			id: 'debug-2',
			widgetName: 'timelineWidget',
			width: 4,
			height: 1
		}
	]
};
