import { useChannelLatest } from '@wuespace/telestion-client-core';
import { LoadingIndicator } from '@wuespace/telestion-client-common';

import { FlightState } from '../../model/channels';
import { FlightStateMessage } from '../../model/messages/flight-state';
import { State } from './model';
import { StateDisplay } from './state-display';
import { flushSync } from 'react-dom';

const states: { [key: number]: State } = {
	//State Definitions: https://gitlab2.informatik.uni-wuerzburg.de/wuespace/daedalus2/software/schwarzes-brett/-/wikis/StateMachine

	//0-15 (excluding 1 = fallback State) are shared States (Seed and Ejector)
	//according to gitlab Wiki: 13-15 are only present in EjectorStateMachine (?)
	0: {
		currentState: 'kInvalid',
		nextState: '', //not defined, multiple states possible after invalid
		tagName: 'Pre-Launch',
		isSpecial: false
	},
	1: {
		//Fallback state (bc 0 is taken by a different state)
		currentState: '-',
		nextState: '-',
		tagName: '-',
		isSpecial: false //true = red border in tag, false = grey
	},
	2: {
		currentState: 'kPost',
		nextState: 'kPostExit',
		tagName: 'Pre-Launch',
		isSpecial: false
	},
	3: {
		currentState: 'kPostExit',
		nextState: 'kPreFlightEntry',
		tagName: 'Pre-Launch',
		isSpecial: false
	},
	4: {
		currentState: 'kPreFlightEntry',
		nextState: 'kPreFlight',
		tagName: 'Pre-Launch',
		isSpecial: false
	},
	5: {
		currentState: 'kPreFlight',
		nextState: 'kPreFlightExit', //a 'main state' (without entry or exit suffix) is repeated until a certain event toggles the transition to the next state, so this next state indicator indicates which states comes after the certain event
		tagName: 'Pre-Launch',
		isSpecial: false
	},
	6: {
		currentState: 'kPreFlightExit',
		nextState: 'kArmedForFlightEntry',
		tagName: 'Pre-Launch',
		isSpecial: false
	},
	7: {
		currentState: 'kArmedForFlightEntry',
		nextState: 'kArmedForFlight',
		tagName: 'Pre-Launch',
		isSpecial: false
	},
	8: {
		currentState: 'kArmedForFlight',
		nextState: 'kArmedForFlightExit',
		tagName: 'Pre-Launch',
		isSpecial: false
	},
	9: {
		currentState: 'kArmedForFlightExit',
		nextState: 'kRocketFlightEntry',
		tagName: 'Pre-Launch',
		isSpecial: false
	},
	10: {
		currentState: 'kRocketFlightEntry',
		nextState: 'kRocketFlight',
		tagName: 'RocketFlight',
		isSpecial: false
	},
	11: {
		currentState: 'kRocketFlight',
		nextState: 'kRocketFlightExit',
		tagName: 'RocketFlight',
		isSpecial: false
	},
	12: {
		currentState: 'kRocketFlightExit',
		nextState: 'kEjectionEntry',
		tagName: 'RocketFlight',
		isSpecial: false
	},
	13: {
		currentState: 'kEjectionEntry',
		nextState: 'kEjection',
		tagName: 'RocketFlight',
		isSpecial: false
	},
	14: {
		currentState: 'kEjection',
		nextState: 'kEjectionExit',
		tagName: 'RocketFlight',
		isSpecial: false
	},
	15: {
		currentState: 'kEjectionExit',
		nextState: 'kFallingEntry', //transition to the Seed State Machine being separate from the Ejector State Machine
		tagName: 'RocketFlight',
		isSpecial: false
	},
	//16 - 30 are states only present in the SeedStateMachine
	16: {
		currentState: 'kFallingEntry',
		nextState: 'kFalling',
		tagName: 'PostEjection',
		isSpecial: false
	},
	17: {
		currentState: 'kFalling',
		nextState: 'kFallingExit',
		tagName: 'PostEjection',
		isSpecial: false
	},
	18: {
		currentState: 'kFallingExit',
		nextState: 'kMidAirBreakingEntry',
		tagName: 'PostEjection',
		isSpecial: false
	},
	19: {
		currentState: 'kMidAirBreakingEntry',
		nextState: 'kMidAirBreaking',
		tagName: 'PostEjection',
		isSpecial: false
	},
	20: {
		currentState: 'kMidAirBreaking',
		nextState: 'kMidAirBreakingExit',
		tagName: 'PostEjection',
		isSpecial: false
	},
	21: {
		currentState: 'kMidAirBreakingExit',
		nextState: 'kFalling2Entry',
		tagName: 'PostEjection',
		isSpecial: false
	},
	22: {
		currentState: 'kFalling2Entry',
		nextState: 'kFalling2',
		tagName: 'PostEjection',
		isSpecial: false
	},
	23: {
		currentState: 'kFalling2',
		nextState: 'kFalling2Exit',
		tagName: 'PostEjection',
		isSpecial: false
	},
	24: {
		currentState: 'kFalling2Exit',
		nextState: 'kLandingEntry',
		tagName: 'PostEjection',
		isSpecial: false
	},
	25: {
		currentState: 'kLandingEntry',
		nextState: 'kLanding',
		tagName: 'PostEjection',
		isSpecial: false
	},
	26: {
		currentState: 'kLanding',
		nextState: 'kLandingExit',
		tagName: 'PostEjection',
		isSpecial: false
	},
	27: {
		currentState: 'kLandingExit',
		nextState: 'kRecoveryEntry',
		tagName: 'PostEjection',
		isSpecial: false
	},
	28: {
		currentState: 'kRecoveryEntry',
		nextState: 'kRecovery',
		tagName: 'PostEjection',
		isSpecial: false
	},
	29: {
		currentState: 'kRecovery',
		nextState: 'kRecoveryExit',
		tagName: 'PostEjection',
		isSpecial: false
	},
	30: {
		currentState: 'kRecoveryExit',
		nextState: '-', //end
		tagName: 'PostEjection',
		isSpecial: false
	},
	//121-126 are Debug-/RadioSilence-States (shared: Seed and Ejector)
	121: {
		currentState: 'kRadioSilenceEntry',
		nextState: 'kRadioSilence',
		tagName: 'Debug / RadioSilence',
		isSpecial: true
	},
	122: {
		currentState: 'kRadioSilence',
		nextState: 'kRadioSilenceExit',
		tagName: 'Debug / RadioSilence',
		isSpecial: true
	},
	123: {
		currentState: 'kRadioSilenceExit',
		nextState: '', //multiple next States possible after RadioSilence
		tagName: 'Debug / RadioSilence',
		isSpecial: true
	},
	124: {
		currentState: 'kDebugEntry',
		nextState: 'kDebug',
		tagName: 'Debug / RadioSilence',
		isSpecial: true
	},
	125: {
		currentState: 'kDebug',
		nextState: 'kDebugExit',
		tagName: 'Debug / RadioSilence',
		isSpecial: true
	},
	126: {
		currentState: 'kDebugExit',
		nextState: '', //multiple next States possible after debug
		tagName: 'Debug / RadioSilence',
		isSpecial: true
	},
	//99-100 are TestStates (only used at wind tunnel tests, shared: Seed and Ejector)
	99: {
		currentState: 'kSTATIC_TEST_ENTRY',
		nextState: 'kSTATIC_TEST',
		tagName: 'Test',
		isSpecial: true
	},
	100: {
		currentState: 'kSTATIC_TEST',
		nextState: '-', //end
		tagName: 'Test',
		isSpecial: true
	}
};

const fallbackState: State = states[1];

export function Widget() {
	const current = useChannelLatest<FlightStateMessage>(FlightState);

	if (current) console.log('Flightstate:', current.result[0].state);

	return (
		// @ts-ignore
		<LoadingIndicator timeout={0} dependencies={[current]}>
			{({ result }) => (
				<StateDisplay
					stateSeed={states[result[0].state] || fallbackState}
					stateEjector={states[result[0].state] || fallbackState}
				/>
			)}
		</LoadingIndicator>
	);
}
