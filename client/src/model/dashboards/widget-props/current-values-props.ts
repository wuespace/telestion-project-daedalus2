import { CurrentValuesWidgetProps } from '../../../widgets/current-values-widget';

export const fakeProps: CurrentValuesWidgetProps = {
	connections: [
		{
			address: 'aggregated-imu.acc.x',
			title: 'Acc X (m/s²)',
			rps: 30
		},
		{
			address: 'aggregated-imu.acc.y',
			title: 'Acc Y (m/s²)',
			precision: 4,
			rps: 20,
			minValueWarn: 3,
			maxValueWarn: 7
		},
		{
			address: 'aggregated-imu.acc.z',
			title: 'Acc Z (m/s²)',
			outdatedTimeWarn: 5000,
			outdatedTimeError: 20000,
			precision: 3,
			rps: 10
		},
		{
			address: 'no-channel',
			title: 'Acc 0 (m/s²)',
			rps: 30,
			minValueError: 4,
			maxValueError: 6
		},
		{
			address: 'aggregated-imu.acc.y',
			title: 'Acc Y (m/s²)',
			precision: 1,
			rps: 20,
			minValueWarn: 3.5,
			minValueError: 2.5,
			maxValueWarn: 8,
			maxValueError: 9
		},
		{
			address: 'aggregated-imu.acc.z',
			title: 'Acc Z (m/s²)',
			precision: 6,
			rps: 10
		},
		{
			address: 'no-channel',
			title: 'Acc 0 (m/s²)',
			rps: 30
		},
		{
			address: 'aggregated-imu.acc.y',
			title: 'Acc Y (m/s²)',
			precision: 1,
			rps: 20
		},
		{
			address: 'aggregated-imu.acc.z',
			title: 'Acc Z (m/s²)',
			precision: 6,
			rps: 10
		}
	]
};
