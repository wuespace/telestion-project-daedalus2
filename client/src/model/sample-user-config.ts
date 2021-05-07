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
							address: 'fake-channel',
							fill: 'white',
							period: 60 * 1000,
							minimum: -3,
							maximum: 13
						}
					}
				]
			}
		]
	}
};
