/**
 * Calculates a new timeout for the status update timeout
 * based on the current delta time.
 * (time between the last update and now)
 *
 * @param deltaTime - the delta time in milliseconds
 * @returns the new timeout time in milliseconds
 */
export function newStatusTimeout(deltaTime: number): number {
	if (deltaTime < 10000) {
		return 2000; // 2 seconds in the first 10 seconds delay
	} else if (deltaTime < 60000) {
		return 5000; // 5 seconds between 10 and 60 seconds delay
	} else {
		return 10000; // 10 seconds for greater delays
	}
}
