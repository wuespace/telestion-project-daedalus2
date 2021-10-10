import { useCallback, useEffect, useState } from 'react';
import {
	Checkbox,
	CheckboxGroup,
	Form,
	TextField,
	View
} from '@adobe/react-spectrum';
import { BaseConfigControlsProps } from '@wuespace/telestion-client-types';
import { WidgetProps } from '../model';
import { useEventBus } from '@wuespace/telestion-client-core';
import { formatTitle } from '../lib/format-title';

export function ConfigControls({
	currentProps,
	onUpdate
}: BaseConfigControlsProps<WidgetProps>) {
	const { eventBus, connectionState } = useEventBus();

	const [availableAddresses, setAvailableAddresses] = useState<string[]>([]);
	const [query, setQuery] = useState('');

	const requestAddresses = useCallback(() => {
		if (['connected', 'error'].includes(connectionState)) {
			eventBus?.send<string[]>('request-keys', `latest/*${query}*`, response =>
				setAvailableAddresses(response)
			);
		} else {
			throw new Error('Not connected');
		}
	}, [eventBus, connectionState, query, setAvailableAddresses]);

	useEffect(() => {
		requestAddresses();
	}, [requestAddresses]);

	let currentFields = currentProps.connections.map(v => v.address);
	return (
		<View paddingX="size-200" paddingBottom="size-200">
			<Form
				onSubmit={e => {
					e.preventDefault();
					requestAddresses();
				}}
			>
				<TextField
					value={query}
					onChange={setQuery}
					label="Query (Press 'Return' to submit)"
				/>
			</Form>

			{availableAddresses && (
				<CheckboxGroup
					orientation={'horizontal'}
					label="Fields"
					value={currentFields}
					onChange={e =>
						onUpdate({
							connections: e.map(e => ({ address: e, title: e, rps: 1 }))
						})
					}
				>
					{availableAddresses
						.sort((a, b) =>
							currentFields.includes(a)
								? -1
								: currentFields.includes(b)
								? 1
								: a.localeCompare(b)
						)
						.map(a => (
							<Checkbox value={a} key={a}>
								{formatTitle(a)}
							</Checkbox>
						))}
				</CheckboxGroup>
			)}
		</View>
	);
}
