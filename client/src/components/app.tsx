import { useEffect } from 'react';
import { Widget } from '@wuespace/telestion-client-types';
import {
	TelestionClient,
	Pages,
	useEventBusManager
} from '@wuespace/telestion-client-core';
import {
	CommonWrapper,
	DashboardPage,
	NotFoundPage,
	useUserConfig
} from '@wuespace/telestion-client-common';

import { userConfig } from '../model/user-config';
import { projectWidgets } from '../widgets';
import appLogo from '../assets/app-logo.png';

import { Header } from './header';
import { LoginPage } from './login-page';

const widgets: Array<Widget> = [...projectWidgets];

export function App() {
	useEventBusManager();

	const set = useUserConfig(state => state.set);

	useEffect(() => {
		// apply user config once
		set(userConfig);
	}, [set]);

	return (
		<TelestionClient
			title="Daedalus 2 Groundstation"
			wrapper={children => (
				<CommonWrapper widgets={widgets} appLogo={appLogo}>
					<>{children}</>
				</CommonWrapper>
			)}
		>
			<Pages preNodes={<Header />}>
				<LoginPage />
				<DashboardPage />
				<NotFoundPage />
			</Pages>
		</TelestionClient>
	);
}
