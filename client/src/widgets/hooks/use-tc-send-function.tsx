import { useEventBus } from '@wuespace/telestion-client-core';
import { useCallback } from 'react';

export function useTcSendFunction(channel: string) {
	const eb = useEventBus(state => state.eventBus);

	return useCallback(cmd => eb?.publish(channel, { content: cmd }), [
		eb,
		channel
	]);
}
