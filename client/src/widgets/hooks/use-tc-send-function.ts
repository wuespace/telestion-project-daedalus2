import { useCallback } from 'react';
import { useEventBus } from '@wuespace/telestion-client-core';

export function useTcSendFunction(channel: string): (cmd: string) => void {
	const eb = useEventBus(state => state.eventBus);

	return useCallback(
		cmd => eb?.publish(channel, { content: cmd }),
		[eb, channel]
	);
}
