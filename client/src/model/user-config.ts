import { UserConfig } from '@wuespace/telestion-client-types';
import {
	overviewDashboard,
	graphDashboard,
	telecommandDashboard,
	notesDashboard,
	electricalDashboard,
	debugDashboard
} from './dashboards';

export const userConfig: UserConfig = {
	admin: {
		dashboards: [
			overviewDashboard,
			graphDashboard,
			electricalDashboard,
			telecommandDashboard,
			notesDashboard,
			debugDashboard
		]
	}
};
