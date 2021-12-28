/**
 * Formats and prettifies a given delta time.
 * It shows the elapsed time from now first in second steps,
 * then in minute steps and then in hour steps.
 *
 * @param deltaTime - the delta time in milliseconds
 *
 * @example
 * ```ts
 * formatDeltaTime(500); // now
 * formatDeltaTime(1000); // 1 sec
 * formatDeltaTime(6000); // 6 sec
 * formatDeltaTime(-8000); // -8 sec
 * formatDeltaTime(130000); // 2 min
 * formatDeltaTime(3610_000); // 1 hrs
 * formatDeltaTime(-3610_000); // -1 hrs
 * ```
 */
export function formatDeltaTime(deltaTime: number): string {
	const absDeltaTime = Math.abs(deltaTime);
	if (absDeltaTime < 1000) {
		return 'now';
	}
	if (Math.abs(deltaTime) < 60_000) {
		return `${Math.round(deltaTime / 1000)} sec`;
	} else if (Math.abs(deltaTime) < 3600_000) {
		return `${Math.round(deltaTime / 60_000)} min`;
	} else {
		return `${Math.round(deltaTime / 3600_000)} hrs`;
	}
}
