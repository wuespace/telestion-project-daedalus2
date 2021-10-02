import { useCallback } from 'react';
import { useLogger, useRequest } from '@wuespace/telestion-client-core';
import {
	AGpsResponse,
	RequestAbort,
	requestChannel,
	RequestNewData,
	RequestNewTarget,
	RequestTransmission
} from '../../model/a-gps';

export interface WidgetReducers {
	newData(name: string, encodedData: string): void;
	newTarget(target: string): void;
	start(): void;
	abort(): void;
}

const requestTransmission: RequestTransmission = {
	type: 'transmission',
	className:
		'de.wuespace.telestion.project.daedalus2.gps.message.RequestTransmission'
};

const requestAbort: RequestAbort = {
	type: 'abort',
	className: 'de.wuespace.telestion.project.daedalus2.gps.message.RequestAbort'
};

export function useAGpsReducers(): WidgetReducers {
	const logger = useLogger('A-GPS Reducer');
	const send = useRequest<AGpsResponse>(requestChannel);

	const newData = useCallback(
		(name: string, encodedData: string) => {
			logger.info(`New data: ${name}`);

			const request: RequestNewData = {
				type: 'new-data',
				data: {
					name,
					encodedData,
					className: 'de.wuespace.telestion.project.daedalus2.gps.AGpsData'
				},
				className:
					'de.wuespace.telestion.project.daedalus2.gps.message.RequestNewData'
			};

			send(request, response => {
				if (response.type === 'new-data') {
					if (response.result === 1) {
						alert('Is already transmitting');
					}
				}
			});
		},
		[logger, send]
	);

	const newTarget = useCallback(
		(target: string) => {
			logger.info(`New target: ${target}`);

			const request: RequestNewTarget = {
				type: 'new-target',
				target,
				className:
					'de.wuespace.telestion.project.daedalus2.gps.message.RequestNewTarget'
			};

			send(request, response => {
				if (response.type === 'new-target') {
					if (response.result === 1) {
						alert('Is already transmitting');
					}
				}
			});
		},
		[logger, send]
	);

	const start = useCallback(() => {
		logger.info('Start transmission');

		send(requestTransmission, response => {
			if (response.type === 'transmission') {
				switch (response.result) {
					case 1:
						alert('Is already transmitting');
						break;
					case 2:
						alert('No data to transmit');
						break;
					case 3:
						alert('No target to transmit data to');
						break;
					case 4:
						alert('Cannot split binary blob');
						break;
				}
			}
		});
	}, [logger, send]);

	const abort = useCallback(() => {
		logger.info('Abort transmission');

		send(requestAbort, response => {
			if (response.type === 'abort') {
				if (response.result > 0) {
					alert('Not transmitting. Nothing to abort');
				}
			}
		});
	}, [logger, send]);

	return { newData, newTarget, start, abort };
}
