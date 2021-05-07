import { SpectrumColor, StaticSpectrumColor } from '../../model/spectrum-color';
import { useDarkColorScheme } from './use-dark-color-scheme';

export const staticColors: { [key in StaticSpectrumColor]: string } = {
	black: '#000',
	white: '#fff',

	'static-gray-50': '#fff',
	'static-gray-75': '#fff',
	'static-gray-100': '#fff',
	'static-gray-200': '#f4f4f4',
	'static-gray-300': '#eaeaea',
	'static-gray-400': '#d3d3d3',
	'static-gray-500': '#bcbcbc',
	'static-gray-600': '#959595',
	'static-gray-700': '#747474',
	'static-gray-800': '#505050',
	'static-gray-900': '#323232',

	'static-blue-200': '#5aa9fa',
	'static-blue-300': '#4b9cf5',
	'static-blue-400': '#378ef0',
	'static-blue-500': '#2680eb',
	'static-blue-600': '#1473e6',
	'static-blue-700': '#0d66d0',
	'static-blue-800': '#095aba',

	'static-red-400': '#ec5b62',
	'static-red-500': '#e34850',
	'static-red-600': '#d7373f',
	'static-red-700': '#c9252d',

	'static-orange-400': '#f29423',
	'static-orange-500': '#e68619',
	'static-orange-600': '#da7b11',
	'static-orange-700': '#cb6f10',

	'static-green-400': '#33ab84',
	'static-green-500': '#2d9d78',
	'static-green-600': '#268e6c',
	'static-green-700': '#12805c',

	'static-celery-200': '#58e06f',
	'static-celery-300': '#51d267',
	'static-celery-400': '#4bc35f',
	'static-celery-500': '#44b556',
	'static-celery-600': '#3da74e',
	'static-celery-700': '#379947',

	'static-chartreuse-300': '#9bec54',
	'static-chartreuse-400': '#8ede49',
	'static-chartreuse-500': '#85d044',
	'static-chartreuse-600': '#7cc33f',
	'static-chartreuse-700': '#73b53a',

	'static-yellow-200': '#ffe22e',
	'static-yellow-300': '#fad900',
	'static-yellow-400': '#edcc00',
	'static-yellow-500': '#dfbf00',
	'static-yellow-600': '#d2b200',
	'static-yellow-700': '#c4a600',

	'static-magenta-200': '#f56bb7',
	'static-magenta-300': '#ec5aaa',
	'static-magenta-400': '#e2499d',
	'static-magenta-500': '#d83790',
	'static-magenta-600': '#ca2982',
	'static-magenta-700': '#bc1c74',

	'static-fuchsia-400': '#cf3edc',
	'static-fuchsia-500': '#c038cc',
	'static-fuchsia-600': '#b130bd',
	'static-fuchsia-700': '#a228ad',

	'static-purple-400': '#9d64e1',
	'static-purple-500': '#9256d9',
	'static-purple-600': '#864ccc',
	'static-purple-700': '#7a42bf',
	'static-purple-800': '#6f38b1',

	'static-indigo-200': '#9090fa',
	'static-indigo-300': '#8282f6',
	'static-indigo-400': '#7575f1',
	'static-indigo-500': '#6767ec',
	'static-indigo-600': '#5c5ce0',
	'static-indigo-700': '#5151d3',

	'static-seafoam-200': '#26c0c7',
	'static-seafoam-300': '#23b2b8',
	'static-seafoam-400': '#20a3a8',
	'static-seafoam-500': '#1b959a',
	'static-seafoam-600': '#16878c',
	'static-seafoam-700': '#0f797d'
};

export const lightColors: { [key in SpectrumColor]: string } = {
	...staticColors,

	'celery-400': '#4bc35f',
	'celery-500': '#44b556',
	'celery-600': '#3da74e',
	'celery-700': '#379947',

	'chartreuse-400': '#8ede49',
	'chartreuse-500': '#85d044',
	'chartreuse-600': '#7cc33f',
	'chartreuse-700': '#73b53a',

	'yellow-400': '#edcc00',
	'yellow-500': '#dfbf00',
	'yellow-600': '#d2b200',
	'yellow-700': '#c4a600',

	'magenta-400': '#e2499d',
	'magenta-500': '#d83790',
	'magenta-600': '#ca2982',
	'magenta-700': '#bc1c74',

	'fuchsia-400': '#cf3edc',
	'fuchsia-500': '#c038cc',
	'fuchsia-600': '#b130bd',
	'fuchsia-700': '#a228ad',

	'purple-400': '#9d64e1',
	'purple-500': '#9256d9',
	'purple-600': '#864ccc',
	'purple-700': '#7a42bf',

	'indigo-400': '#7575f1',
	'indigo-500': '#6767ec',
	'indigo-600': '#5c5ce0',
	'indigo-700': '#5151d3',

	'seafoam-400': '#20a3a8',
	'seafoam-500': '#1b959a',
	'seafoam-600': '#16878c',
	'seafoam-700': '#0f797d',

	'red-400': '#ec5b62',
	'red-500': '#e34850',
	'red-600': '#d7373f',
	'red-700': '#c9252d',

	'orange-400': '#f29423',
	'orange-500': '#e68619',
	'orange-600': '#da7b11',
	'orange-700': '#cb6f10',

	'green-400': '#33ab84',
	'green-500': '#2d9d78',
	'green-600': '#268e6c',
	'green-700': '#12805c',

	'blue-400': '#378ef0',
	'blue-500': '#2680eb',
	'blue-600': '#1473e6',
	'blue-700': '#0d66d0',

	'gray-50': '#fff',
	'gray-75': '#fff',
	'gray-100': '#fff',
	'gray-200': '#f4f4f4',
	'gray-300': '#eaeaea',
	'gray-400': '#d3d3d3',
	'gray-500': '#bcbcbc',
	'gray-600': '#959595',
	'gray-700': '#747474',
	'gray-800': '#505050',
	'gray-900': '#323232'
};

export const darkColors: { [key in SpectrumColor]: string } = {
	...staticColors,

	'celery-400': '#3da74e',
	'celery-500': '#44b556',
	'celery-600': '#4bc35f',
	'celery-700': '#51d267',

	'chartreuse-400': '#7cc33f',
	'chartreuse-500': '#85d044',
	'chartreuse-600': '#8ede49',
	'chartreuse-700': '#9bec54',

	'yellow-400': '#d2b200',
	'yellow-500': '#dfbf00',
	'yellow-600': '#edcc00',
	'yellow-700': '#fad900',

	'magenta-400': '#ca2996',
	'magenta-500': '#d83790',
	'magenta-600': '#e2499d',
	'magenta-700': '#ec5aaa',

	'fuchsia-400': '#b130bd',
	'fuchsia-500': '#c038cc',
	'fuchsia-600': '#cf3edc',
	'fuchsia-700': '#d951e5',

	'purple-400': '#864ccc',
	'purple-500': '#9256d9',
	'purple-600': '#9d64e1',
	'purple-700': '#a873df',

	'indigo-400': '#5c5ce0',
	'indigo-500': '#6767ec',
	'indigo-600': '#7575f1',
	'indigo-700': '#8282f6',

	'seafoam-400': '#16878c',
	'seafoam-500': '#1b959a',
	'seafoam-600': '#20a3a8',
	'seafoam-700': '#23b2b8',

	'red-400': '#d7373f',
	'red-500': '#e34850',
	'red-600': '#ec5b62',
	'red-700': '#f76d74',

	'orange-400': '#da7b11',
	'orange-500': '#e68619',
	'orange-600': '#f29423',
	'orange-700': '#f9a43f',

	'green-400': '#268e6c',
	'green-500': '#2d9d78',
	'green-600': '#33ab84',
	'green-700': '#39b990',

	'blue-400': '#1473e6',
	'blue-500': '#2680eb',
	'blue-600': '#378ef0',
	'blue-700': '#4b9cf5',

	'gray-50': '#080808',
	'gray-75': '#1a1a1a',
	'gray-100': '#1e1e1e',
	'gray-200': '#2c2c2c',
	'gray-300': '#393939',
	'gray-400': '#494949',
	'gray-500': '#5c5c5c',
	'gray-600': '#7c7c7c',
	'gray-700': '#a2a2a2',
	'gray-800': '#c8c8c8',
	'gray-900': '#efefef'
};

/**
 * Converts the given spectrum colors to hexadecimal colors.
 * It respects the application color scheme and changes to the suitable color.
 *
 * @param spectrumColors - the spectrum colors to convert to hexadecimal values
 * @returns the hexadecimal values
 *
 * @example
 * ```ts
 * const [color1, color2] = useSpectrumColor(['indigo-400', 'yellow-700']);
 *
 * console.log(color1); // #7575f1
 * console.log(color2); // c4a600
 * ```
 */
export function useSpectrumColor(spectrumColors: SpectrumColor[]): string[] {
	const isDark = useDarkColorScheme();

	return spectrumColors.map(spectrumColor =>
		isDark ? darkColors[spectrumColor] : lightColors[spectrumColor]
	);
}
