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
import { MissionTimeAction } from './mission-time-action';
import { ConnectionStatusPanel } from './connection-status-panel';

export function Header() {
	return (
		<TCHeader
			left={
				<>
					<AppLogo />
					<NavBar />
					<ConnectionStatusPanel />
				</>
			}
			center={<DashboardPicker />}
			right={
				<>
					<ConnectionIndicator />
					<Actions>
						<MissionTimeAction />
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
