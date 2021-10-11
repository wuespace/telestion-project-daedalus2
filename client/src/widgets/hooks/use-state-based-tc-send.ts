import { useCallback, useEffect, useState } from 'react';
import { TCState } from '../../model/tc-state';
import { useTcSendFunction } from './index';

export function useStateBasedTCSend(
	channel: string,
	target: string,
	cmd: string
) {
	const [state, setState] = useState(TCState.IDLE);

	const sendTC = useTcSendFunction(channel);

	// Reset state to idle on input change
	useEffect(() => {
		setState(TCState.IDLE);
	}, [target, cmd]);

	// Warn if no confirmation was received after 5 seconds
	useEffect(() => {
		if (state === TCState.SENDING) {
			const warningTrigger = setTimeout(() => setState(TCState.ERROR), 5000);

			return () => clearTimeout(warningTrigger);
		}
	}, [state]);

	const onSend = useCallback(() => {
		setState(TCState.SENDING);
		sendTC(target, cmd, () => setState(TCState.SENT));
	}, [sendTC, cmd, target]);
	return { state, setState, onSend };
}
