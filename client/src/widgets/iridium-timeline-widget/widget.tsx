import { Flex, View } from '@adobe/react-spectrum';
import { StepList } from './components/StepList';
import { useStateStepPoints } from './hooks/use-state-step-points';

export function Widget() {
	const points = useStateStepPoints();

	return (
		<View width="100%" height="100%" padding="size-100">
			<Flex
				width="100%"
				height="100%"
				direction="column"
				justifyContent="center"
				alignItems="center"
			>
				<StepList points={points} />
			</Flex>
		</View>
	);
}
