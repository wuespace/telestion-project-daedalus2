import { UserConfig } from '@wuespace/telestion-client-types';

export const userConfig: UserConfig = {
	admin: {
		dashboards: [
			{
				title: 'Overview',
				columns: 1,
				rows: 1,
				widgets: [
					{
						widgetName: 'pgraphWidget',
						width: 1,
						height: 1,
						title: 'PGraph',
						initialProps: {}
					},
					{
						widgetName: 'Widget2',
						width: 2,
						height: 2,
						title: 'Widget 2'
					},
					{
						widgetName: 'Widget3',
						width: 2,
						height: 1,
						title: 'Widget 3'
					},
					{
						widgetName: 'Widget4',
						width: 1,
						height: 1,
						title: 'Widget 4'
					},
					{
						widgetName: 'Widget5',
						width: 1,
						height: 2,
						title: 'Widget 5'
					},
					{
						widgetName: 'Widget6',
						width: 1,
						height: 3,
						title: 'Widget 6'
					}
				]
			}
		]
	}
};
