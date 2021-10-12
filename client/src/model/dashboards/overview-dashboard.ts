import { Dashboard } from '@wuespace/telestion-client-types';

export const overviewDashboard: Dashboard = {
	title: 'Overview',
	columns: 12,
	rows: 12,
	widgets: [
		{
			id: 'overview-0',
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
			id: 'overview-1',
			widgetName: 'checklistWidget',
			width: 6,
			height: 6,
			initialProps: {
				title: 'Note 2'
			}
		},
		{
			id: 'overview-2',
			widgetName: 'currentValuesWidget',
			width: 6,
			height: 6,
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
			id: 'overview-3',
			widgetName: 'simpleGraphWidget',
			width: 6,
			height: 6,
			initialProps: {
				seconds: 60,
				maxSamples: 30,
				title: 'Some graph widget',
				minY: 0,
				maxY: 1,
				series: [
					{
						key: 'ts/seedA/SEED_SYSTEM_T/imu_gyro_z',
						minMax: true,
						color: 'hsl(0, 100%, 50%)'
					}
				]
			}
		}
	]
};
