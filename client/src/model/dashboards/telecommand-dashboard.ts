import { Dashboard } from '@wuespace/telestion-client-types';

export const telecommandDashboard: Dashboard = {
	title: 'Telecommand',
	columns: 12,
	rows: 12,
	widgets: [
		{
			id: 'telecommand--1',
			widgetName: 'stateWidget',
			width: 6,
			height: 6,
			initialProps: {
				seedASource: 'SEED_SYSTEM_T',
				seedBSource: 'SEED_SYSTEM_T',
				ejectorSource: 'EJECTOR_SYSTEM_T'
			}
		},
		{
			id: 'telecommand-0',
			widgetName: 'timeSyncWidget',
			width: 3,
			height: 6,
			initialProps: {
				title: 'Note 3'
			}
		},
		{
			id: 'telecommand-1',
			widgetName: 'aGpsWidget',
			width: 3,
			height: 6,
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
			width: 3,
			height: 6,
			initialProps: {
				title: 'Common TCs Seed A',
				channel: 'tc-sender',
				target: 'seedA'
			}
		},
		{
			id: 'telecommand-3',
			widgetName: 'commonSeedTcsWidget',
			width: 3,
			height: 6,
			initialProps: {
				title: 'Common TCs Seed B',
				channel: 'tc-sender',
				target: 'seedB'
			}
		},
		{
			id: 'telecommand-4',
			widgetName: 'freitextTcWidget',
			width: 3,
			height: 6,
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
			width: 3,
			height: 6,
			initialProps: {}
		}
	]
};
