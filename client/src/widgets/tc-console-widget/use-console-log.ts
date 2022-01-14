import { RefObject, useEffect, useRef } from 'react';
import { StateSelector } from 'zustand';
import { JsonSerializable } from '@wuespace/telestion-client-types';
import {
	EventBusState,
	useEventBus,
	useLogger
} from '@wuespace/telestion-client-core';

import {
	isLogMessages,
	isResponseState,
	LogMessage,
	notifyChannel,
	requestChannel,
	requestStateMessage
} from '../../model/tc-console';
import { formatMessage, genBootSeparator, splitBoots } from './lib';

const selector: StateSelector<
	EventBusState,
	EventBusState['eventBus']
> = state => state.eventBus;

export function useConsoleLog(
	source: string,
	elementRef: RefObject<HTMLPreElement>,
	isScroll = true,
	showAllBoots = false
) {
	const eventBus = useEventBus(selector);
	const logger = useLogger('useConsoleLog');

	const isScrollRef = useRef(isScroll);
	const showAllBootsRef = useRef(showAllBoots);
	const updateFuncRef = useRef(() => {});

	// keep refs synced with given React state
	useEffect(() => {
		isScrollRef.current = isScroll;
	}, [isScroll]);

	useEffect(() => {
		showAllBootsRef.current = showAllBoots;
		updateFuncRef.current();
	}, [showAllBoots]);

	// setup notify handler
	useEffect(() => {
		if (eventBus) {
			let messages: LogMessage[] = [];

			updateFuncRef.current = () => {
				if (elementRef.current) {
					let boots = splitBoots(messages);

					if (!showAllBootsRef.current) {
						boots = [boots[boots.length - 1]]; // only show the latest boot
					}

					const formattedMessages = boots.reduce((acc, current, index, arr) => {
						acc.push(genBootSeparator(index, arr.length));
						acc.push(...current.map(formatMessage));
						return acc;
					}, [] as string[]);

					elementRef.current.innerHTML = formattedMessages.join('\n');
					if (isScrollRef.current) {
						elementRef.current.scrollIntoView(false);
					}
				}
			};

			const notifyHandler = (message: JsonSerializable) => {
				try {
					if (isLogMessages(message)) {
						if (message.source === source) {
							messages = message.messages;
							updateFuncRef.current();
							logger.info('State update through notify.');
						}
					} else {
						logger.warn('Received invalid message:', message);
					}
				} catch (err) {
					logger.error(
						'Cannot successfully process notified state update:',
						err
					);
				}
			};

			const requestHandler = (message: JsonSerializable) => {
				try {
					if (isResponseState(message)) {
						if (message.messages.source === source) {
							messages = message.messages.messages;
							updateFuncRef.current();
							logger.info('State update through request.');
						}
					} else {
						logger.warn('Received invalid response message:', message);
					}
				} catch (err) {
					logger.error(
						'Cannot successfully process received state request:',
						err
					);
				}
			};

			eventBus.register(notifyChannel, notifyHandler);
			eventBus.send(
				requestChannel,
				requestStateMessage(source),
				requestHandler
			);
			return () => eventBus.unregister(notifyChannel, notifyHandler);
		}
	}, [eventBus, logger, elementRef, source]);
}
