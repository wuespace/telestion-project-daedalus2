import { UserConfig } from '@wuespace/telestion-client-types';
import { PGraphTest, CurrentValuesTest } from './dashboards';

export const userConfig: UserConfig = {
	admin: {
		dashboards: [CurrentValuesTest, PGraphTest]
	}
};
