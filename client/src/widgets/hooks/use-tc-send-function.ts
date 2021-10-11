import { useCallback } from 'react';
import { useLogger, useRequest } from '@wuespace/telestion-client-core';
import { telecommandClassName } from '../../model/channels';

/**
 * Send a message to a Daedalus target. (e.g. seedA, seedB, ejector)
 */
interface SendFunction {
	/**
	 * Send a message to a Daedalus target. (e.g. seedA, seedB, ejector)
	 * @param target - the Daedalus target
	 * @param msg - the message to send to the target
	 * @param callback - a callback that gets called with the server's response. `response` parameter should be `0`
	 * for success
	 */
	(target: string, msg: string, callback?: (response: number) => void): void;
}

export function useTcSendFunction(channel: string): SendFunction {
	const logger = useLogger('TC Sender');
	const request = useRequest<number>(channel);

	return useCallback(
		(target, msg, callback) => {
			logger.info(`Sending TC "${msg}" to "${target}"`);

			request({ target, msg, className: telecommandClassName }, resp => {
				logger.success(`TC ${msg} sent to ${target} successfully.`);
				if (callback) callback(resp);
			});
		},
		[logger, request]
	);
}
