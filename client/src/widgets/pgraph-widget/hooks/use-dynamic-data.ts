import { MutableRefObject, useEffect, useMemo, useRef } from 'react';
import { StateSelector } from 'zustand';
import * as d3 from 'd3';
import { EventBusState, useEventBus } from '@wuespace/telestion-client-core';
import { Callback } from '@wuespace/telestion-client-types';

import { DataSample } from '../../../model/data-sample';
import { ExtendedSVGSVGElement, GraphDimensions, Options } from '../model';
import { getMaxFromStore, getMinFromStore, removeOldSamples } from '../lib';

const initialDimensions: GraphDimensions = {
	minX: 0,
	maxX: 1,
	minY: 0,
	maxY: 1
};

const selector: StateSelector<
	EventBusState,
	EventBusState['eventBus']
> = store => store.eventBus;

/**
 * Registers to given event bus channel,
 * appends received data sample to the store and informs the d3 node.
 * @param node - the d3 node
 * @param options - options configured for this widget
 * @returns the current store
 */
export function useDynamicData(
	node: MutableRefObject<ExtendedSVGSVGElement | undefined>,
	options: Options
): MutableRefObject<DataSample[][]> {
	const initialStore = useMemo(() => options.connections.map(() => []), [
		options
	]);
	const store = useRef<DataSample[][]>(initialStore);
	const dimensions = useRef<GraphDimensions>(initialDimensions);

	const eventBus = useEventBus(selector);

	useEffect(() => {
		const { connections, period, maximum, minimum, rps } = options;

		let timeoutId: unknown | undefined;

		const notify = () => {
			if (!timeoutId) {
				timeoutId = setTimeout(() => {
					node.current?.update(store.current, dimensions.current);
					timeoutId = undefined;
				}, 1000 / rps);
			}
		};

		node.current?.update(store.current, dimensions.current);

		if (eventBus) {
			const handlers: Array<Callback<DataSample[]>> = connections.map(
				(_, index) => newSamples => {
					let { current: storeRef } = store;
					let { current: dimensionsRef } = dimensions;

					// add new samples for current graph
					storeRef[index].push(...newSamples);
					// calculate minimum and maximum timestamp
					dimensionsRef.maxX = Math.max(
						d3.max(newSamples.map(sample => sample.timeStamp)) as number,
						dimensions.current.maxX
					);
					dimensionsRef.minX = dimensionsRef.maxX - period;

					// remove old samples from all connections
					storeRef = removeOldSamples(storeRef, dimensionsRef.minX);

					// calculate min and max
					dimensionsRef.minY = minimum
						? minimum
						: getMinFromStore(storeRef, initialDimensions.minY);
					dimensionsRef.maxY = maximum
						? maximum
						: getMaxFromStore(storeRef, initialDimensions.maxY);

					// inform d3 graph
					notify();

					store.current = storeRef;
					dimensions.current = dimensionsRef;
				}
			);

			handlers.forEach((handler, index) =>
				eventBus.register(connections[index].address, handler)
			);

			return () =>
				handlers.forEach((handler, index) =>
					eventBus.unregister(connections[index].address, handler)
				);
		}
	}, [eventBus, node, options]);

	return store;
}
