import { useCachedLatest } from '../widgets/hooks';
import {
	ActionButton,
	Flex,
	StatusLight,
	Tooltip,
	TooltipTrigger,
	View
} from '@adobe/react-spectrum';
import { EventBusState, useEventBus } from '@wuespace/telestion-client-core';
import { StateSelector } from 'zustand';
import { formatDeltaTime } from '../widgets/lib/format-delta-time';
import { getConfig } from '../hooks/use-config';

const selector: StateSelector<EventBusState, boolean> = state =>
	state.connectionState === 'connected' || state.connectionState === 'error';

interface Connection {
	/**
	 * The title of the device.
	 */
	title: string;
	/**
	 * the Redis key of the connection.
	 */
	redisKey: string;
	/**
	 * Warning timeout in milliseconds.
	 */
	warnTimeout: number;
	/**
	 * Error timeout in milliseconds.
	 */
	errTimeout: number;
}

const localConnections: Connection[] = [
	{
		title: 'Seed A',
		redisKey: 'seedA/SEED_HEARTBEAT',
		warnTimeout: 5000,
		errTimeout: 15000
	},
	{
		title: 'Seed B',
		redisKey: 'seedB/SEED_HEARTBEAT',
		warnTimeout: 5000,
		errTimeout: 15000
	},
	{
		title: 'Ejector',
		redisKey: 'ejector/EJECTOR_HEARTBEAT',
		warnTimeout: 5000,
		errTimeout: 15000
	}
];

const iridiumConnections: Connection[] = [
	{
		title: 'Seed A',
		redisKey: 'seedA/iridium',
		warnTimeout: 60000,
		errTimeout: 90000
	},
	{
		title: 'Seed B',
		redisKey: 'seedB/iridium',
		warnTimeout: 60000,
		errTimeout: 90000
	}
];

const connections = getConfig().isIridium
	? iridiumConnections
	: localConnections;

const redisKeys = connections.map(device => `latest-time/${device.redisKey}`);

function getStatusLightVariant(
	connection: Connection,
	time: number | undefined,
	isConnected: boolean
): 'positive' | 'yellow' | 'negative' | 'neutral' {
	// handle no event bus connection
	if (!isConnected) return 'neutral';
	// handle no data
	if (!time) return 'negative';

	const deltaTime = Date.now() - time;

	if (deltaTime < connection.warnTimeout) {
		return 'positive';
	} else if (deltaTime < connection.errTimeout) {
		return 'yellow';
	} else {
		return 'negative';
	}
}

function getTooltipText(
	connection: Connection,
	time: number | undefined,
	isConnected: boolean
): string {
	if (!isConnected) return 'Not connected';
	if (!time) return "Hasn't send any data yet";

	const deltaTime = Date.now() - time;

	if (deltaTime < connection.warnTimeout) {
		return `Up-to-date with ${formatDeltaTime(deltaTime)}`;
	} else if (deltaTime < connection.errTimeout) {
		return `Delayed with ${formatDeltaTime(deltaTime)}`;
	} else {
		return `Out-of-date with ${formatDeltaTime(deltaTime)}`;
	}
}

export function ConnectionStatusPanel() {
	const isConnected = useEventBus(selector);
	const times = useCachedLatest<number[]>(redisKeys);

	return (
		<View
			borderWidth="thin"
			borderColor="dark"
			borderRadius="medium"
			paddingStart="size-100"
			paddingEnd="size-200"
			paddingTop="size-50"
		>
			<Flex direction="row" gap="size-100">
				{connections.map((connection, index) => (
					<TooltipTrigger>
						<ActionButton>
							<StatusLight
								variant={getStatusLightVariant(
									connection,
									times[index],
									isConnected
								)}
							>
								{connection.title}
							</StatusLight>
						</ActionButton>
						<Tooltip>
							{getTooltipText(connection, times[index], isConnected)}
						</Tooltip>
					</TooltipTrigger>
				))}
			</Flex>
		</View>
	);
}
