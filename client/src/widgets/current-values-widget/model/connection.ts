import { JsonSerializable } from '@wuespace/telestion-client-types';

export interface Connection
	extends Record<string, JsonSerializable | undefined> {
	/**
	 * The address the datasets come from.
	 */
	address: string;

	/**
	 * The title of the dataset.
	 */
	title: string;

	/**
	 * The precision to display in digits.
	 * Defaults to `2`.
	 */
	precision?: number;

	/**
	 * Switch the status to warn, if the delay exceeds the specified time.
	 * When set to `undefined`, no status switch will occur.
	 */
	outdatedTimeWarn?: number;

	/**
	 * Switch the status to error, if the delay exceeds the specified time.
	 * When set to `undefined`, no status switch will occur.
	 */
	outdatedTimeError?: number;

	/**
	 * If the current value drops below this specified value, it will be painted in a "notice" variant.
	 * When set to `undefined` no painting will occur.
	 */
	minValueWarn?: number;

	/**
	 * If the current value drops below this specified value, it will be painted in a "negative" variant.
	 * When set to `undefined` no painting will occur.
	 */
	minValueError?: number;

	/**
	 * If the current value increases above this specified value, it will be painted in a "notice" variant.
	 * When set to `undefined` no painting will occur.
	 */
	maxValueWarn?: number;

	/**
	 * If the current value increases above this specified value, it will be painted in a "negative" variant.
	 * When set to `undefined` no painting will occur.
	 */
	maxValueError?: number;
}
