import { useCallback, useEffect, useState } from 'react';
import { TCState } from '../../model/tc-state';

export function useOuterTcSendButtonState() {
	const [messageState, setMessageState] = useState<
		'success' | 'error' | undefined
	>();

	useEffect(() => {
		const to = setTimeout(() => setMessageState(undefined), 5000);
		return () => clearTimeout(to);
	}, [messageState, setMessageState]);

	const onStateChange = useCallback(
		(state, setState) => {
			if (state === TCState.SENT) {
				setMessageState('success');
				setState(TCState.IDLE);
			} else if (state === TCState.ERROR) {
				setMessageState('error');
				setState(TCState.IDLE);
			} else if (state === TCState.SENDING) {
				setMessageState(undefined);
			}
		},
		[setMessageState]
	);
	return { messageState, onStateChange };
}
