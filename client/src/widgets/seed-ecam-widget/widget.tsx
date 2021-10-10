import { Divider, Heading, View } from '@adobe/react-spectrum';
import { useSpectrumColor } from '@wuespace/telestion-client-common';
import { useCachedLatest } from '../hooks';
import SeedECAMSVG from './exported-ecam-svg';
import { WidgetProps } from './model';

export function Widget({ seed, voltThreshold, ampsThreshold }: WidgetProps) {
	const [systemT] = useCachedLatest<Record<string, any>[]>([
		`latest/${seed}/SEED_SYSTEM_T`
	]);

	const data = {
		availableStatus: {
			className:
				'de.wuespace.telestion.project.daedalus2.messages.AvailableStatus',
			baroAvail: true,
			servoAmpsAvail: true,
			batTempAvail: true,
			imuAccAvail: true,
			imuGyroAvail: true,
			tachoRotAvail: true,
			vacuumBaroAvail: true,
			voltsAvail: true
		},
		batHeatingAmps: Number.NaN,

		lidarServo: {
			className: 'de.wuespace.telestion.project.daedalus2.messages.LidarServo',
			lidarServoOn: true,
			lidarServoOpen: true
		},
		servoAmps: {
			swashplate_servo1_amps: Number.NaN,
			fin_servo_amps: Number.NaN,
			swashplate_servo3_amps: Number.NaN,
			className: 'de.wuespace.telestion.project.daedalus2.messages.ServoAmps',
			swashplate_servo2_amps: Number.NaN
		},
		systemConfig: {
			className:
				'de.wuespace.telestion.project.daedalus2.messages.SystemConfig',
			bat1Allowed: (systemT?.bat_allowed & 0b001) > 0,
			bat2Allowed: (systemT?.bat_allowed & 0b010) > 0,
			rxsmAllowed: (systemT?.bat_allowed & 0b100) > 0
		},
		volts: {
			bat1_volts: Number.NaN,
			bat2_volts: Number.NaN,
			rxsmVolts: Number.NaN,
			rail3V3Volts: Number.NaN,
			className: 'de.wuespace.telestion.project.daedalus2.messages.Volts'
		}
	};

	const [activeColor, inactive, positive, orange] = useSpectrumColor([
		'gray-900',
		'gray-500',
		'green-400',
		'orange-400'
	]);

	const valve = (component: string, allow: boolean, active: boolean) => ({
		[`${component}ValveBG`]: allow ? positive : orange,
		[`${component}ValveBorder`]: allow && active ? activeColor : inactive,
		[`${component}ValveOnLine`]: !allow ? '' : active ? activeColor : inactive,
		[`${component}ValveOffLine`]: allow ? '' : inactive,
		[`${component}ValveOut`]: allow && active ? activeColor : inactive
	});

	const onOffSwitch = (component: string, on: boolean, active: boolean) => ({
		[`${component}SwitchOnLine`]: !on ? '' : active ? activeColor : inactive,
		[`${component}SwitchOffLine`]: on ? '' : active ? activeColor : inactive
	});

	const activeInactive = (component: string, active: boolean) => ({
		[component]: active ? activeColor : inactive
	});

	const diff = (number1: number, number2: number) =>
		Math.abs(number1 - number2);

	const mainBusVoltage = Math.max(
		data.systemConfig.bat1Allowed ? data.volts.bat1_volts : 0,
		data.systemConfig.bat2Allowed ? data.volts.bat2_volts : 0,
		data.systemConfig.rxsmAllowed ? data.volts.rxsmVolts : 0
	);

	return (
		<View padding={'size-200'}>
			<Heading level={3} margin={0} marginBottom="size-200">
				Seed {seed.substr(4)} ECAM
			</Heading>
			<Divider size="S" />
			<View marginTop={'size-200'}>
				<SeedECAMSVG
					{...valve(
						'bat1Main',
						data.systemConfig.bat1Allowed,
						data.volts.bat1_volts > voltThreshold
					)}
					{...activeInactive(
						'bat1Input',
						data.volts.bat1_volts > voltThreshold
					)}
					{...activeInactive(
						'bat1MainEffective',
						diff(mainBusVoltage, data.volts.bat1_volts) < voltThreshold
					)}
					bat1Volts={data.volts.bat1_volts.toFixed(2) + ' V'}
					{...valve(
						'bat2Main',
						data.systemConfig.bat2Allowed,
						data.volts.bat2_volts > voltThreshold
					)}
					{...activeInactive(
						'bat2Input',
						data.volts.bat2_volts > voltThreshold
					)}
					{...activeInactive(
						'bat2MainEffective',
						diff(mainBusVoltage, data.volts.bat2_volts) < voltThreshold
					)}
					bat2Volts={data.volts.bat2_volts.toFixed(2) + ' V'}
					{...valve(
						'rxsmMain',
						data.systemConfig.rxsmAllowed,
						data.volts.rxsmVolts > voltThreshold
					)}
					{...activeInactive('rxsmInput', data.volts.rxsmVolts > voltThreshold)}
					{...activeInactive(
						'rxsmMainEffective',
						diff(mainBusVoltage, data.volts.rxsmVolts) < voltThreshold
					)}
					rxsmVolts={data.volts.rxsmVolts.toFixed(2) + ' V'}
					{...valve(
						'heater',
						true /* todo: woher? */,
						data.batHeatingAmps > ampsThreshold
					)}
					heaterValveBG={inactive}
					heaterBlock={
						data.batHeatingAmps > ampsThreshold ? activeColor : inactive
					}
					heaterAmps={data.batHeatingAmps.toFixed(2) + ' A'}
					{...activeInactive(
						'dcdcOutBlock',
						data.volts.rail3V3Volts > voltThreshold
					)}
					dcdcOut1Volts={data.volts.rail3V3Volts.toFixed(2) + ' V'}
					dcdcOut2Volts={'TBD'}
					{...onOffSwitch(
						'servoTrunk',
						mainBusVoltage < 9.2,
						mainBusVoltage > voltThreshold
					)}
					{...activeInactive(
						'servoTrunk',
						mainBusVoltage < 9.2 && mainBusVoltage > voltThreshold
					)}
					vccVolts={mainBusVoltage.toFixed(2) + ' V'}
					{...activeInactive('vccBus', mainBusVoltage > voltThreshold)}
					sFinnenAmps={
						(data.servoAmps.fin_servo_amps * 1000).toPrecision(3) + ' mA'
					}
					st1Amps={
						(data.servoAmps.swashplate_servo1_amps * 1000).toPrecision(3) +
						' mA'
					}
					st2Amps={
						(data.servoAmps.swashplate_servo2_amps * 1000).toPrecision(3) +
						' mA'
					}
					st3Amps={
						(data.servoAmps.swashplate_servo3_amps * 1000).toPrecision(3) +
						' mA'
					}
					{...activeInactive(
						'sFinnenBlock',
						data.servoAmps.fin_servo_amps > ampsThreshold
					)}
					{...activeInactive(
						'st1Block',
						data.servoAmps.swashplate_servo1_amps > ampsThreshold
					)}
					{...activeInactive(
						'st2Block',
						data.servoAmps.swashplate_servo2_amps > ampsThreshold
					)}
					{...activeInactive(
						'st3Block',
						data.servoAmps.swashplate_servo3_amps > ampsThreshold
					)}
				/>
			</View>
		</View>
	);
}
