import { Dashboard } from '@wuespace/telestion-client-types';

export const electricalDashboard: Dashboard = {
	title: 'Electrical',
	columns: 3,
	rows: 21,
	widgets: [
		{
			id: 'electrical-0',
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
			id: 'electrical-1',
			widgetName: 'checklistWidget',
			width: 1,
			height: 10,
			initialProps: {
				title: 'Note 2'
			}
		},
		{
			id: 'electrical-2',
			widgetName: 'currentValuesWidget',
			width: 1,
			height: 20,
			initialProps: {
				title: 'Current values',
				connections: []
			}
		},
		{
			id: 'electrical-3',
			widgetName: 'seedEcam',
			width: 1,
			height: 10,
			initialProps: {
				seed: 'seedA',
				voltThreshold: 0.5,
				ampsThreshold: 0.005
			}
		},
		{
			id: 'electrical-4',
			widgetName: 'seedEcam',
			width: 1,
			height: 10,
			initialProps: {
				seed: 'seedB',
				voltThreshold: 0.5,
				ampsThreshold: 0.005
			}
		},
		{
			id: 'electrical-5',
			widgetName: 'timelineWidget',
			width: 3,
			height: 1,
			initialProps: {}
		}
	]
};
