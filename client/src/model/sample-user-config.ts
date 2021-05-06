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
						initialProps: {
							fill: 'white',
							period: 10 * 1000,
							minimum: 0,
							maximum: 10
						}
					}
				]
			}
		]
	}
};
