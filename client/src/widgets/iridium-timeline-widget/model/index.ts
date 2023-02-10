export interface StepPoint {
	/**
	 * The unique id of the step point.
	 */
	id: string | number;

	/**
	 * The step point is connected
	 * with the step point on the left side.
	 */
	isLeftConnected: boolean;

	/**
	 * The step point is connected
	 * with the step point on the right side.
	 */
	isRightConnected: boolean;

	/**
	 * The label of the step point.
	 */
	label: string;

	/**
	 * When `true`, the step point is done in the step list.
	 */
	isDone: boolean;

	/**
	 * When `true`, the step point is currently active in the step list.
	 */
	isActive: boolean;

	/**
	 * When `true`, the step point is rendered special.
	 */
	isCritical: boolean;
}
