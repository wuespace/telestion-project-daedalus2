import { Dashboard } from '@wuespace/telestion-client-types';

export const currentValuesDashboard: Dashboard = {
	title: 'Current Values',
	columns: 12,
	rows: 12,
	widgets: [
		{
			id: 'current-values-0',
			widgetName: 'currentValuesWidget',
			width: 12,
			height: 7,
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
			id: 'current-values-2',
			widgetName: 'aGpsWidget',
			width: 3,
			height: 5,
			initialProps: {
				title: 'A-GPS Upload',
				targets: {
					seedA: 'Seed A',
					seedB: 'Seed B'
				}
			}
		},
		{
			id: 'current-values-3',
			widgetName: 'cameraWidget',
			width: 3,
			height: 5,
			initialProps: {
				title: 'Camera Widget Seed A',
				channel: 'tc-sender',
				target: 'seedA'
			}
		},
		{
			id: 'current-values-4',
			widgetName: 'cameraWidget',
			width: 3,
			height: 5,
			initialProps: {
				title: 'Camera Widget Seed B',
				channel: 'tc-sender',
				target: 'seedB'
			}
		},
		{
			id: 'current-values-5',
			widgetName: 'freitextTcWidget',
			width: 3,
			height: 5,
			initialProps: {
				title: 'Freitext Telecommand Widget',
				channel: 'tc-sender',
				targets: {
					seedA: 'Seed A',
					seedB: 'Seed B',
					ejector: 'Ejector'
				}
			}
		}
	]
};
