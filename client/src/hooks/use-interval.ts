// many thanks to overreacted:
// https://overreacted.io/making-setinterval-declarative-with-react-hooks/
import { useEffect, useRef } from 'react';

/**
 * Executes the given callback in the given interval.
 * If the interval is null, then the execution stops.
 *
 * @param callback callback function to execute in given interval
 * @param interval interval in milliseconds, if null the execution stops
 *
 * @see https://overreacted.io/making-setinterval-declarative-with-react-hooks/
 */
export default function useInterval(
	callback: () => void,
	interval: number | null
): void {
	const savedCallback = useRef<() => void>();

	// remember the latest callback
	useEffect(() => {
		savedCallback.current = callback;
	}, [callback]);

	// set up the interval
	useEffect(() => {
		function tick() {
			savedCallback.current?.();
		}
		if (interval !== null) {
			const id = setInterval(tick, interval);
			return () => clearInterval(id);
		}
	}, [interval]);
}
