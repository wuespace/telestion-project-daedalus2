/**
 * Generates a random floating point number in the lower and upper bounds.
 * @param lower - the lower bound of the random floating point number
 * @param upper - the upper bound of the random floating point number
 */
export function randNum(lower: number, upper: number): number {
	return Math.random() * (upper - lower) + lower;
}

/**
 * Generates a random integer in the lower and upper bounds.
 * @param lower - the lower bound of the random integer
 * @param upper - the upper bound of the random integer
 */
export function randInt(lower: number, upper: number): number {
	lower = Math.ceil(lower);
	upper = Math.ceil(upper);
	return Math.ceil(Math.random() * (lower - upper)) + lower;
}

/**
 * Returns `true` with a given probability.
 * @param probability - the probability of occurring `true` between `0` and `1`
 */
export function isOccurred(probability: number): boolean {
	return Math.random() < probability;
}
