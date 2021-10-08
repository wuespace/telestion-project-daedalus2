import { useMemo } from 'react';
import {
	Button,
	Divider,
	Flex,
	Form,
	Heading,
	ProgressBar,
	Radio,
	RadioGroup,
	TextField,
	View
} from '@adobe/react-spectrum';
import { BaseRendererProps } from '@wuespace/telestion-client-types';

import { useAGpsState } from './use-a-gps-state';
import { useAGpsReducers } from './use-a-gps-reducers';
import { WidgetProps } from './model';
import { FileInput } from './file-input';

/**
 * Unpacks the packed content to data name and encoded data.
 * @param packed - the packed content to unpack
 * @param separator - splits data name and encoded data in the packed content
 * (defaults to `\n`)
 */
function splitContents(
	packed: string,
	separator = '\n'
): readonly [dataName: string, encodedData: string] {
	const index = packed.indexOf(separator);
	if (index < 0) {
		throw new Error('Separator not found in contents');
	}
	return [packed.slice(0, index), packed.slice(index + separator.length)];
}

export function Widget({ title, targets }: BaseRendererProps<WidgetProps>) {
	const targetKeys = useMemo(() => Object.keys(targets), [targets]);
	const { isTransmitting, dataName, target, progress } = useAGpsState();
	const { newData, newTarget, start, abort } = useAGpsReducers();

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading margin={0} level={3}>
					{title}
				</Heading>
			</Flex>
			<Divider size="M" marginTop="size-100" />
			<Form
				maxWidth="100%"
				onSubmit={e => {
					e.preventDefault();
				}}
				isRequired={true}
			>
				<FileInput
					onContent={content => newData(...splitContents(content))}
					isDisabled={isTransmitting}
				/>
				<TextField
					isRequired={true}
					label={'Uploaded A-GPS data'}
					value={dataName || 'nothing uploaded yet'}
					isDisabled
				/>
				<RadioGroup
					label="Target"
					value={target}
					onChange={newTarget}
					isRequired={true}
					isDisabled={isTransmitting}
				>
					{targetKeys.map(key => (
						<Radio key={key} value={key}>
							{targets[key]}
						</Radio>
					))}
				</RadioGroup>
				<Button
					variant={isTransmitting ? 'negative' : 'cta'}
					onPress={isTransmitting ? abort : start}
				>
					{isTransmitting ? 'Abort' : 'Transmit'}
				</Button>
			</Form>
			{isTransmitting && <ProgressBar label="Transmitting…" value={progress} />}
		</View>
	);
}
