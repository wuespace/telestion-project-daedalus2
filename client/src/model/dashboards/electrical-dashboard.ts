import { Dashboard } from '@wuespace/telestion-client-types';

export const electricalDashboard: Dashboard = {
	title: 'Electrical',
	columns: 12,
	rows: 12,
	widgets: [
		{
			id: 'electrical-0',
			widgetName: 'stateWidget',
			width: 6,
			height: 6,
			initialProps: {
				seedASource: 'SEED_HEARTBEAT',
				seedBSource: 'SEED_HEARTBEAT',
				ejectorSource: 'EJECTOR_HEARTBEAT'
			}
		},
		{
			id: 'electrical-1',
			widgetName: 'checklistWidget',
			width: 6,
			height: 6,
			initialProps: {
				title: 'Note 2'
			}
		},
		{
			id: 'electrical-2',
			widgetName: 'currentValuesWidget',
			width: 6,
			height: 6,
			initialProps: {
				title: 'Current values',
				connections: [
					{
						address: 'latest/seedA/SEED_HEARTBEAT/state_cur',
						rps: 10,
						title: 'latest/seedA/SEED_HEARTBEAT/state_cur'
					}
				]
			}
		},
		{
			id: 'electrical-3',
			widgetName: 'seedEcam',
			width: 6,
			height: 6,
			initialProps: {
				seed: 'seedA',
				voltThreshold: 0.5,
				ampsThreshold: 0.005
			}
		}
	]
};
