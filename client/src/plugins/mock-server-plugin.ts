import chalk from 'chalk';
import { Logger, ChalkLogger } from '@fliegwerk/logsemts';
import { MockServer, OnClose, OnInit } from '@wuespace/vertx-mock-server';
import { FakeDataGenerator } from '../lib/fake-data-generator';

class RocketSoundMockServer extends MockServer implements OnInit, OnClose {
	intervalId: any;

	readonly fakeChannel1 = 'fake-channel1';
	readonly fakeChannel2 = 'fake-channel2';
	readonly fakeChannel3 = 'fake-channel3';

	readonly fakeDataGen1: FakeDataGenerator;
	readonly fakeDataGen2: FakeDataGenerator;
	readonly fakeDataGen3: FakeDataGenerator;

	constructor(...args: any[]) {
		super(...args);
		this.fakeDataGen1 = new FakeDataGenerator(0, 10, 10);
		this.fakeDataGen2 = new FakeDataGenerator(-2, 12, 20);
		this.fakeDataGen3 = new FakeDataGenerator(4, 16, 7);
	}

	onInit() {
		this.intervalId = setInterval(() => {
			let sample = this.fakeDataGen1.getDataSample();
			this.send(this.fakeChannel1, [sample]);

			sample = this.fakeDataGen2.getDataSample();
			this.send(this.fakeChannel2, [sample]);

			sample = this.fakeDataGen3.getDataSample();
			this.send(this.fakeChannel3, [sample]);
		}, 33); // send every 1 second new data
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
