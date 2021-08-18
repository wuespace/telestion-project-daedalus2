import {
	Header as TCHeader,
	AppLogo,
	NavBar,
	DashboardPicker,
	ConnectionIndicator,
	Actions,
	NotificationAction,
	ColorSchemeAction,
	FullscreenAction,
	AccountControls
} from '@wuespace/telestion-client-common';
import { PerformanceAction } from './performance-action';

export function Header() {
	return (
		<TCHeader
			left={
				<>
					<AppLogo />
					<NavBar />
				</>
			}
			center={<DashboardPicker />}
			right={
				<>
					<ConnectionIndicator />
					<Actions>
						<PerformanceAction />
						<NotificationAction />
						<ColorSchemeAction />
						<FullscreenAction />
					</Actions>
					<AccountControls />
				</>
			}
		/>
	);
}
