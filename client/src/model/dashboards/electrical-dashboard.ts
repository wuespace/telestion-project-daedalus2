import { Dashboard } from '@wuespace/telestion-client-types';

export const electricalDashboard: Dashboard = {
	title: 'Electrical',
	columns: 12,
	rows: 12,
	widgets: [
		{
			id: 'electrical-0',
			widgetName: 'stateWidget',
			width: 4,
			height: 6,
			initialProps: {
				seedASource: 'SEED_SYSTEM_T',
				seedBSource: 'SEED_SYSTEM_T',
				ejectorSource: 'EJECTOR_SYSTEM_T'
			}
		},
		{
			id: 'electrical-1',
			widgetName: 'checklistWidget',
			width: 4,
			height: 6,
			initialProps: {
				title: 'Note 2'
			}
		},
		{
			id: 'electrical-2',
			widgetName: 'currentValuesWidget',
			width: 4,
			height: 12,
			initialProps: {
				title: 'Current values',
				connections: [
					{
						address: 'latest/seedA/SEED_SYSTEM_T/state_cur',
						rps: 10,
						title: 'latest/seedA/SEED_SYSTEM_T/state_cur'
					}
				]
			}
		},
		{
			id: 'electrical-3',
			widgetName: 'seedEcam',
			width: 4,
			height: 6,
			initialProps: {
				seed: 'seedA',
				voltThreshold: 0.5,
				ampsThreshold: 0.005
			}
		},
		{
			id: 'electrical-4',
			widgetName: 'seedEcam',
			width: 4,
			height: 6,
			initialProps: {
				seed: 'seedB',
				voltThreshold: 0.5,
				ampsThreshold: 0.005
			}
		}
	]
};
