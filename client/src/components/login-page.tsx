import {
	LoginPage as TCLoginPage,
	LoginTitle,
	LoginLogo,
	LoginDescription,
	LoginForm
} from '@wuespace/telestion-client-common';
import { useConfig } from '../hooks/use-config';

export function LoginPage() {
	const config = useConfig();

	return (
		<TCLoginPage>
			<LoginLogo />
			<LoginTitle />
			<LoginDescription />
			<LoginForm
				initialServerURL={config.initialServerURL}
				initialUsername={config.initialUsername}
			/>
		</TCLoginPage>
	);
}

LoginPage.routing = TCLoginPage.routing;
