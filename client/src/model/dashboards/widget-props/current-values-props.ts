import { CurrentValuesWidgetProps } from '../../../widgets/current-values-widget';

export const fakeProps: CurrentValuesWidgetProps = {
	connections: [
		{
			address: 'fake-channel1',
			title: 'Acc X (m/s²)',
			rps: 30
		},
		{
			address: 'fake-channel2',
			title: 'Acc Y (m/s²)',
			precision: 4,
			rps: 20
		},
		{
			address: 'fake-channel3',
			title: 'Acc Z (m/s²)',
			precision: 3,
			rps: 10
		},
		{
			address: 'fake-channel1',
			title: 'Acc X (m/s²)',
			rps: 30
		},
		{
			address: 'fake-channel2',
			title: 'Acc Y (m/s²)',
			precision: 1,
			rps: 20
		},
		{
			address: 'fake-channel3',
			title: 'Acc Z (m/s²)',
			precision: 6,
			rps: 10
		},
		{
			address: 'fake-channel1',
			title: 'Acc X (m/s²)',
			rps: 30
		},
		{
			address: 'fake-channel2',
			title: 'Acc Y (m/s²)',
			rps: 20
		},
		{
			address: 'fake-channel3',
			title: 'Acc Z (m/s²)',
			rps: 10
		}
	]
};
