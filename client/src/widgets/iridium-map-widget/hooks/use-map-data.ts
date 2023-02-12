import { JsonSerializable } from '@wuespace/telestion-client-types';
import { useCachedLatest } from '../../hooks';

export interface SeedPosition extends Record<string, JsonSerializable> {
	latitude: number;
	longitude: number;
	altitude: number;
}

export interface MapData {
	/**
	 * The latest position of Seed A directly reported by the GPS module.
	 */
	seedALatestPos?: [latitude: number, longitude: number];

	/**
	 * The last valid position of Seed A validated by the Seed software.
	 */
	seedAValidPos?: [latitude: number, longitude: number];

	/**
	 * The latest position of Seed B directly reported by the GPS module
	 */
	seedBLatestPos?: [latitude: number, longitude: number];

	/**
	 * The last valid position of Seed B validated by the Seed software.
	 */
	seedBValidPos?: [latitude: number, longitude: number];

	/**
	 * The time span since the last report from Seed A in milliseconds.
	 */
	seedALastTime?: number;

	/**
	 * The time span since the last report from Seed B in milliseconds.
	 */
	seedBLastTime?: number;
}

export function useMapData(): MapData {
	const currentTime = Date.now();

	const [seedAPosValid, seedAPosLatest, seedBPosValid, seedBPosLatest] =
		useCachedLatest<SeedPosition[]>([
			'latest/seedA/iridium/payload/valid',
			'latest/seedA/iridium/payload/latest',
			'latest/seedB/iridium/payload/valid',
			'latest/seedB/iridium/payload/latest'
		]);
	const [seedATime, seedBTime] = useCachedLatest<number[]>([
		'latest-time/seedA/iridium/payload',
		'latest-time/seedB/iridium/payload'
	]);

	return {
		seedALatestPos: seedAPosLatest
			? [seedAPosLatest.latitude, seedAPosLatest.longitude]
			: undefined,
		seedAValidPos: seedAPosValid
			? [seedAPosValid.latitude, seedAPosValid.longitude]
			: undefined,
		seedBLatestPos: seedBPosLatest
			? [seedBPosLatest.latitude, seedBPosLatest.longitude]
			: undefined,
		seedBValidPos: seedBPosValid
			? [seedBPosValid.latitude, seedBPosValid.longitude]
			: undefined,
		seedALastTime: seedATime ? currentTime - seedATime : undefined,
		seedBLastTime: seedBTime ? currentTime - seedBTime : undefined
	};
}
