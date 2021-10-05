import { useCallback, useEffect, useState } from 'react';
import { StateSelector } from 'zustand';
import {
	EventBusState,
	useChannel,
	useEventBus,
	useRequest
} from '@wuespace/telestion-client-core';
import {
	ChecklistResponse,
	ChecklistState,
	RequestState
} from '../../model/checklist-state';

const selector: StateSelector<EventBusState, boolean> = state =>
	state.connectionState === 'connected' || state.connectionState === 'error';

export interface ListPoint {
	key: number;
	title: string;
	isChecked: boolean;
}

export interface WidgetState {
	isCurrent: boolean;
	points?: ListPoint[];
}

const initialState: WidgetState = {
	isCurrent: false,
	points: undefined
};

const requestState: RequestState = {
	type: 'state',
	className:
		'de.wuespace.telestion.project.daedalus2.checklist.message.RequestState'
};

function getNewState(appState: ChecklistState): WidgetState {
	return {
		isCurrent: true,
		points: appState.points.map((point, index) => ({
			key: index,
			isChecked: appState.states[index] || false,
			title: point.name
		}))
	};
}

export function useChecklistState(
	requestChannel: string,
	notifyChannel: string
): WidgetState {
	const isConnected = useEventBus(selector);
	const send = useRequest<ChecklistResponse>(requestChannel);
	const [state, setState] = useState<WidgetState>(initialState);

	// hear on new states
	useChannel<ChecklistState>(
		notifyChannel,
		useCallback(state => setState(getNewState(state)), [])
	);

	// request new state on reconnect
	useEffect(() => {
		if (isConnected) {
			// request
			send(requestState, response => {
				setState(prevState => {
					if (response.type === 'state' && !prevState.isCurrent) {
						return getNewState(response.state);
					}
					return prevState;
				});
			});
		} else {
			// set isCurrent flag to false
			setState(prevState => ({ ...prevState, isCurrent: false }));
		}
	}, [isConnected, send]);

	return state;
}
