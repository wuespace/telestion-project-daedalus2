import { Dashboard } from '@wuespace/telestion-client-types';

export const recoveryDashboard: Dashboard = {
	title: 'Recovery',
	columns: 2,
	rows: 2,
	widgets: [
		{
			id: 'recovery-0',
			widgetName: 'mapWidget',
			width: 1,
			height: 2
		},
		{
			id: 'recovery-1',
			widgetName: 'simpleGraphWidget',
			width: 1,
			height: 1,
			initialProps: {
				seconds: 60,
				maxSamples: 30,
				title: 'Seed altitudes',
				minY: 0,
				maxY: 50000,
				series: [
					{
						key: 'ts/seedA/iridium/payload/altitude',
						color: 'hsl(240, 100%, 50%)', // blue
						minMax: true
					},
					{
						key: 'ts/seedB/iridium/payload/altitude',
						color: 'hsl(30, 100%, 50%)', // orange
						minMax: true
					}
				]
			}
		},
		{
			id: 'recovery-2',
			widgetName: 'currentValuesWidget',
			width: 1,
			height: 1,
			initialProps: {
				title: 'Seed payload data',
				connections: [
					{
						address: 'latest/seedA/iridium/payload/latitude',
						title: 'latest/seedA/iridium/payload/latitude',
						rps: 1
					},
					{
						address: 'latest/seedA/iridium/payload/longitude',
						title: 'latest/seedA/iridium/payload/longitude',
						rps: 1
					},
					{
						address: 'latest/seedA/iridium/payload/altitude',
						title: 'latest/seedA/iridium/payload/altitude',
						rps: 1
					},
					{
						address: 'latest/seedA/iridium/payload/payload_appendix',
						title: 'latest/seedA/iridium/payload/payload_appendix',
						rps: 1
					},
					{
						address: 'latest/seedB/iridium/payload/latitude',
						title: 'latest/seedB/iridium/payload/latitude',
						rps: 1
					},
					{
						address: 'latest/seedB/iridium/payload/longitude',
						title: 'latest/seedB/iridium/payload/longitude',
						rps: 1
					},
					{
						address: 'latest/seedB/iridium/payload/altitude',
						title: 'latest/seedB/iridium/payload/altitude',
						rps: 1
					},
					{
						address: 'latest/seedB/iridium/payload/payload_appendix',
						title: 'latest/seedB/iridium/payload/payload_appendix',
						rps: 1
					}
				]
			}
		}
	]
};
