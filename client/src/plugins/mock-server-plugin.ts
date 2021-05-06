import chalk from 'chalk';
import { Logger, ChalkLogger } from '@fliegwerk/logsemts';
import { MockServer, OnClose, OnInit } from '@wuespace/vertx-mock-server';
import { genFakeData } from '../lib/gen-fake-data';

class RocketSoundMockServer extends MockServer implements OnInit, OnClose {
	intervalId: any;

	static readonly fakeChannel = 'fake-channel';

	onInit() {
		this.intervalId = setInterval(() => {
			const sample = genFakeData();
			this.send(RocketSoundMockServer.fakeChannel, sample);
		}, 100); // send every 1 second new data
	}

	onClose() {
		clearInterval(this.intervalId);
	}
}

const logger = new Logger({
	loggers: [ChalkLogger(chalk)]
});

export function onReady() {
	if (
		process.env.NODE_ENV !== 'production' &&
		process.env.MOCK_SERVER === 'true'
	) {
		const server = new RocketSoundMockServer({
			// logger: logger.getComponentLogger('Mock Server')
		});
		server.listen({ port: 9870, hostname: '0.0.0.0' });
	}
}
