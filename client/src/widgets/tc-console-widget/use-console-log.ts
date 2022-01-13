import { RefObject, useEffect } from 'react';
import {
	EventBusState,
	useEventBus,
	useLogger
} from '@wuespace/telestion-client-core';
import { StateSelector } from 'zustand';
import {
	isLogMessage,
	isResponseState,
	notifyChannel,
	requestChannel,
	requestStateMessage
} from '../../model/tc-console';
import { JsonSerializable } from '@wuespace/telestion-client-types';

const selector: StateSelector<
	EventBusState,
	EventBusState['eventBus']
> = state => state.eventBus;

export function useConsoleLog(source: string, ref: RefObject<HTMLPreElement>) {
	const eventBus = useEventBus(selector);
	const logger = useLogger('useConsoleLog');

	useEffect(() => {
		if (eventBus) {
			// update on notify
			const handler = (message: JsonSerializable) => {
				if (isLogMessage(message)) {
					if (message.source === source) {
						if (ref.current) {
							ref.current.innerHTML = message.messages.join('\n');
							ref.current.scrollIntoView({
								behavior: 'smooth',
								block: 'end',
								inline: 'nearest'
							});
						} else {
							logger.warn('Pre-Ref not defined.');
						}

						logger.info('State update through notify.');
					}
				} else {
					logger.warn('Received invalid message:', message);
				}
			};

			eventBus.register(notifyChannel, handler);
			return () => eventBus.unregister(notifyChannel, handler);
		}
	}, [eventBus, logger, ref, source]);

	// actively request state on first render
	useEffect(() => {
		if (eventBus) {
			const handler = (message: JsonSerializable) => {
				if (isResponseState(message)) {
					if (message.state.source === source) {
						if (ref.current) {
							ref.current.innerHTML = message.state.messages.join('\n');
							ref.current.scrollIntoView({
								behavior: 'smooth',
								block: 'end',
								inline: 'nearest'
							});
						}
						logger.info('State update through request');
					}
				}
			};

			eventBus.send(requestChannel, requestStateMessage(source), handler);
		}
	}, [eventBus, logger, ref, source]);
}
