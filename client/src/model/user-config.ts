import { UserConfig } from '@wuespace/telestion-client-types';
import {
	overviewDashboard,
	graphDashboard,
	miscTelecommandDashboard,
	notesDashboard,
	electricalDashboard,
	debugDashboard,
	currentValuesDashboard,
	systemTelecommandDashboard,
	recoveryDashboard,
	recovery2Dashboard
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
	},
	iridium: {
		dashboards: [recoveryDashboard, recovery2Dashboard, debugDashboard]
	}
};
