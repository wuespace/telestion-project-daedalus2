import { PGraphWidgetProps } from '../../../widgets/pgraph-widget';

export const fakeProps: PGraphWidgetProps = {
	title: 'Fake Data',
	channels: {},
	connections: [
		{
			id: 0,
			address: 'aggregated-imu.acc.x',
			title: 'Accelerometer X',
			stroke: 'orange-700',
			fill: 'orange-400'
		},
		{
			id: 1,
			address: 'aggregated-imu.acc.y',
			title: 'Accelerometer Y',
			stroke: 'seafoam-700',
			fill: 'seafoam-400'
		},
		{
			id: 2,
			address: 'aggregated-imu.acc.z',
			title: 'Accelerometer Z',
			stroke: 'blue-700',
			fill: 'blue-400'
		}
	],
	period: 2 * 60 * 1000,
	rps: 10
};
