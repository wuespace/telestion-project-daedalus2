/**
 * States of the D2 state machines.
 *
 * A 'main state' (without entry or exit suffix) is repeated until a certain
 * event toggles the transition to the next state, so this next state indicator
 * indicates which state comes after the certain event.
 */
export interface State {
	/**
	 * The name of the current state.
	 */
	name: string;
	/**
	 * The id of the next state.
	 */
	next: number;
	/**
	 * The id of the next major state.
	 * Can be undefined. In this case, the next state applies.
	 */
	major?: number;
	/**
	 * The mission stage id of the current state.
	 */
	stage: number;
	/**
	 * When `true`, the state is special.
	 */
	isSpecial: boolean;
}

/**
 * Shared States:
 *
 * Until the Moment of Ejection, both StateMachines run in parallel
 * and get triggered by the RXSM.
 * Its signals are forwarded to the SBC via CAN.
 * These States are exactly identical between the RBC and SBC.
 */
export const sharedStates: Record<number, State> = {
	// "capture all" state which displays "*"
	'-1': {
		name: '*',
		next: -1,
		stage: 0,
		isSpecial: false
	},
	0: {
		name: 'Invalid',
		next: -1,
		stage: 1,
		isSpecial: false
	},
	// Fallback state (bc 0 is taken by a different state)
	1: {
		name: 'N/A',
		next: 1,
		stage: 0,
		isSpecial: false
	},
	2: {
		name: 'Post',
		next: 3,
		major: 5,
		stage: 1,
		isSpecial: false
	},
	3: {
		name: 'PostExit',
		next: 4,
		major: 5,
		stage: 1,
		isSpecial: false
	},
	4: {
		name: 'PreFlightEntry',
		next: 5,
		stage: 1,
		isSpecial: false
	},
	5: {
		name: 'PreFlight',
		next: 6,
		major: 8,
		stage: 1,
		isSpecial: false
	},
	6: {
		name: 'PreFlightExit',
		next: 7,
		major: 8,
		stage: 1,
		isSpecial: false
	},
	7: {
		name: 'ArmedForFlightEntry',
		next: 8,
		stage: 1,
		isSpecial: false
	},
	8: {
		name: 'ArmedForFlight',
		next: 9,
		major: 11,
		stage: 1,
		isSpecial: false
	},
	9: {
		name: 'ArmedForFlightExit',
		next: 10,
		major: 11,
		stage: 1,
		isSpecial: false
	},
	10: {
		name: 'RocketFlightEntry',
		next: 11,
		stage: 3,
		isSpecial: false
	},
	11: {
		name: 'RocketFlight',
		next: 12,
		major: 14,
		stage: 3,
		isSpecial: false
	},
	12: {
		name: 'RocketFlightExit',
		next: 13,
		major: 14,
		stage: 3,
		isSpecial: false
	},
	13: {
		name: 'EjectionEntry',
		next: 14,
		stage: 3,
		isSpecial: false
	},
	14: {
		name: 'Ejection',
		next: 15,
		major: 17,
		stage: 3,
		isSpecial: false
	},
	15: {
		name: 'EjectionExit',
		next: 16,
		major: 17,
		stage: 3,
		isSpecial: false
	},
	// from this point, ejector and seeds have different states
	// please look at:
	//  - ejectorStates
	//  - seedStates

	// debug/radio-silence states
	121: {
		name: 'RadioSilenceEntry',
		next: 122,
		stage: 2,
		isSpecial: true
	},
	122: {
		name: 'RadioSilence',
		next: 123,
		major: -1,
		stage: 2,
		isSpecial: true
	},
	123: {
		name: 'RadioSilenceExit',
		next: -1,
		stage: 2,
		isSpecial: true
	},
	124: {
		name: 'DebugEntry',
		next: 125,
		stage: 2,
		isSpecial: true
	},
	125: {
		name: 'Debug',
		next: 126,
		major: -1,
		stage: 2,
		isSpecial: true
	},
	126: {
		name: 'DebugExit',
		next: -1,
		stage: 2,
		isSpecial: true
	},
	// test states (only used during testing)
	99: {
		name: 'STATIC_TEST_ENTRY',
		next: 100,
		stage: 5,
		isSpecial: true
	},
	100: {
		name: 'STATIC_TEST',
		next: -1,
		stage: 5,
		isSpecial: true
	}
};

export const ejectorStates: Record<number, State> = {
	...sharedStates,
	16: {
		name: 'PostEjectionEntry',
		next: 17,
		stage: 4,
		isSpecial: false
	},
	17: {
		name: 'PostEjection',
		next: 18,
		major: -1,
		stage: 4,
		isSpecial: false
	},
	18: {
		name: 'PostEjectionExit',
		next: -1,
		stage: 4,
		isSpecial: false
	}
};

export const seedStates: Record<number, State> = {
	...sharedStates,
	16: {
		name: 'FallingEntry',
		next: 17,
		stage: 4,
		isSpecial: false
	},
	17: {
		name: 'Falling',
		next: 18,
		major: 20,
		stage: 4,
		isSpecial: false
	},
	18: {
		name: 'FallingExit',
		next: 19,
		major: 20,
		stage: 4,
		isSpecial: false
	},
	19: {
		name: 'MidAirBreakingEntry',
		next: 20,
		stage: 4,
		isSpecial: false
	},
	20: {
		name: 'MidAirBreaking',
		next: 21,
		major: 23,
		stage: 4,
		isSpecial: false
	},
	21: {
		name: 'MidAirBreakingExit',
		next: 22,
		major: 23,
		stage: 4,
		isSpecial: false
	},
	22: {
		name: 'Falling2Entry',
		next: 23,
		stage: 4,
		isSpecial: false
	},
	23: {
		name: 'Falling2',
		next: 24,
		major: 26,
		stage: 4,
		isSpecial: false
	},
	24: {
		name: 'Falling2Exit',
		next: 25,
		major: 26,
		stage: 4,
		isSpecial: false
	},
	25: {
		name: 'LandingEntry',
		next: 26,
		stage: 4,
		isSpecial: false
	},
	26: {
		name: 'Landing',
		next: 27,
		major: 29,
		stage: 4,
		isSpecial: false
	},
	27: {
		name: 'LandingExit',
		next: 28,
		major: 29,
		stage: 4,
		isSpecial: false
	},
	28: {
		name: 'FinalPlopEntry',
		next: 29,
		stage: 4,
		isSpecial: false
	},
	29: {
		name: 'FinalPlop',
		next: 30,
		major: 32,
		stage: 4,
		isSpecial: false
	},
	30: {
		name: 'FinalPlopExit',
		next: 31,
		major: 32,
		stage: 4,
		isSpecial: false
	},
	31: {
		name: 'RecoveryEntry',
		next: 32,
		stage: 4,
		isSpecial: false
	},
	32: {
		name: 'Recovery',
		next: 33,
		major: -1,
		stage: 4,
		isSpecial: false
	},
	33: {
		name: 'RecoveryExit',
		next: -1,
		stage: 4,
		isSpecial: false
	}
};

/**
 * Returns the state information for the specific subsystem.
 * @param subsystem - the subsystem which sends the state
 * @param stateNumber - the actual state number
 */
export function getState(
	subsystem: 'ejector' | 'seed',
	stateNumber: number
): State {
	if (subsystem === 'ejector') {
		return ejectorStates[stateNumber] || ejectorStates[-1];
	} else {
		return seedStates[stateNumber] || seedStates[-1];
	}
}
