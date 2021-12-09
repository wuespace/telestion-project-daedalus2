import { UserConfig } from '@wuespace/telestion-client-types';
import {
	overviewDashboard,
	graphDashboard,
	telecommandDashboard,
	notesDashboard,
	electricalDashboard,
	debugDashboard,
	currentValuesDashboard
} from './dashboards';

export const userConfig: UserConfig = {
	admin: {
		dashboards: [
			overviewDashboard,
			currentValuesDashboard,
			graphDashboard,
			electricalDashboard,
			telecommandDashboard,
			notesDashboard,
			debugDashboard
		]
	}
};
