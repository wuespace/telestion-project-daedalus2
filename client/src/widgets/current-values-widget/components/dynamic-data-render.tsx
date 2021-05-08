import { useEffect, useRef } from 'react';
import { useEventBus } from '@wuespace/telestion-client-core';
import { Callback } from '@wuespace/telestion-client-types';

import { DataSample } from '../../../model/data-sample';
import { Connection } from '../model';

export interface DataRenderProps {
	connection: Connection;
}

export function DynamicDataRender({ connection }: DataRenderProps) {
	const eventBus = useEventBus(state => state.eventBus);

	const divRef = useRef<HTMLDivElement>(null);
	const data = useRef<string>('0');

	useEffect(() => {
		if (eventBus) {
			let timeoutId: unknown | null = null;

			const notify = () => {
				if (!timeoutId) {
					timeoutId = setTimeout(() => {
						if (divRef.current) divRef.current.innerText = data.current;
						timeoutId = null;
					}, 1000 / connection.rps);
				}
			};

			const handler: Callback<DataSample[]> = newSamples => {
				data.current = newSamples[newSamples.length - 1].avg.toFixed(
					connection.precision || 2
				);
				notify();
			};

			eventBus.register(connection.address, handler);
			return () => eventBus.unregister('fake-channel1', handler);
		}
	}, [eventBus]);

	return <div ref={divRef} />;
}
