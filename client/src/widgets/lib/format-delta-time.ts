/**
 * Formats and prettifies a given delta time.
 * It shows the elapsed time from now first in second steps,
 * then in minute steps and then in hour steps.
 *
 * @param deltaTime - the delta time in milliseconds
 *
 * @example
 * ```ts
 * formatDeltaTime(undefined); // waiting
 * formatDeltaTime(null); // waiting
 * formatDeltaTime(500); // now
 * formatDeltaTime(1000); // 1 sec
 * formatDeltaTime(6000); // 6 sec
 * formatDeltaTime(-8000); // now
 * formatDeltaTime(130000); // 2 min
 * formatDeltaTime(3610_000); // 1 hrs
 * formatDeltaTime(-3610_000); // -1 hrs
 * ```
 */
export function formatDeltaTime(deltaTime: number | null | undefined): string {
	if (typeof deltaTime !== 'number') {
		return 'waiting';
	}

	// return "now" between +1s and -10s
	if (1000 > deltaTime && deltaTime > -10000) {
		return 'now';
	}

	// normal operation
	if (Math.abs(deltaTime) < 600_000) {
		return `${Math.round(deltaTime / 1000)} sec`;
	} else if (Math.abs(deltaTime) < 3600_000) {
		return `${Math.round(deltaTime / 60_000)} min`;
	} else {
		return `${Math.round(deltaTime / 3600_000)} hrs`;
	}
}
