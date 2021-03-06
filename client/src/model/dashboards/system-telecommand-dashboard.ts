import { Dashboard } from '@wuespace/telestion-client-types';

export const systemTelecommandDashboard: Dashboard = {
	title: 'System Telecommand',
	columns: 4,
	rows: 21,
	widgets: [
		{
			id: '1-telecommand-0',
			widgetName: 'stateWidget',
			width: 2,
			height: 10,
			initialProps: {
				seedASource: 'SEED_HEARTBEAT',
				seedBSource: 'SEED_HEARTBEAT',
				ejectorSource: 'EJECTOR_HEARTBEAT'
			}
		},
		{
			id: '1-telecommand-1',
			widgetName: 'tcConsoleWidget',
			width: 2,
			height: 20,
			initialProps: {}
		},
		{
			id: '1-telecommand-2',
			widgetName: 'stateChangeWidget',
			width: 1,
			height: 10,
			initialProps: {}
		},
		{
			id: '1-telecommand-4',
			widgetName: 'radioSilenceWidget',
			width: 1,
			height: 10,
			initialProps: {}
		},
		{
			id: '1-telecommand-5',
			widgetName: 'timelineWidget',
			width: 4,
			height: 1,
			initialProps: {}
		}
	]
};
