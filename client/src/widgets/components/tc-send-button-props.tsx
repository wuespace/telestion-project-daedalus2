import { ReactNode } from 'react';
import { TCState } from '../../model/tc-state';

export interface TcSendButtonProps {
	/**
	 * The button style
	 *
	 * Default: `'action'`
	 */
	variant?: 'cta' | 'primary' | 'secondary' | 'action';
	/**
	 * Whether the button should use the "quiet" variant"
	 *
	 * Default: `'false'`
	 */
	isQuiet?: boolean;
	/**
	 * The TC
	 */
	command: string;
	/**
	 * The TC's target
	 */
	target: 'seedA' | 'seedB' | 'ejector';
	/**
	 * The button text
	 */
	children: ReactNode;
	/**
	 * A callback that gets called whenever the state changes and allows you to manually set the state.
	 *
	 * You may want to override the state from `TC.SENT` and `TC.ERROR` back to idle to "re-enable" the button.
	 * Otherwise, the button will only get re-enabled if `command` or `target` changes.
	 *
	 * @param state - the new state
	 * @param setState - a function to set a new state
	 */
	onStateChange?: (state: TCState, setState: (state: TCState) => void) => void;
	/**
	 * Whether the button is disabled
	 *
	 * Please note that the button is disabled outside the idle state or if no `command` or `target` have been specified,
	 * anyways.
	 */
	isDisabled?: boolean;
}
