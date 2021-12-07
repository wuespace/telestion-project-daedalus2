import { Dashboard } from '@wuespace/telestion-client-types';

export const debugDashboard: Dashboard = {
	title: 'Debug',
	columns: 1,
	rows: 1,
	widgets: [
		{
			id: 'debug-0',
			widgetName: 'eventbusDebugWidget',
			width: 1,
			height: 1
		}
	]
};
