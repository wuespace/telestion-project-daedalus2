import {
	Content,
	Divider,
	Flex,
	Heading,
	StatusLight,
	Well
} from '@adobe/react-spectrum';
import { Connection } from '../model';
import { useLiveData } from '../hooks';

export interface LiveDisplayProps {
	connection: Connection;
}

export function LiveWidget({ connection }: LiveDisplayProps) {
	const { valueNode, statusNode } = useLiveData(connection);

	return (
		<Well width="100%">
			<Flex direction="column">
				<Flex direction="row" justifyContent="space-between">
					<Heading level={5} margin="size-50">
						{connection.title}
					</Heading>
					<StatusLight ref={statusNode} variant="neutral">
						Waiting
					</StatusLight>
				</Flex>
				<Divider size="S" />
				<Content
					UNSAFE_style={{
						fontSize: '1.5rem',
						fontFamily: 'monospace',
						overflow: 'hidden',
						whiteSpace: 'nowrap'
					}}
					marginX="size-50"
				>
					<div ref={valueNode}>-</div>
				</Content>
			</Flex>
		</Well>
	);
}
