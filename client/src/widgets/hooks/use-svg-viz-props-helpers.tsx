import { useSpectrumColor } from '@wuespace/telestion-client-common';
import { useCallback } from 'react';

/**
 * Helpers for filling SVG widget props with correct color values.
 *
 * ### `valve(component, isAllowed, isActive)`
 *
 * Defined color properties:
 * - `[component]ValveBG`
 * - `[component]ValveBorder`
 * - `[component]ValveOnLine`
 * - `[component]ValveOffLine`
 * - `[component]ValveOut`
 *
 * ### `onOffSwitch(component, isAllowed, isActive)`
 *
 * Defined color properties:
 * - `[component]SwitchOnLine`
 * - `[component]SwitchOffLine`
 *
 * ### `activeInactive(component, isActive)`
 *
 * Defined color properties:
 * - `[component]`
 *
 * ### Other "exported" members
 * - `active` - a color for "active" lines
 * - `inactive` - a color for "inactive" lines
 */
export function useSvgVizPropsHelpers() {
	const [active, inactive, positive, orange] = useSpectrumColor([
		'gray-900',
		'gray-500',
		'green-400',
		'orange-400'
	]);

	const valve = useCallback(
		(component: string, isAllowed: boolean, isActive: boolean) => ({
			[`${component}ValveBG`]: isAllowed ? positive : orange,
			[`${component}ValveBorder`]: isAllowed && isActive ? active : inactive,
			[`${component}ValveOnLine`]: !isAllowed
				? ''
				: isActive
				? active
				: inactive,
			[`${component}ValveOffLine`]: isAllowed ? '' : inactive,
			[`${component}ValveOut`]: isAllowed && isActive ? active : inactive
		}),
		[active, inactive, orange, positive]
	);

	const onOffSwitch = useCallback(
		(component: string, isAllowed: boolean, isActive: boolean) => ({
			[`${component}SwitchOnLine`]: !isAllowed
				? ''
				: isActive
				? active
				: inactive,
			[`${component}SwitchOffLine`]: isAllowed
				? ''
				: isActive
				? active
				: inactive
		}),
		[active, inactive]
	);

	const activeInactive = (component: string, isActive: boolean) => ({
		[component]: isActive ? active : inactive
	});
	return { inactive, active, valve, onOffSwitch, activeInactive };
}
