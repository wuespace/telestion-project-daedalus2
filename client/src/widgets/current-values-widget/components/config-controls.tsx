import { useCallback, useEffect, useState } from 'react';
import {
	Checkbox,
	CheckboxGroup,
	Form,
	SearchField,
	View
} from '@adobe/react-spectrum';
import { BaseConfigControlsProps } from '@wuespace/telestion-client-types';
import { WidgetProps } from '../model';
import { useEventBus } from '@wuespace/telestion-client-core';

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
			<p>
				Please select the current keys whose values get displayed in the widget.
			</p>
			<p>
				To narrow down the list, you can enter a query into the search bar and
				press the return key to search. Queries automatically get pre- and
				post-fixed with a wildcard and wildcards are supported using an asterisk{' '}
				<code>*</code>. Thus, you could write something like{' '}
				<code>seedA*acc*x</code> to quickly get to all accelerometer x-Axis
				values of <code>seedA</code>.{' '}
				<b>Please note that queries are case-sensitive!</b>
			</p>
			<p>
				Keys are in the format{' '}
				<code>source/MAVLINK_MESSAGE_TYPE/decodedMavlinkFieldName</code>.
				Available sources are <code>seedA</code>, <code>seedB</code> and{' '}
				<code>ejector</code>
			</p>
			<Form
				onSubmit={e => {
					e.preventDefault();
					requestAddresses();
				}}
			>
				<SearchField
					defaultValue={query}
					onSubmit={setQuery}
					placeholder={
						'Key name query (case-sensitive, supports "*" wildcards)'
					}
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
								{a.split('/').slice(1).join('/')}
							</Checkbox>
						))}
				</CheckboxGroup>
			)}
		</View>
	);
}
