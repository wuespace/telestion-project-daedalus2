import { useLogger, useRequest } from '@wuespace/telestion-client-core';
import { useCallback, useMemo } from 'react';
import {
	ChecklistResponse,
	RequestNewState
} from '../../model/checklist-state';

export interface WidgetReducers {
	newState(index: number, newState: boolean): void;
}

export function useChecklistReducers(title: string, requestChannel: string) {
	const logger = useLogger(useMemo(() => `Checklist Logger ${title}`, [title]));
	const send = useRequest<ChecklistResponse>(requestChannel);

	const newState = useCallback(
		(index: number, newState: boolean) => {
			logger.info(`New state for point ${index}: ${newState}`);

			const request: RequestNewState = {
				type: 'new-state',
				index,
				newState,
				className:
					'de.wuespace.telestion.project.daedalus2.checklist.message.RequestNewState'
			};

			send(request, response => {
				if (response.type === 'new-state' && response.result === 1) {
					alert('Given index is out of bounds.');
				}
			});
		},
		[logger, send]
	);

	return useMemo(() => ({ newState }), [newState]);
}
