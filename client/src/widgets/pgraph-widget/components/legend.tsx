import { Content, Flex, View } from '@adobe/react-spectrum';
import { ChartConnection } from '../model/chart-connection';

export interface LegendProps {
	connections: ChartConnection[];
}

export function Legend({ connections }: LegendProps) {
	return (
		<Flex
			flexGrow={0}
			direction="row"
			alignItems="center"
			justifyContent="center"
			wrap="wrap"
			margin="size-100"
		>
			{connections.map(connection => (
				<Flex
					direction="row"
					alignItems="baseline"
					justifyContent="center"
					marginX="size-100"
					key={connection.address}
				>
					<View
						flexGrow={0}
						flexShrink={0}
						width="size-125"
						height="size-125"
						backgroundColor={connection.stroke}
						borderRadius="small"
					/>
					<Content marginStart="size-100">{connection.title}</Content>
				</Flex>
			))}
		</Flex>
	);
}
