import { Dashboard } from '@wuespace/telestion-client-types';

export const electricalDashboard: Dashboard = {
	title: 'Electrical',
	columns: 3,
	rows: 21,
	widgets: [
		{
			id: 'electrical-0',
			widgetName: 'stateWidget',
			width: 2,
			height: 10,
			initialProps: {
				seedASource: 'SEED_HEARTBEAT',
				seedBSource: 'SEED_HEARTBEAT',
				ejectorSource: 'EJECTOR_HEARTBEAT'
			}
		},
		{
			id: 'electrical-2',
			widgetName: 'currentValuesWidget',
			width: 1,
			height: 20,
			initialProps: {
				title: 'Current values',
				connections: [
					{
						address: 'latest/seedA/SEED_HEARTBEAT/d2time',
						title: 'latest/seedA/SEED_HEARTBEAT/d2time',
						rps: 1
					},
					{
						address: 'latest/seedB/SEED_HEARTBEAT/d2time',
						title: 'latest/seedB/SEED_HEARTBEAT/d2time',
						rps: 1
					},
					{
						address: 'latest/ejector/EJECTOR_HEARTBEAT/d2time',
						title: 'latest/ejector/EJECTOR_HEARTBEAT/d2time',
						rps: 1
					},
					{
						address: 'latest/ejector/EJECTOR_HEARTBEAT/seed_power_enabled',
						title: 'latest/ejector/EJECTOR_HEARTBEAT/seed_power_enabled',
						rps: 1
					},
					{
						address: 'latest/ejector/EJECTOR_HEARTBEAT/seed_a_present',
						title: 'latest/ejector/EJECTOR_HEARTBEAT/seed_a_present',
						rps: 1
					},
					{
						address: 'latest/ejector/EJECTOR_HEARTBEAT/seed_b_present',
						title: 'latest/ejector/EJECTOR_HEARTBEAT/seed_b_present',
						rps: 1
					},
					{
						address: 'latest/ejector/EJECTOR_HEARTBEAT/cam_enabled',
						title: 'latest/ejector/EJECTOR_HEARTBEAT/cam_enabled',
						rps: 1
					}
				]
			}
		},
		{
			id: 'electrical-3',
			widgetName: 'seedEcam',
			width: 1,
			height: 10,
			initialProps: {
				seed: 'seedA',
				voltThreshold: 0.5,
				ampsThreshold: 0.005
			}
		},
		{
			id: 'electrical-4',
			widgetName: 'seedEcam',
			width: 1,
			height: 10,
			initialProps: {
				seed: 'seedB',
				voltThreshold: 0.5,
				ampsThreshold: 0.005
			}
		},
		{
			id: 'electrical-5',
			widgetName: 'timelineWidget',
			width: 3,
			height: 1,
			initialProps: {}
		}
	]
};
