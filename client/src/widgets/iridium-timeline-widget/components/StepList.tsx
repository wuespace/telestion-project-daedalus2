import { StepPoint } from '../model';
import '../widget.scss';
import { useDarkColorScheme } from '@wuespace/telestion-client-common';

export interface StepListProps {
	points: StepPoint[];
}

const backupPoint: StepPoint = {
	id: -1,
	label: '',
	isLeftConnected: false,
	isRightConnected: false,
	isActive: false,
	isDone: false,
	isCritical: false
};

function getLabelClasses(
	isDone: boolean,
	isActive: boolean,
	isDark: boolean,
	isCritical: boolean
): string {
	const darkCss = isDark ? 'dark' : '';
	const criticalCss = isCritical ? `${darkCss} critical` : darkCss;

	if (isActive) return `${criticalCss} steplist-text active`;
	if (isDone) return `${criticalCss} steplist-text done`;
	return `${criticalCss} steplist-text`;
}

function getConnectionClasses(
	isConnected: boolean,
	isDone: boolean,
	isActive: boolean,
	isDark: boolean,
	isCritical: boolean
): string {
	const darkCss = isDark ? 'dark' : '';
	const criticalCss = isCritical ? `${darkCss} critical` : darkCss;

	if (!isConnected) return darkCss; // it's not connected -> nothing to do

	if (isActive) return `${criticalCss} active`;
	if (isDone) return `${criticalCss} done`;
	return `${criticalCss} dashed`;
}

function getDotClasses(
	isDone: boolean,
	isActive: boolean,
	isDark: boolean,
	isCritical: boolean
): string {
	const darkCss = isDark ? 'dark' : '';
	const criticalCss = isCritical ? `${darkCss} critical` : darkCss;

	if (isActive) return `${criticalCss} dot active`;
	if (isDone) return `${criticalCss} dot done`;
	return `${criticalCss} dot`;
}

export function StepList({ points }: StepListProps) {
	const isDark = useDarkColorScheme();

	return (
		<>
			<div className="steplist-container">
				{points.map(point => (
					<div
						key={point.id}
						className={getLabelClasses(
							point.isDone,
							point.isActive,
							isDark,
							point.isCritical
						)}
					>
						{point.label}
					</div>
				))}
			</div>

			<div className="steplist-container">
				{points.map((point, index, list) => {
					const previous = list[index - 1] || backupPoint;
					const next = list[index + 1] || backupPoint;

					return (
						<div key={point.id} className="steplist-item">
							<span
								className={getConnectionClasses(
									point.isLeftConnected,
									point.isDone && previous.isDone,
									point.isActive && previous.isActive,
									isDark,
									point.isCritical || previous.isCritical
								)}
							/>
							<span
								className={getDotClasses(
									point.isDone,
									point.isActive,
									isDark,
									point.isCritical
								)}
							/>
							<span
								className={getConnectionClasses(
									point.isRightConnected,
									point.isDone && next.isDone,
									point.isActive && next.isActive,
									isDark,
									point.isCritical || next.isCritical
								)}
							/>
						</div>
					);
				})}
			</div>
		</>
	);
}
