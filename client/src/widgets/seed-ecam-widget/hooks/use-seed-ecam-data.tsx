import { useCachedLatest } from '../../hooks';

export function useSeedEcamData(seed: 'seedA' | 'seedB') {
	const [systemT] = useCachedLatest<Record<string, any>[]>([
		`latest/${seed}/SEED_SYSTEM_T`
	]);

	const data = systemT && {
		// batHeatingAmps: Number.NaN,
		fin_servo_amps: systemT.adc_measurements_cop[0] / 1000,
		swashplate_servo1_amps: systemT.adc_measurements_cop[1] / 1000,
		swashplate_servo2_amps: systemT.adc_measurements_cop[2] / 1000,
		swashplate_servo3_amps: systemT.adc_measurements_cop[3] / 1000,
		rxsmAllowed: (systemT?.bat_allowed & 0b001) > 0,
		rxsmUsed: (systemT?.bat_used & 0b001) > 0,
		bat1Allowed: (systemT?.bat_allowed & 0b010) > 0,
		bat1Used: (systemT?.bat_used & 0b010) > 0,
		bat2Allowed: (systemT?.bat_allowed & 0b100) > 0,
		bat2Used: (systemT?.bat_used & 0b100) > 0,
		bat1: systemT?.adc_measurements_cop[7] / 1000,
		bat2: systemT?.adc_measurements_cop[6] / 1000,
		rxsmVolts: systemT?.adc_measurements_sbc[0] / 1000,
		rail3V3Volts: systemT.adc_measurements_sbc[1] / 1000,
		rail5Volts: systemT.adc_measurements_sbc[7] / 1000
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
