import { loadSync } from '../lib/load-sync';

export interface Config {
	initialServerURL: string;
	initialUsername: string;
	/**
	 * When `true`, reconfigure the UI to match the Iridium connection tracking.
	 */
	isIridium: boolean;
}

export const defaultConfig: Config = {
	initialServerURL: 'http://localhost:9870/bridge',
	initialUsername: 'admin',
	isIridium: false
};

function loadConfig(): Config {
	const result = loadSync('./config.json', 'application/json');
	if (!result) return defaultConfig;

	try {
		const parsed = JSON.parse(result);
		// if the administrator forgets some configuration variables
		return { ...defaultConfig, ...parsed };
	} catch (e) {
		return defaultConfig;
	}
}

const config = loadConfig();

export function getConfig() {
	return config;
}

export function useConfig() {
	return config;
}
