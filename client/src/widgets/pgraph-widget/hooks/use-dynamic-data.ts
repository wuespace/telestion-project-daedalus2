import { MutableRefObject, useCallback, useRef } from 'react';
import { useChannel } from '@wuespace/telestion-client-core';
import { ChannelAddress } from '@wuespace/telestion-client-types';

import { DataSample } from '../../../model/data-sample';
import { ExtendedSVGSVGElement } from '../model';

/**
 * Registers to given event bus channel,
 * appends received data sample to the store and informs the d3 node.
 * @param node - the d3 node
 * @param address - the event bus channel address
 * @returns the current store
 */
export function useDynamicData(
	node: MutableRefObject<ExtendedSVGSVGElement | undefined>,
	address: ChannelAddress
): MutableRefObject<DataSample[]> {
	const store = useRef<DataSample[]>([]);

	// append data sample to store on new data
	const handler = useCallback((sample: DataSample) => {
		const { current } = store;
		// update store
		current.push(sample);
		// inform d3 node (when possible)
		store.current = node.current?.update(current) || current;
	}, []);
	useChannel<DataSample>(address, handler);

	return store;
}
