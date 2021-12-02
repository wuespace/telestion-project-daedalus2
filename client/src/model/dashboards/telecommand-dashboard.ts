import { Dashboard } from '@wuespace/telestion-client-types';

export const telecommandDashboard: Dashboard = {
	title: 'Telecommand',
	columns: 20,
	rows: 2,
	widgets: [
		{
			id: 'telecommand--1',
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
			id: 'telecommand-6',
			widgetName: 'stateChangeWidget',
			width: 4,
			height: 1,
			initialProps: {
				title: 'State Change',
				targets: {
					seedA: 'Seed A',
					seedB: 'Seed B',
					ejector: 'Ejector'
				}
			}
		},
		{
			id: 'telecommand-0',
			widgetName: 'timeSyncWidget',
			width: 4,
			height: 1,
			initialProps: {
				title: 'Note 3'
			}
		},
		{
			id: 'telecommand-1',
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
			id: 'telecommand-2',
			widgetName: 'commonSeedTcsWidget',
			width: 5,
			height: 1,
			initialProps: {
				title: 'Common TCs Seed A',
				channel: 'tc-sender',
				target: 'seedA'
			}
		},
		{
			id: 'telecommand-3',
			widgetName: 'commonSeedTcsWidget',
			width: 5,
			height: 1,
			initialProps: {
				title: 'Common TCs Seed B',
				channel: 'tc-sender',
				target: 'seedB'
			}
		},
		{
			id: 'telecommand-4',
			widgetName: 'freitextTcWidget',
			width: 5,
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
			id: 'telecommand-5',
			widgetName: 'batHeatingTcWidget',
			width: 5,
			height: 1,
			initialProps: {}
		}
	]
};
