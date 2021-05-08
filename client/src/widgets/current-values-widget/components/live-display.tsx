import { Content, Divider, Flex, Heading, Well } from '@adobe/react-spectrum';
import { Connection } from '../model';
import { DynamicDataRender } from './dynamic-data-render';

export interface LiveDisplayProps {
	connection: Connection;
}

export function LiveDisplay({ connection }: LiveDisplayProps) {
	return (
		<Well width="100%">
			<Flex direction="column">
				<Heading level={5} margin="size-50">
					{connection.title}
				</Heading>
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
					<DynamicDataRender connection={connection} />
				</Content>
			</Flex>
		</Well>
	);
}
