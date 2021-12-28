import { Heading, View, Divider, Flex } from '@adobe/react-spectrum';
import { ReactElement } from 'react';

export interface StateDisplayProps {
	children: ReactElement | ReactElement[];
}

export function StateDisplay({ children }: StateDisplayProps) {
	return (
		<View padding="size-200" width="100%">
			<Flex direction="column" alignItems="start">
				<Heading level={3} marginTop={0}>
					State Machine State
				</Heading>
				<Divider size="M" />

				<View width="100%" overflow={'auto'}>
					<Flex direction="row" width="100%" minWidth={500} gap={'size-200'}>
						{children}
					</Flex>
				</View>
			</Flex>
		</View>
	);
}
