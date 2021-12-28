export interface Stage {
	/**
	 * The name of the current stage
	 */
	name: string;

	/**
	 * When `true`, the entire stage is special.
	 */
	isSpecial: boolean;
}

/**
 * The different stages of the project mission.
 * Every state can is associated to one stage.
 */
export const stages: Record<number, Stage> = {
	0: {
		name: 'N/A',
		isSpecial: false
	},
	1: {
		name: 'Pre-launch',
		isSpecial: false
	},
	2: {
		name: 'Debug / RadioSilence',
		isSpecial: true
	},
	3: {
		name: 'RocketFlight',
		isSpecial: false
	},
	4: {
		name: 'PostEjection',
		isSpecial: false
	},
	5: {
		name: 'Test',
		isSpecial: true
	}
};

export function getStage(stageId: number): Stage {
	return stages[stageId] || stages[0];
}
