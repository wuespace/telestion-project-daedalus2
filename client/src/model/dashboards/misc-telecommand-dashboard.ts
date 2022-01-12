import { Dashboard } from '@wuespace/telestion-client-types';

export const miscTelecommandDashboard: Dashboard = {
	title: 'Misc Telecommand',
	columns: 12,
	rows: 2,
	widgets: [
		{
			id: '0-system-telecommand-0',
			widgetName: 'aGpsWidget',
			width: 3,
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
			id: '0-system-telecommand-1',
			widgetName: 'batHeatingTcWidget',
			width: 3,
			height: 1,
			initialProps: {}
		},
		{
			id: '0-system-telecommand-2',
			widgetName: 'tcConsoleWidget',
			width: 6,
			height: 2,
			initialProps: {}
		},
		{
			id: '0-system-telecommand-3',
			widgetName: 'commonSeedTcsWidget',
			width: 2,
			height: 1,
			initialProps: {
				title: 'Common TCs Seed A',
				channel: 'tc-sender',
				target: 'seedA'
			}
		},
		{
			id: '0-system-telecommand-4',
			widgetName: 'commonSeedTcsWidget',
			width: 2,
			height: 1,
			initialProps: {
				title: 'Common TCs Seed B',
				channel: 'tc-sender',
				target: 'seedB'
			}
		},
		{
			id: '0-system-telecommand-5',
			widgetName: 'ejectorCameraWidget',
			width: 2,
			height: 1,
			initialProps: {}
		}
	]
};
