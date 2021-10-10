export interface TimeSyncRequest {
	className: 'de.wuespace.telestion.project.daedalus2.TimeSyncRequest';
	target: 'ejector' | 'seedA' | 'seedB';
	command: 'time setfw' | 'time force' | 'time forcefw';
}
