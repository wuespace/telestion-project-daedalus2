import { Divider, Flex, Heading, View } from '@adobe/react-spectrum';
import { BaseRendererProps } from '@wuespace/telestion-client-types';
import { WidgetProps } from './model';

export function Widget({ title, content }: BaseRendererProps<WidgetProps>) {
	const lines = content ? content.split('\n') : [];

	const isEmpty = !content;

	return (
		<View padding="size-200">
			<Flex direction="column">
				<Heading level={3} margin={0} marginBottom="size-200">
					{title || 'Default title'}
				</Heading>
				<Divider size="S" />
				<View>
					{isEmpty ? (
						<p>
							<em>No content available</em>
						</p>
					) : (
						lines.map(line => <p>{line}</p>)
					)}
				</View>
			</Flex>
		</View>
	);
}
