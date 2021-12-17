import { loadSync } from '../lib/load-sync';

export interface Config {
	initialServerURL: string;
	initialUsername: string;
}

export const defaultConfig: Config = {
	initialServerURL: 'http://localhost:9870/bridge',
	initialUsername: 'admin'
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

export function useConfig() {
	return config;
}
