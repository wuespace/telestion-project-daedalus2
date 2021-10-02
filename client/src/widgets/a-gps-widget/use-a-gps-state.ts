import { useCallback, useEffect, useState } from 'react';
import {
	useChannel,
	useEventBus,
	useRequest
} from '@wuespace/telestion-client-core';

import {
	AGpsResponse,
	AGpsState,
	notifyChannel,
	requestChannel,
	RequestState
} from '../../model/a-gps';

export interface WidgetState {
	isCurrent: boolean;
	isTransmitting: boolean;
	dataName?: string;
	target?: string;
	progress: number;
}

const initialState: WidgetState = {
	isCurrent: false,
	isTransmitting: false,
	dataName: undefined,
	target: '',
	progress: 0.0
};

const requestState: RequestState = {
	type: 'state',
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.RequestState'
};

function getNewState(appState: AGpsState): WidgetState {
	return {
		isCurrent: true,
		isTransmitting: appState.state === 'transmitting',
		dataName: appState.dataName || undefined,
		target: appState.target || '',
		progress: appState.progress < 0 ? 0.0 : appState.progress * 100 // to percent :D
	};
}

export function useAGpsState(): WidgetState {
	const isConnected = useEventBus(
		state =>
			state.connectionState === 'connected' || state.connectionState === 'error'
	);
	const send = useRequest<AGpsResponse>(requestChannel);
	const [state, setState] = useState(initialState);

	// hear on new states
	useChannel<AGpsState>(
		notifyChannel,
		useCallback(state => setState(getNewState(state)), [])
	);

	useEffect(() => {
		if (isConnected) {
			// request current state because we are connected again
			send(requestState, response => {
				setState(prevState => {
					if (response.type === 'state' && !prevState.isCurrent) {
						return getNewState(response.state);
					}
					return prevState;
				});
			});
		} else {
			// we are disconnected, remove current flag
			setState(prevState => ({ ...prevState, isCurrent: false }));
		}
	}, [isConnected, send]);

	return state;
}
