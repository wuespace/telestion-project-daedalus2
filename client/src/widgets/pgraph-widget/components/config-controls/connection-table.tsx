import {
	ActionGroup,
	Item,
	Text,
	View,
	Flex,
	ActionButton
} from '@adobe/react-spectrum';
import {
	Cell,
	Column,
	Row,
	TableView,
	TableBody,
	TableHeader
} from '@react-spectrum/table';

import ChevronDown from '@spectrum-icons/workflow/ChevronDown';
import ChevronUp from '@spectrum-icons/workflow/ChevronUp';
import Add from '@spectrum-icons/workflow/Add';

import { ChartConnection } from '../../model/chart-connection';

export interface ConnectionTableProps {
	connections: ChartConnection[];
}

export function ConnectionTable({ connections }: ConnectionTableProps) {
	const actions = (
		<View padding="size-50">
			<ActionGroup isQuiet buttonLabelBehavior="hide">
				<Item key="up">
					<ChevronUp />
					<Text>Up</Text>
				</Item>
				<Item key="down">
					<ChevronDown />
					<Text>Down</Text>
				</Item>
			</ActionGroup>
		</View>
	);

	return (
		<Flex direction="column" gap="size-100" alignItems="end">
			<TableView aria-label="Connection Table">
				<TableHeader>
					<Column key="address">Channel</Column>
					<Column key="stroke">Stroke</Column>
					<Column key="fill">Fill</Column>
					<Column key="actions" hideHeader showDivider align="end" width={90}>
						Actions
					</Column>
				</TableHeader>
				<TableBody items={connections}>
					{connection => (
						<Row key={connection.id}>
							{columnKey => (
								<Cell>
									{columnKey === 'actions' ? actions : connection[columnKey]}
								</Cell>
							)}
						</Row>
					)}
				</TableBody>
			</TableView>
			<ActionButton aria-label="Add new entry" isQuiet>
				<Add />
			</ActionButton>
		</Flex>
	);
}
