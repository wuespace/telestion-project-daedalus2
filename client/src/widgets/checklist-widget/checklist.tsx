import { Checkbox, CheckboxGroup } from '@adobe/react-spectrum';
import { ListPoint } from './use-checklist-state';

export interface ChecklistProps {
	points: ListPoint[];
	onChange: (index: number, newState: boolean) => void;
}

export function Checklist({ points, onChange }: ChecklistProps) {
	const checkedKeys = points
		.filter(point => point.isChecked)
		.map(point => `${point.key}`);

	const handle = (selectedKeys: string[]) => {
		points.forEach(point => {
			const lastState = point.isChecked;
			const newState = selectedKeys.includes(`${point.key}`);
			if (lastState !== newState) onChange(point.key, newState);
		});
	};

	return (
		<CheckboxGroup
			value={checkedKeys}
			onChange={handle}
			isEmphasized
			marginTop="size-100"
		>
			{points.map(point => (
				<Checkbox
					value={`${point.key}`}
					isRequired
					validationState={
						!checkedKeys.includes(`${point.key}`) ? 'invalid' : undefined
					}
				>
					{point.title}
				</Checkbox>
			))}
		</CheckboxGroup>
	);
}
