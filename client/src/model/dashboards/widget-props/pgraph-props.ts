import { PGraphWidgetProps } from '../../../widgets/pgraph-widget';

export const fakeProps: PGraphWidgetProps = {
	connections: [
		{
			address: 'fake-channel1',
			title: 'Fake Data 1',
			stroke: 'orange-700',
			fill: 'orange-400'
		},
		{
			address: 'fake-channel2',
			title: 'Fake Data 2',
			stroke: 'celery-700',
			fill: 'celery-400'
		},
		{
			address: 'fake-channel3',
			title: 'Fake Data 3',
			stroke: 'seafoam-700',
			fill: 'seafoam-400'
		}
	],
	period: 2 * 60 * 1000,
	rps: 10
};
