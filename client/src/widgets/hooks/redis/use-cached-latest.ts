import create from 'zustand';
import { JsonSerializable } from '@wuespace/telestion-client-types';
import { useEffect } from 'react';
import { useEventBus } from '@wuespace/telestion-client-core';
import { ConnectionState } from '@wuespace/vertx-event-bus';

let timerId: NodeJS.Timeout | undefined = undefined;
let nextId = 1;

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
		console.log('subscribe', ...keys, 'id', nextId);
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
		console.log('unsubscribe', id);

		set(state => ({ subscriptions: { ...state.subscriptions, [id]: [] } }));
	},
	fetch: () => {
		console.log('fetch');
		timerId = setTimeout(() => {
			const { eventBus } = useEventBus.getState();

			const subscribedKeys = Array.from(
				new Set(Object.values(get().subscriptions).flat())
			);

			if (
				eventBus?.state === ConnectionState.OPEN &&
				subscribedKeys.length > 0
			) {
				eventBus.send(
					'request-latest',
					{
						fields: subscribedKeys,
						className:
							'de.wuespace.telestion.project.daedalus2.redis.RedisLatestRequest'
					},
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
): T {
	const { subscribe, unsubscribe, data } = useLatestCache();

	useEffect(() => {
		console.log('useCacheLatest -> useEffect');
		const subscriptionId = subscribe(keys);

		return () => unsubscribe(subscriptionId);
	}, [subscribe, unsubscribe, JSON.stringify(keys)]);

	console.log('useCacheLatest');

	return keys.map(key => data[key]) as T;
}
