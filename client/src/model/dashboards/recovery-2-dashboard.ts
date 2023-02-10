import { Dashboard } from '@wuespace/telestion-client-types';

export const recovery2Dashboard: Dashboard = {
	title: 'Recovery 2',
	columns: 2,
	rows: 21,
	widgets: [
		{
			id: 'recovery-2-0',
			widgetName: 'iridiumMapWidget',
			width: 1,
			height: 20
		},
		{
			id: 'recovery-2-1',
			widgetName: 'iridiumStateWidget',
			width: 1,
			height: 10,
			initialProps: {}
		},
		{
			id: 'recovery-2-2',
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
			id: 'recovery-2-3',
			widgetName: 'iridiumTimelineWidget',
			width: 2,
			height: 1,
			initialProps: {}
		}
	]
};
