import { useEffect } from 'react';
import { Widget } from '@wuespace/telestion-client-types';
import {
	TelestionClient,
	Pages,
	useEventBusManager
} from '@wuespace/telestion-client-core';
import {
	commonWidgets,
	CommonWrapper,
	DashboardPage,
	NotFoundPage,
	useColorScheme,
	useUserConfig
} from '@wuespace/telestion-client-common';

import { userConfig } from '../model/user-config';
import { projectWidgets } from '../widgets';
import appLogo from '../assets/app-logo.png';

import { useSpTheme } from '../hooks/use-sp-theme';
import { Header } from './header';
import { LoginPage } from './login-page';

const widgets: Array<Widget> = [...commonWidgets, ...projectWidgets];

export function App() {
	useEventBusManager();
	useSpTheme();

	const { set: setColorSchema, colorScheme } = useColorScheme();

	useEffect(() => {
		const scheme = window.localStorage.getItem('color-schema') || 'dark';
		// @ts-ignore
		setColorSchema(scheme);
	}, [setColorSchema]);

	useEffect(() => {
		window.localStorage.setItem('color-schema', colorScheme);
	}, [colorScheme]);

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
