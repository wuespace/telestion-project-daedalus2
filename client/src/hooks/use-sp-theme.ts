import { useEffect } from 'react';
import { useDarkColorScheme } from '@wuespace/telestion-client-common';

export function useSpTheme() {
	const isDark = useDarkColorScheme();

	useEffect(() => {
		document
			.querySelector('sp-theme')
			?.setAttribute('color', isDark ? 'dark' : 'light');
	}, [isDark]);
}
