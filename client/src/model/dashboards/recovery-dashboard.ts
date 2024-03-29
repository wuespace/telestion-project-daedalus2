import { Dashboard } from '@wuespace/telestion-client-types';

export const recoveryDashboard: Dashboard = {
	title: 'Recovery',
	columns: 2,
	rows: 21,
	widgets: [
		{
			id: 'recovery-0',
			widgetName: 'iridiumMapWidget',
			width: 1,
			height: 20
		},
		{
			id: 'recovery-1',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 10,
			initialProps: {
				seconds: 60,
				maxSamples: 30,
				title: 'Seed altitudes',
				minY: 0,
				maxY: 50000,
				series: [
					{
						key: 'ts/seedA/iridium/payload/valid/altitude',
						color: 'hsl(240, 100%, 50%)', // blue
						minMax: true
					},
					{
						key: 'ts/seedB/iridium/payload/valid/altitude',
						color: 'hsl(30, 100%, 50%)', // orange
						minMax: true
					},
					{
						key: 'ts/seedA/iridium/payload/latest/altitude',
						color: 'hsl(270, 100%, 50%)', // blue + 30°
						minMax: true
					},
					{
						key: 'ts/seedB/iridium/payload/latest/altitude',
						color: 'hsl(60, 100%, 50%)', // orange + 30°
						minMax: true
					}
				]
			}
		},
		{
			id: 'recovery-2',
			widgetName: 'currentValuesWidget',
			width: 1,
			height: 10,
			initialProps: {
				title: 'Seed payload data',
				connections: [
					{
						address: 'latest/seedA/iridium/payload/valid/latitude',
						title: 'latest/seedA/iridium/payload/valid/latitude',
						rps: 1
					},
					{
						address: 'latest/seedA/iridium/payload/valid/longitude',
						title: 'latest/seedA/iridium/payload/valid/longitude',
						rps: 1
					},
					{
						address: 'latest/seedA/iridium/payload/valid/altitude',
						title: 'latest/seedA/iridium/payload/valid/altitude',
						rps: 1
					},
					{
						address: 'latest/seedA/iridium/payload/latest/latitude',
						title: 'latest/seedA/iridium/payload/latest/latitude',
						rps: 1
					},
					{
						address: 'latest/seedA/iridium/payload/latest/longitude',
						title: 'latest/seedA/iridium/payload/latest/longitude',
						rps: 1
					},
					{
						address: 'latest/seedA/iridium/payload/latest/altitude',
						title: 'latest/seedA/iridium/payload/latest/altitude',
						rps: 1
					},
					{
						address: 'latest/seedB/iridium/payload/valid/latitude',
						title: 'latest/seedB/iridium/payload/valid/latitude',
						rps: 1
					},
					{
						address: 'latest/seedB/iridium/payload/valid/longitude',
						title: 'latest/seedB/iridium/payload/valid/longitude',
						rps: 1
					},
					{
						address: 'latest/seedB/iridium/payload/valid/altitude',
						title: 'latest/seedB/iridium/payload/valid/altitude',
						rps: 1
					},
					{
						address: 'latest/seedB/iridium/payload/latest/latitude',
						title: 'latest/seedB/iridium/payload/latest/latitude',
						rps: 1
					},
					{
						address: 'latest/seedB/iridium/payload/latest/longitude',
						title: 'latest/seedB/iridium/payload/latest/longitude',
						rps: 1
					},
					{
						address: 'latest/seedB/iridium/payload/latest/altitude',
						title: 'latest/seedB/iridium/payload/latest/altitude',
						rps: 1
					}
				]
			}
		},
		{
			id: 'recovery-3',
			widgetName: 'iridiumTimelineWidget',
			width: 2,
			height: 1,
			initialProps: {}
		}
	]
};
