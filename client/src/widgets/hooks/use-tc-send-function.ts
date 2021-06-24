import { useCallback } from 'react';
import { useEventBus, useLogger } from '@wuespace/telestion-client-core';
import { telecommandClassName } from '../../model/channels';

/**
 * Send a message to a Daedalus target. (e.g. seedA, seedB, ejector)
 */
interface SendFunction {
	/**
	 * Send a message to a Daedalus target. (e.g. seedA, seedB, ejector)
	 * @param target - the Daedalus target
	 * @param msg - the message to send to the target
	 */
	(target: string, msg: string): void;
}

export function useTcSendFunction(channel: string): SendFunction {
	const logger = useLogger('TC Sender');
	const eb = useEventBus(state => state.eventBus);

	return useCallback(
		(target, msg) => {
			logger.info(`Sending TC "${msg}" to "${target}"`);
			eb?.publish(channel, { target, msg, className: telecommandClassName });
		},
		[logger, eb, channel]
	);
}
