import { MongoPick, SystemT } from './mongo-collections';

export const telecommandClassName =
	'de.wuespace.telestion.project.daedalus2.mavlink.internal.Telecommand';

export type LatestState = MongoPick<SystemT, 'currentState'>;
export const latestStateChannel = 'latest-state';
