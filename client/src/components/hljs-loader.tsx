import { useDarkColorScheme } from '@wuespace/telestion-client-common';

/**
 * Dynamically loads the light or dark styles for highlight.js.
 */
export function HljsLoader() {
	const isDark = useDarkColorScheme();

	return (
		<link
			rel="stylesheet"
			type="text/css"
			href={isDark ? 'dark.css' : 'light.css'}
		/>
	);
}
