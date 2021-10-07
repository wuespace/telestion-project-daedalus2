import create from 'zustand';
import { JsonSerializable } from '@wuespace/telestion-client-types';
import { useEffect, useMemo } from 'react';
import { useEventBus } from '@wuespace/telestion-client-core';
import { ConnectionState } from '@wuespace/vertx-event-bus';
import { RedisLatestRequest } from './model';
import { Undefinable } from '@wuespace/telestion-client-common';

let timerId: NodeJS.Timeout | undefined = undefined;
let nextId = 1;

const requestLatestAddress = 'request-latest';
export const useLatestCache = create<{
	data: Record<string, JsonSerializable>;
	subscriptions: Record<number, string[]>;
	subscribe: (keys: string[]) => number;
	unsubscribe: (id: number) => void;
	fetch: () => void;
}>((set, get) => ({
	data: {},
	subscriptions: {},
	fetching: false,
	subscribe: (keys: string[]) => {
		const { fetch } = get();

		const currentId = nextId;
		nextId++;

		if (!timerId) {
			fetch();
		}

		set(state => ({
			subscriptions: {
				...state.subscriptions,
				[currentId]: [...keys]
			}
		}));

		return currentId;
	},
	unsubscribe: id => {
		set(state => ({ subscriptions: { ...state.subscriptions, [id]: [] } }));
	},
	fetch: () => {
		timerId = setTimeout(() => {
			const { eventBus } = useEventBus.getState();

			const subscribedKeys = Array.from(
				new Set(Object.values(get().subscriptions).flat())
			);

			if (
				eventBus?.state === ConnectionState.OPEN &&
				subscribedKeys.length > 0
			) {
				const requestObject: RedisLatestRequest = {
					fields: subscribedKeys,
					className:
						'de.wuespace.telestion.project.daedalus2.redis.RedisLatestRequest'
				};

				eventBus.send<JsonSerializable[]>(
					requestLatestAddress,
					requestObject,
					result => {
						const newData = Object.freeze(
							Object.fromEntries(
								subscribedKeys.map((key, i) => [
									key,
									Array.isArray(result) ? result[i] : null
								])
							)
						);
						set({ data: newData });
					}
				);
				get().fetch();
			} else {
				set({ data: {} });
				get().fetch();
			}
		}, 2000);
	}
}));

export function useCachedLatest<T extends JsonSerializable[]>(
	keys: string[]
): Undefinable<T> {
	const { subscribe, unsubscribe, data } = useLatestCache();

	const cachedKeys = useMemo(() => JSON.stringify(keys), [keys]);

	useEffect(() => {
		const subscriptionId = subscribe(keys);

		return () => unsubscribe(subscriptionId);

		// already covered by `cachedKeys`
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [subscribe, unsubscribe, cachedKeys]);

	return useMemo(
		() => keys.map(key => data[key]) as Undefinable<T>,
		// already covered by `cachedKeys`
		// eslint-disable-next-line react-hooks/exhaustive-deps
		[data, cachedKeys]
	);
}
