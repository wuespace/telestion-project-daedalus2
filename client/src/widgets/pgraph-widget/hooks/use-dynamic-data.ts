import { MutableRefObject, useCallback, useRef } from 'react';
import { useChannel } from '@wuespace/telestion-client-core';
import { ChannelAddress } from '@wuespace/telestion-client-types';

import { DataSample } from '../../../model/data-sample';

import { ExtendedSVGSVGElement, Options } from '../model';
import { removeOldSamples } from '../lib';

/**
 * Registers to given event bus channel,
 * appends received data sample to the store and informs the d3 node.
 * @param node - the d3 node
 * @param address - the event bus channel address
 * @param options - options configured for this widget
 * @returns the current store
 */
export function useDynamicData(
	node: MutableRefObject<ExtendedSVGSVGElement | undefined>,
	address: ChannelAddress,
	options: Options
): MutableRefObject<DataSample[]> {
	const store = useRef<DataSample[]>([]);

	// append data sample to store on new data
	const handler = useCallback(
		(sample: DataSample) => {
			// remove outdated samples from store
			const current = removeOldSamples(store.current, options.period);
			// add new sample to the store
			current.push(sample);
			// inform d3 node (when possible)
			store.current = node.current?.update(current) || current;
		},
		[options]
	);
	useChannel<DataSample>(address, handler);

	return store;
}
