import { useCachedLatest } from '../../hooks';

export function useSeedEcamData(seed: 'seedA' | 'seedB') {
	const [seedHeartbeat] = useCachedLatest<Record<string, any>[]>([
		`latest/${seed}/SEED_HEARTBEAT`
	]);
	const [time] = useCachedLatest<number[]>([
		`latest-time/${seed}/SEED_HEARTBEAT`
	]);

	const data = seedHeartbeat && {
		time,
		// batHeatingAmps: Number.NaN,
		fin_servo_amps: seedHeartbeat.adc_measurements_cop[0] / 1000,
		swashplate_servo1_amps: seedHeartbeat.adc_measurements_cop[1] / 1000,
		swashplate_servo2_amps: seedHeartbeat.adc_measurements_cop[2] / 1000,
		swashplate_servo3_amps: seedHeartbeat.adc_measurements_cop[3] / 1000,
		rxsmAllowed: (seedHeartbeat?.bat_status & 0b001) > 0,
		rxsmUsed: (seedHeartbeat?.bat_status & 0b1000) > 0,
		bat1Allowed: (seedHeartbeat?.bat_status & 0b010) > 0,
		bat1Used: (seedHeartbeat?.bat_status & 0b010000) > 0,
		bat2Allowed: (seedHeartbeat?.bat_status & 0b100) > 0,
		bat2Used: (seedHeartbeat?.bat_status & 0b100000) > 0,
		bat1: seedHeartbeat?.adc_measurements_cop[7] / 1000,
		bat2: seedHeartbeat?.adc_measurements_cop[6] / 1000,
		rxsmVolts: seedHeartbeat?.adc_measurements_sbc[0] / 1000,
		rail3V3Volts: seedHeartbeat.adc_measurements_sbc[1] / 1000,
		rail5Volts: seedHeartbeat.adc_measurements_sbc[7] / 1000,
		heaterAllowed: (seedHeartbeat?.bat_status & 0b1000000) > 0,
		heaterFault: seedHeartbeat?.bat_heater_fault > 0,
		heaterTemps: seedHeartbeat?.adc_measurements_cop[4] / 100 - 273.15
	};

	// TODO: Incorporate bat_used into mainBusVoltage calculation
	const mainBusVoltage =
		data &&
		Math.max(
			data.bat1Allowed ? data.bat1 : 0,
			data.bat2Allowed ? data.bat2 : 0,
			data.rxsmAllowed ? data.rxsmVolts : 0
		);

	return data && mainBusVoltage !== undefined
		? ({ ...data, mainBusVoltage } as typeof data & {
				mainBusVoltage: typeof mainBusVoltage;
		  })
		: undefined;
}
