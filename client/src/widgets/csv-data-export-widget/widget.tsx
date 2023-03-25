import {
	Divider,
	Flex,
	Heading,
	Radio,
	RadioGroup,
	View,
	Button
} from '@adobe/react-spectrum';
import { useState } from 'react';
import { useRequest } from '@wuespace/telestion-client-core';
import { saveAs } from 'file-saver';

import {
	CSVData,
	CSVDataRequest,
	requestChannel
} from '../../model/csv-data-exporter';

export function Widget() {
	const [target, setTarget] = useState<'seedA' | 'seedB'>('seedA');
	const [count, setCount] = useState(0);
	const request = useRequest<CSVData>(requestChannel);

	const handle = () => {
		const requestDate = new Date().toISOString().replaceAll(':', '');
		const requestMsg: CSVDataRequest = {
			target,
			className:
				'de.wuespace.telestion.project.daedalus2.mongodb.message.CSVDataRequest'
		};

		request(requestMsg, response => {
			setCount(response.count);
			const blob = new Blob([response.content], {
				type: 'text/csv;charset=utf-8'
			});
			saveAs(blob, `iridium-messages-${target}-${requestDate}.csv`);
		});
	};

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading level={3} margin={0} marginBottom="size-200">
					CSV Data Exporter
				</Heading>
				<Divider size="S" />
				<View paddingTop="size-100">
					<Flex direction="row" justifyContent="space-between" gap="size-100">
						<RadioGroup
							label="Target"
							orientation="horizontal"
							labelPosition="side"
							value={target}
							onChange={value => setTarget(value as 'seedA' | 'seedB')}
						>
							<Radio value="seedA">Seed A</Radio>
							<Radio value="seedB">Seed B</Radio>
						</RadioGroup>
						<div>Message count: {count}</div>
					</Flex>
					<Button variant="cta" onPress={handle}>
						Download iridium messages
					</Button>
				</View>
			</Flex>
		</View>
	);
}
