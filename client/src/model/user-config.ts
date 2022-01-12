import { UserConfig } from '@wuespace/telestion-client-types';
import {
	overviewDashboard,
	graphDashboard,
	miscTelecommandDashboard,
	notesDashboard,
	electricalDashboard,
	debugDashboard,
	currentValuesDashboard,
	systemTelecommandDashboard
} from './dashboards';

export const userConfig: UserConfig = {
	admin: {
		dashboards: [
			overviewDashboard,
			currentValuesDashboard,
			graphDashboard,
			electricalDashboard,
			systemTelecommandDashboard,
			miscTelecommandDashboard,
			notesDashboard,
			debugDashboard
		]
	}
};
