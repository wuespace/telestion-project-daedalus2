import { Dashboard } from '@wuespace/telestion-client-types';

export const notesDashboard: Dashboard = {
	title: 'Notes',
	columns: 12,
	rows: 12,
	widgets: [
		{
			id: 'notes-0',
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
			id: 'notes-1',
			widgetName: 'checklistWidget',
			width: 6,
			height: 6,
			initialProps: {
				title: 'Note 2'
			}
		},
		{
			id: 'notes-2',
			widgetName: 'timeSyncWidget',
			width: 6,
			height: 6,
			initialProps: {
				title: 'Note 3'
			}
		},
		{
			id: 'notes-3',
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
