import { Dashboard } from '@wuespace/telestion-client-types';

export const telecommandDashboard: Dashboard = {
	title: 'Telecommand',
	columns: 20,
	rows: 2,
	widgets: [
		{
			id: 'telecommand-0',
			widgetName: 'stateWidget',
			width: 8,
			height: 1,
			initialProps: {
				seedASource: 'SEED_HEARTBEAT',
				seedBSource: 'SEED_HEARTBEAT',
				ejectorSource: 'EJECTOR_HEARTBEAT'
			}
		},
		{
			id: 'telecommand-1',
			widgetName: 'stateChangeWidget',
			width: 4,
			height: 1,
			initialProps: {}
		},
		{
			id: 'telecommand-2',
			widgetName: 'timeSyncWidget',
			width: 4,
			height: 1,
			initialProps: {
				title: 'Note 3'
			}
		},
		{
			id: 'telecommand-3',
			widgetName: 'aGpsWidget',
			width: 4,
			height: 1,
			initialProps: {
				title: 'A-GPS Upload',
				targets: {
					seedA: 'Seed A',
					seedB: 'Seed B'
				}
			}
		},
		{
			id: 'telecommand-4',
			widgetName: 'commonSeedTcsWidget',
			width: 3,
			height: 1,
			initialProps: {
				title: 'Common TCs Seed A',
				channel: 'tc-sender',
				target: 'seedA'
			}
		},
		{
			id: 'telecommand-5',
			widgetName: 'commonSeedTcsWidget',
			width: 3,
			height: 1,
			initialProps: {
				title: 'Common TCs Seed B',
				channel: 'tc-sender',
				target: 'seedB'
			}
		},
		{
			id: 'telecommand-6',
			widgetName: 'freitextTcWidget',
			width: 3,
			height: 1,
			initialProps: {
				title: 'Freitext Telecommand Widget',
				channel: 'tc-sender',
				targets: {
					seedA: 'Seed A',
					seedB: 'Seed B',
					ejector: 'Ejector'
				}
			}
		},
		{
			id: 'telecommand-7',
			widgetName: 'batHeatingTcWidget',
			width: 4,
			height: 1,
			initialProps: {}
		},
		{
			id: 'telecommand-8',
			widgetName: 'radioSilenceWidget',
			width: 3,
			height: 1,
			initialProps: {}
		},
		{
			id: 'telecommand-9',
			widgetName: 'ejectorCameraWidget',
			width: 4,
			height: 1,
			initialProps: {}
		}
	]
};
