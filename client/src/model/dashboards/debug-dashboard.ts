import { Dashboard } from '@wuespace/telestion-client-types';

export const debugDashboard: Dashboard = {
	title: 'Debug',
	columns: 1,
	rows: 20,
	widgets: [
		{
			id: 'debug-0',
			widgetName: 'eventbusDebugWidget',
			width: 1,
			height: 19
		},
		{
			id: 'debug-2',
			widgetName: 'timelineWidget',
			width: 1,
			height: 1
		}
	]
};
