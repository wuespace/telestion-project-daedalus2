import { Divider, Heading, View } from '@adobe/react-spectrum';
import { LoadingIndicator } from '@wuespace/telestion-client-common';
import SeedECAMSVG from './components/exported-ecam-svg';
import { WidgetProps } from './model';
import { useSvgVizPropsHelpers } from '../hooks/use-svg-viz-props-helpers';
import { useSeedEcamData } from './hooks/use-seed-ecam-data';

export function Widget({ seed, voltThreshold, ampsThreshold }: WidgetProps) {
	const { inactive, valve, onOffSwitch, activeInactive } =
		useSvgVizPropsHelpers();
	const data = useSeedEcamData(seed);

	return (
		<View padding={'size-200'}>
			<Heading level={3} margin={0} marginBottom="size-200">
				Seed {seed.substr(4)} ECAM
			</Heading>
			<Divider size="S" />
			<View marginTop={'size-200'}>
				{/*@ts-ignore*/}
				<LoadingIndicator dependencies={[data]}>
					{data => (
						<SeedECAMSVG
							{...valve(
								'bat1Main',
								data.bat1Allowed,
								data.bat1 > voltThreshold
							)}
							{...activeInactive('bat1Input', data.bat1 > voltThreshold)}
							{...activeInactive('bat1MainEffective', data.bat1Used)}
							bat1Volts={data.bat1.toFixed(2) + ' V'}
							{...valve(
								'bat2Main',
								data.bat2Allowed,
								data.bat2 > voltThreshold
							)}
							{...activeInactive('bat2Input', data.bat2 > voltThreshold)}
							{...activeInactive('bat2MainEffective', data.bat2Used)}
							bat2Volts={data.bat2.toFixed(2) + ' V'}
							{...valve(
								'rxsmMain',
								data.rxsmAllowed,
								data.rxsmVolts > voltThreshold
							)}
							{...activeInactive('rxsmInput', data.rxsmVolts > voltThreshold)}
							{...activeInactive('rxsmMainEffective', data.rxsmUsed)}
							rxsmVolts={data.rxsmVolts.toFixed(2) + ' V'}
							/* TODO: Add back in if suitable TM gets added
							{...valve(
								'heater',
								true,
								data.batHeatingAmps > ampsThreshold
							)} */
							heaterValveOffLine={''}
							heaterValveBG={inactive}
							{...activeInactive(
								'heaterValveBorder',
								data.rxsmVolts > voltThreshold
							)}
							{...activeInactive(
								'heaterValveOnLine',
								data.rxsmVolts > voltThreshold
							)}
							{...activeInactive('heaterBlock', data.rxsmVolts > voltThreshold)}
							heaterAmps={'-NoData-'}
							{...activeInactive(
								'dcdcOutBlock',
								data.rail3V3Volts > voltThreshold
							)}
							dcdcOut1Volts={data.rail3V3Volts.toFixed(2) + ' V'}
							dcdcOut2Volts={data.rail5Volts.toFixed(2) + ' V'}
							{...onOffSwitch(
								'servoTrunk',
								data.mainBusVoltage < 9.2,
								data.mainBusVoltage > voltThreshold
							)}
							{...activeInactive(
								'servoTrunk',
								data.mainBusVoltage < 9.2 && data.mainBusVoltage > voltThreshold
							)}
							vccVolts={data.mainBusVoltage.toFixed(2) + ' V'}
							{...activeInactive('vccBus', data.mainBusVoltage > voltThreshold)}
							sFinnenAmps={(data.fin_servo_amps * 1000).toPrecision(3) + ' mA'}
							st1Amps={
								(data.swashplate_servo1_amps * 1000).toPrecision(3) + ' mA'
							}
							st2Amps={
								(data.swashplate_servo2_amps * 1000).toPrecision(3) + ' mA'
							}
							st3Amps={
								(data.swashplate_servo3_amps * 1000).toPrecision(3) + ' mA'
							}
							{...activeInactive(
								'sFinnenBlock',
								data.fin_servo_amps > ampsThreshold
							)}
							{...activeInactive(
								'st1Block',
								data.swashplate_servo1_amps > ampsThreshold
							)}
							{...activeInactive(
								'st2Block',
								data.swashplate_servo2_amps > ampsThreshold
							)}
							{...activeInactive(
								'st3Block',
								data.swashplate_servo3_amps > ampsThreshold
							)}
						/>
					)}
				</LoadingIndicator>
			</View>
		</View>
	);
}
