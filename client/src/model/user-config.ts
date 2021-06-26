import { UserConfig } from '@wuespace/telestion-client-types';
import {
	graphDashboard,
	currentValuesDashboard,
	notesDashboard
} from './dashboards';

export const userConfig: UserConfig = {
	admin: {
		dashboards: [currentValuesDashboard, graphDashboard, notesDashboard]
	}
};
