import { StepPoint } from '../model';
import { useExperimentStates } from './use-experiment-states';
import { useExperimentDelay } from './use-experiment-delay';

const MAX_SEED_NOMINAL_STATE = 33;

const MAX_DELAY = 60000; /* ms */

function isDelayed(delay: number) {
	return delay > MAX_DELAY;
}

function hasSeedNominalState(
	seedState: number,
	minState: number,
	delay = 0
): boolean {
	return (
		delay <= MAX_DELAY &&
		seedState >= minState &&
		seedState <= MAX_SEED_NOMINAL_STATE
	);
}

function isIn(
	state: number,
	minState: number,
	maxState: number,
	delay = 0
): boolean {
	return delay <= MAX_DELAY && state >= minState && state <= maxState;
}

function isInRadioSilence(state: number, delay = 0): boolean {
	return delay <= MAX_DELAY && state >= 121 && state <= 123;
}

function isInNormalState(state: number): boolean {
	return state >= 2 && state <= 33;
}

function hasDiverged(state1: number, state2: number): boolean {
	return Math.abs(state1 - state2) >= 6;
}

function isInAbnormalState(state: number): boolean {
	// is abnormal when not in normal states and not in radio silence
	return !isInNormalState(state) && !isInRadioSilence(state);
}

export function useStateStepPoints(): StepPoint[] {
	const [seedAState, seedBState] = useExperimentStates();
	const [seedADelay, seedBDelay] = useExperimentDelay();

	const isAnyInRadioSilence =
		isInRadioSilence(seedAState) || isInRadioSilence(seedBState);
	const isAnyActiveInRadioSilence =
		isInRadioSilence(seedAState, seedADelay) ||
		isInRadioSilence(seedBState, seedBDelay);

	const isAnyDelayed =
		(isDelayed(seedADelay) && !isInRadioSilence(seedAState)) ||
		(isDelayed(seedBDelay) && !isInRadioSilence(seedBState));

	const isDiverged = hasDiverged(seedAState, seedBState);
	const isAnyInAbnormalState =
		isInAbnormalState(seedAState) || isInAbnormalState(seedBState);
	const isAbnormal = isAnyDelayed || isDiverged || isAnyInAbnormalState;

	return [
		{
			id: 0,
			label: 'Abnormal',
			isLeftConnected: false,
			isRightConnected: false,
			isDone: isAbnormal,
			isActive: isAbnormal,
			isCritical: true
		},
		{
			id: 1,
			label: 'Radio Silence',
			isLeftConnected: false,
			isRightConnected: true,
			isDone: isAnyInRadioSilence,
			isActive: isAnyActiveInRadioSilence,
			isCritical: true
		},
		{
			id: 2,
			label: 'Pre Flight',
			isLeftConnected: true,
			isRightConnected: true,
			isDone:
				hasSeedNominalState(seedAState, 4) ||
				hasSeedNominalState(seedBState, 4) ||
				isAnyInRadioSilence,
			isActive:
				isIn(seedAState, 4, 6, seedADelay) ||
				isIn(seedBState, 4, 6, seedBDelay) ||
				isAnyActiveInRadioSilence,
			isCritical: false
		},
		{
			id: 3,
			label: 'Armed for Flight',
			isLeftConnected: true,
			isRightConnected: true,
			isDone:
				hasSeedNominalState(seedAState, 7) ||
				hasSeedNominalState(seedBState, 7),
			isActive:
				isIn(seedAState, 7, 9, seedADelay) ||
				isIn(seedBState, 7, 9, seedBDelay),
			isCritical: false
		},
		{
			id: 4,
			label: 'Rocket Flight',
			isLeftConnected: true,
			isRightConnected: true,
			isDone:
				hasSeedNominalState(seedAState, 10) ||
				hasSeedNominalState(seedBState, 10),
			isActive:
				isIn(seedAState, 10, 12, seedADelay) ||
				isIn(seedBState, 10, 12, seedBDelay),
			isCritical: false
		},
		{
			id: 5,
			label: 'Ejection',
			isLeftConnected: true,
			isRightConnected: true,
			isDone:
				hasSeedNominalState(seedAState, 13) ||
				hasSeedNominalState(seedBState, 13),
			isActive:
				isIn(seedAState, 13, 15, seedADelay) ||
				isIn(seedBState, 13, 15, seedBDelay),
			isCritical: false
		},
		{
			id: 6,
			label: 'Falling',
			isLeftConnected: true,
			isRightConnected: true,
			isDone:
				hasSeedNominalState(seedAState, 16) ||
				hasSeedNominalState(seedBState, 16),
			isActive:
				isIn(seedAState, 16, 18, seedADelay) ||
				isIn(seedBState, 16, 18, seedBDelay),
			isCritical: false
		},
		{
			id: 7,
			label: 'Mid Air Breaking',
			isLeftConnected: true,
			isRightConnected: true,
			isDone:
				hasSeedNominalState(seedAState, 19) ||
				hasSeedNominalState(seedBState, 19),
			isActive:
				isIn(seedAState, 19, 21, seedADelay) ||
				isIn(seedBState, 19, 21, seedBDelay),
			isCritical: false
		},
		{
			id: 8,
			label: 'Falling 2',
			isLeftConnected: true,
			isRightConnected: true,
			isDone:
				hasSeedNominalState(seedAState, 22) ||
				hasSeedNominalState(seedBState, 22),
			isActive:
				isIn(seedAState, 22, 24, seedADelay) ||
				isIn(seedBState, 22, 24, seedBDelay),
			isCritical: false
		},
		{
			id: 9,
			label: 'Landing',
			isLeftConnected: true,
			isRightConnected: true,
			isDone:
				hasSeedNominalState(seedAState, 25) ||
				hasSeedNominalState(seedBState, 25),
			isActive:
				isIn(seedAState, 25, 27, seedADelay) ||
				isIn(seedBState, 25, 27, seedBDelay),
			isCritical: false
		},
		{
			id: 10,
			label: 'Final Plop',
			isLeftConnected: true,
			isRightConnected: true,
			isDone:
				hasSeedNominalState(seedAState, 28) ||
				hasSeedNominalState(seedBState, 28),
			isActive:
				isIn(seedAState, 28, 31, seedADelay) ||
				isIn(seedBState, 28, 31, seedBDelay),
			isCritical: false
		},
		{
			id: 11,
			label: 'Recovery',
			isLeftConnected: true,
			isRightConnected: false,
			isDone:
				hasSeedNominalState(seedAState, 31) ||
				hasSeedNominalState(seedBState, 31),
			isActive:
				isIn(seedAState, 31, 33, seedADelay) ||
				isIn(seedBState, 31, 33, seedBDelay),
			isCritical: false
		}
	];
}
