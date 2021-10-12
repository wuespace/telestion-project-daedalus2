import { Dashboard } from '@wuespace/telestion-client-types';

export const graphDashboard: Dashboard = {
	title: 'Graphes',
	columns: 2,
	rows: 3,
	widgets: [
		{
			id: 'graphes-0',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 3,
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
		},
		{
			id: 'graphes-1',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 1,
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
		},
		{
			id: 'graphes-2',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 1,
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
		},
		{
			id: 'graphes-3',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 1,
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
