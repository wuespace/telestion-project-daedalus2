import { Content, Divider, Flex, Heading, Well } from '@adobe/react-spectrum';
import { JsonSerializable } from '@wuespace/telestion-client-types';
import { formatDeltaTime } from '../../lib/format-delta-time';
import { formatTitle } from '../lib/format-title';

export interface LiveDisplayProps {
	value?: JsonSerializable;
	time?: number;
	title: string;
}

export function LiveWidget({ value, time, title }: LiveDisplayProps) {
	return (
		<Well width="100%">
			<Flex direction="column">
				<Flex direction="row" justifyContent="space-between">
					<Heading level={5} margin="size-50">
						{formatTitle(title).split('->').slice(1).join('->').trim()}
					</Heading>
					{/*<StatusLight variant="neutral">*/}
					<code>{time ? formatDeltaTime(Date.now() - time) : 'Waiting'}</code>
					{/*</StatusLight>*/}
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
					<div>{value?.toString() || '-'}</div>
				</Content>
			</Flex>
		</Well>
	);
}
