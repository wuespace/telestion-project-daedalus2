/**
 * States of the D2 state machines.
 *
 * A 'main state' (without entry or exit suffix) is repeated until a certain
 * event toggles the transition to the next state, so this next state indicator
 * indicates which states comes after the certain event.
 */
export interface State {
	/**
	 * The name of the current state.
	 */
	name: string;
	/**
	 * The name of the next state.
	 */
	next: string;
	/**
	 * The next major state.
	 * Can be undefined. In this case, the next state applies.
	 */
	major?: string;
	/**
	 * The mission stage of the current state.
	 */
	stage: string;
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
	0: {
		name: 'kInvalid',
		next: '*', // not defined, multiple states possible after invalid
		stage: 'Pre-Launch',
		isSpecial: false
	},
	// Fallback state (bc 0 is taken by a different state)
	1: {
		name: 'N/A',
		next: 'N/A',
		stage: 'N/A',
		isSpecial: false
	},
	2: {
		name: 'kPost',
		next: 'kPostExit',
		major: 'kPreFlight',
		stage: 'Pre-Launch',
		isSpecial: false
	},
	3: {
		name: 'kPostExit',
		next: 'kPreFlightEntry',
		major: 'kPreFlight',
		stage: 'Pre-Launch',
		isSpecial: false
	},
	4: {
		name: 'kPreFlightEntry',
		next: 'kPreFlight',
		stage: 'Pre-Launch',
		isSpecial: false
	},
	5: {
		name: 'kPreFlight',
		next: 'kPreFlightExit',
		major: 'kArmedForFlight',
		stage: 'Pre-Launch',
		isSpecial: false
	},
	6: {
		name: 'kPreFlightExit',
		next: 'kArmedForFlightEntry',
		major: 'kArmedForFlight',
		stage: 'Pre-Launch',
		isSpecial: false
	},
	7: {
		name: 'kArmedForFlightEntry',
		next: 'kArmedForFlight',
		stage: 'Pre-Launch',
		isSpecial: false
	},
	8: {
		name: 'kArmedForFlight',
		next: 'kArmedForFlightExit',
		major: 'kRocketFlight',
		stage: 'Pre-Launch',
		isSpecial: false
	},
	9: {
		name: 'kArmedForFlightExit',
		next: 'kRocketFlightEntry',
		major: 'kRocketFlight',
		stage: 'Pre-Launch',
		isSpecial: false
	},
	10: {
		name: 'kRocketFlightEntry',
		next: 'kRocketFlight',
		stage: 'RocketFlight',
		isSpecial: false
	},
	11: {
		name: 'kRocketFlight',
		next: 'kRocketFlightExit',
		major: 'kEjection',
		stage: 'RocketFlight',
		isSpecial: false
	},
	12: {
		name: 'kRocketFlightExit',
		next: 'kEjectionEntry',
		major: 'kEjection',
		stage: 'RocketFlight',
		isSpecial: false
	},
	13: {
		name: 'kEjectionEntry',
		next: 'kEjection',
		stage: 'RocketFlight',
		isSpecial: false
	},
	14: {
		name: 'kEjection',
		next: 'kEjectionExit',
		stage: 'RocketFlight',
		isSpecial: false
	},
	// transition to the Seed State Machine being separate
	// from the Ejector State Machine
	15: {
		name: 'kEjectionExit',
		next: 'kFallingEntry',
		major: 'kFalling',
		stage: 'RocketFlight',
		isSpecial: false
	},
	// debug/radio-silence states
	121: {
		name: 'kRadioSilenceEntry',
		next: 'kRadioSilence',
		stage: 'Debug / RadioSilence',
		isSpecial: true
	},
	122: {
		name: 'kRadioSilence',
		next: 'kRadioSilenceExit',
		major: '*',
		stage: 'Debug / RadioSilence',
		isSpecial: true
	},
	123: {
		name: 'kRadioSilenceExit',
		next: '*', // multiple next States possible after RadioSilence
		stage: 'Debug / RadioSilence',
		isSpecial: true
	},
	124: {
		name: 'kDebugEntry',
		next: 'kDebug',
		stage: 'Debug / RadioSilence',
		isSpecial: true
	},
	125: {
		name: 'kDebug',
		next: 'kDebugExit',
		major: '*',
		stage: 'Debug / RadioSilence',
		isSpecial: true
	},
	126: {
		name: 'kDebugExit',
		next: '*', // multiple next States possible after debug
		stage: 'Debug / RadioSilence',
		isSpecial: true
	},
	// test states (only used during testing)
	99: {
		name: 'kSTATIC_TEST_ENTRY',
		next: 'kSTATIC_TEST',
		stage: 'Test',
		isSpecial: true
	},
	100: {
		name: 'kSTATIC_TEST',
		next: '-', // end
		stage: 'Test',
		isSpecial: true
	}
};

/**
 * Seed States:
 *
 * These states are only present in the SeedStateMachine.
 */
export const seedStates: Record<number, State> = {
	16: {
		name: 'kFallingEntry',
		next: 'kFalling',
		stage: 'PostEjection',
		isSpecial: false
	},
	17: {
		name: 'kFalling',
		next: 'kFallingExit',
		major: 'kMidAirBreaking',
		stage: 'PostEjection',
		isSpecial: false
	},
	18: {
		name: 'kFallingExit',
		next: 'kMidAirBreakingEntry',
		major: 'kMidAirBreaking',
		stage: 'PostEjection',
		isSpecial: false
	},
	19: {
		name: 'kMidAirBreakingEntry',
		next: 'kMidAirBreaking',
		stage: 'PostEjection',
		isSpecial: false
	},
	20: {
		name: 'kMidAirBreaking',
		next: 'kMidAirBreakingExit',
		major: 'kFalling2',
		stage: 'PostEjection',
		isSpecial: false
	},
	21: {
		name: 'kMidAirBreakingExit',
		next: 'kFalling2Entry',
		major: 'kFalling2',
		stage: 'PostEjection',
		isSpecial: false
	},
	22: {
		name: 'kFalling2Entry',
		next: 'kFalling2',
		stage: 'PostEjection',
		isSpecial: false
	},
	23: {
		name: 'kFalling2',
		next: 'kFalling2Exit',
		major: 'kLanding',
		stage: 'PostEjection',
		isSpecial: false
	},
	24: {
		name: 'kFalling2Exit',
		next: 'kLandingEntry',
		major: 'kLanding',
		stage: 'PostEjection',
		isSpecial: false
	},
	25: {
		name: 'kLandingEntry',
		next: 'kLanding',
		stage: 'PostEjection',
		isSpecial: false
	},
	26: {
		name: 'kLanding',
		next: 'kLandingExit',
		major: 'kRecovery',
		stage: 'PostEjection',
		isSpecial: false
	},
	27: {
		name: 'kLandingExit',
		next: 'kRecoveryEntry',
		major: 'kRecovery',
		stage: 'PostEjection',
		isSpecial: false
	},
	28: {
		name: 'kRecoveryEntry',
		next: 'kRecovery',
		stage: 'PostEjection',
		isSpecial: false
	},
	29: {
		name: 'kRecovery',
		next: 'kRecoveryExit',
		major: '-',
		stage: 'PostEjection',
		isSpecial: false
	},
	30: {
		name: 'kRecoveryExit',
		next: '-', //end
		stage: 'PostEjection',
		isSpecial: false
	}
};

/**
 * All states that are possible during mission.
 */
export const states: Record<number, State> = {
	...sharedStates,
	...seedStates
};

export const fallbackState = states[1];

/**
 * Searches and returns the state by a given name.
 * If no state name matches, the fallback state is returned.
 * @param name - the name of the state to search for
 */
export function stateFromName(name: string): State {
	return (
		Object.values(states).filter(state => state.name === name)[0] ||
		fallbackState
	);
}
