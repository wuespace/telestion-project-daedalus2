import { Divider, Flex, Heading, View } from '@adobe/react-spectrum';
import { LoadingIndicator } from '@wuespace/telestion-client-common';
import SeedECAMSVG from './components/exported-ecam-svg';
import { WidgetProps } from './model';
import { useSvgVizPropsHelpers } from '../hooks/use-svg-viz-props-helpers';
import { useSeedEcamData } from './hooks/use-seed-ecam-data';
import { formatDeltaTime } from '../lib/format-delta-time';

const SERVO_THRESHOLD_VOLTAGE = 8.75;

export function Widget({ seed, voltThreshold }: WidgetProps) {
	const { valve, onOffSwitch, activeInactive } = useSvgVizPropsHelpers();
	const data = useSeedEcamData(seed);

	return (
		<View padding={'size-200'}>
			<Flex justifyContent="space-between">
				<Heading level={3} margin={0} marginBottom="size-200">
					Seed {seed.substr(4)} ECAM
				</Heading>
				<code>
					{data?.time ? formatDeltaTime(Date.now() - data.time) : 'Waiting'}
				</code>
			</Flex>
			<Divider size="S" />
			<View marginTop={'size-200'}>
				{/*@ts-ignore*/}
				<LoadingIndicator dependencies={[data]}>
					{data => (
						<>
							{data && (
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
									{...activeInactive(
										'rxsmInput',
										data.rxsmVolts > voltThreshold
									)}
									{...activeInactive('rxsmMainEffective', data.rxsmUsed)}
									rxsmVolts={data.rxsmVolts.toFixed(2) + ' V'}
									{...valve(
										'heater',
										data.heaterAllowed,
										data.rxsmVolts > voltThreshold
									)}
									/*heaterValveOffLine={''}
									heaterValveBG={inactive}
									{...activeInactive(
										'heaterValveBorder',
										data.rxsmVolts > voltThreshold
									)}
									{...activeInactive(
										'heaterValveOnLine',
										data.rxsmVolts > voltThreshold
									)}*/
									{...activeInactive(
										'heaterBlock',
										data.rxsmVolts > voltThreshold && data.heaterAllowed
									)}
									heaterAmps={
										data.heaterFault
											? '-Fault-'
											: `${data.heaterTemps.toFixed(1)} K`
									}
									{...activeInactive(
										'dcdcOutBlock',
										data.rail3V3Volts > voltThreshold
									)}
									dcdcOut1Volts={data.rail3V3Volts.toFixed(2) + ' V'}
									dcdcOut2Volts={data.rail5Volts.toFixed(2) + ' V'}
									{...onOffSwitch(
										'servoTrunk',
										data.mainBusVoltage <= SERVO_THRESHOLD_VOLTAGE,
										data.mainBusVoltage > voltThreshold
									)}
									{...activeInactive(
										'servoTrunk',
										data.mainBusVoltage <= SERVO_THRESHOLD_VOLTAGE &&
											data.mainBusVoltage > voltThreshold
									)}
									vccVolts={data.mainBusVoltage.toFixed(2) + ' V'}
									{...activeInactive(
										'vccBus',
										data.mainBusVoltage > voltThreshold
									)}
									sFinnenAmps={'0.0 mA'}
									st1Amps={'0.0 mA'}
									st2Amps={'0.0 mA'}
									st3Amps={'0.0 mA'}
									{...activeInactive(
										'sFinnenBlock',
										data.mainBusVoltage <= SERVO_THRESHOLD_VOLTAGE
									)}
									{...activeInactive(
										'st1Block',
										data.mainBusVoltage <= SERVO_THRESHOLD_VOLTAGE
									)}
									{...activeInactive(
										'st2Block',
										data.mainBusVoltage <= SERVO_THRESHOLD_VOLTAGE
									)}
									{...activeInactive(
										'st3Block',
										data.mainBusVoltage <= SERVO_THRESHOLD_VOLTAGE
									)}
								/>
							)}
						</>
					)}
				</LoadingIndicator>
			</View>
		</View>
	);
}
