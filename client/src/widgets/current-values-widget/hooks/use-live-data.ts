import { RefObject, useEffect, useRef } from 'react';
import { StateSelector } from 'zustand';
import { DOMRefValue } from '@react-types/shared';
import { Callback } from '@wuespace/telestion-client-types';
import { EventBusState, useEventBus } from '@wuespace/telestion-client-core';

import { DataSample } from '../../../model/data-sample';
import { useSpectrumColor } from '../../hooks';

import { Connection } from '../model';
import { newStatusTimeout, updateStatusNode, StatusType } from '../lib';

const selector: StateSelector<
	EventBusState,
	EventBusState['eventBus']
> = state => state.eventBus;

export interface ReturnType {
	valueNode: RefObject<HTMLDivElement>;
	statusNode: RefObject<DOMRefValue<HTMLDivElement>>;
}

export function useLiveData(connection: Connection): ReturnType {
	const [notice, negative] = useSpectrumColor(['orange-600', 'red-500']);
	const eventBus = useEventBus(selector);

	const valueNode = useRef<HTMLDivElement>(null);
	const statusNode = useRef<DOMRefValue<HTMLDivElement>>(null);

	useEffect(() => {
		if (eventBus) {
			const {
				address,
				precision,
				rps,
				outdatedTimeWarn,
				outdatedTimeError,
				minValueWarn,
				minValueError,
				maxValueWarn,
				maxValueError
			} = connection;

			let value = 0;
			let lastUpdate = 0;

			let notifyTimeout: unknown | null = null;
			let statusTimeout: unknown | null = null;
			let statusType: StatusType = 'neutral';

			/**
			 * Updates the status light in a Non-React way.
			 * @param deltaTime - the delay since the last received data
			 */
			const updateStatus = (deltaTime: number) => {
				if (statusNode.current) {
					const newType: StatusType =
						outdatedTimeError && outdatedTimeError < deltaTime
							? 'negative'
							: outdatedTimeWarn && outdatedTimeWarn < deltaTime
							? 'notice'
							: 'positive';

					statusType = updateStatusNode(
						statusNode.current.UNSAFE_getDOMNode(),
						deltaTime,
						statusType,
						newType
					);
				}
			};

			/**
			 * Updates the value node in a Non-React way.
			 */
			const updateValue = () => {
				if (valueNode.current) {
					const node = valueNode.current;
					node.innerText = value.toFixed(precision || 2);

					node.style.color =
						(minValueError && value < minValueError) ||
						(maxValueError && value > maxValueError)
							? negative
							: (minValueWarn && value < minValueWarn) ||
							  (maxValueWarn && value > maxValueWarn)
							? notice
							: '';
				}
			};

			/**
			 * Sets the status timeout to update the status light in regular intervals.
			 * @param reset - should reset the current update interval and pull new data?
			 */
			const setStatusTimeout = (reset = false) => {
				if (reset && statusTimeout) clearTimeout(statusTimeout as any);
				const deltaTime = Date.now() - lastUpdate;
				updateStatus(deltaTime);
				statusTimeout = setTimeout(
					() => setStatusTimeout(),
					newStatusTimeout(deltaTime)
				);
			};

			/**
			 * Notifies the display that new data was received and is ready for view.
			 */
			const notify = () => {
				if (!notifyTimeout) {
					updateValue();
					setStatusTimeout(true);
					notifyTimeout = setTimeout(() => {
						notifyTimeout = null;
					}, 1000 / rps);
				}
			};

			const handler: Callback<DataSample[]> = newSamples => {
				value = newSamples[newSamples.length - 1].avg;
				lastUpdate = Date.now();
				notify();
			};

			eventBus.register(address, handler);
			return () => {
				eventBus.unregister(address, handler);
				if (notifyTimeout) clearTimeout(notifyTimeout as any);
				if (statusTimeout) clearTimeout(statusTimeout as any);
			};
		}
	}, [eventBus, connection, notice, negative]);

	return { valueNode, statusNode };
}
