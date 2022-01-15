import { LogMessage } from '../../model/tc-console';

export function splitBoots(messages: LogMessage[]): LogMessage[][] {
	const bootIndices: number[] = [0];
	let lastTimeLocal = -1;
	messages.forEach((message, index) => {
		// when time_local jumps because of reboot,
		// insert current index into bootIndices array
		if (message.time_local < lastTimeLocal) bootIndices.push(index);
		lastTimeLocal = message.time_local;
	});

	// now, we have list of indices
	// which point to the start messages of different boots

	// add the imaginary "future" boot index,
	// so we can split easier
	bootIndices.push(messages.length);

	const boots: LogMessage[][] = [];
	for (let i = 0; i < bootIndices.length - 1; i++) {
		boots.push(messages.slice(bootIndices[i], bootIndices[i + 1]));
	}
	return boots;
}

export function formatMessage(message: LogMessage): string {
	const date = new Date(message.receiveTime);
	// native date formatting in JavaScript...
	const [, , , , time] = date.toUTCString().split(' ');
	return time + ': ' + message.message.trim();
}

export function genBootSeparator(currentIndex: number, count: number): string {
	let name;
	// last index === current boot cycle
	if (currentIndex === count - 1) {
		name = 'CURRENT BOOT';
	} else {
		name = `BOOT ${currentIndex - (count - 1)}`;
	}

	return `####################### ${name} #######################`;
}
