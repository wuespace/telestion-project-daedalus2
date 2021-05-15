import { formatDeltaTime } from './format-delta-time';

export type StatusType = 'neutral' | 'positive' | 'notice' | 'negative';

const baseClass = 'spectrum-StatusLight';
let version: string | undefined = undefined;

/**
 * Extracts the version string from a class name of a Spectrum StatusLight.
 * @param className - the class name of a Spectrum StatusLight
 * @returns the extracted version string
 */
function extractVersion(className: string): string {
	const parts = className.split('_');
	return parts[parts.length - 1];
}

/**
 * Update the CSS classes of a html node by replacing status types.
 * @param classList - the class list object of the html node
 * @param oldType - the old status type
 * @param newType - the new status type
 */
function updateCCSClasses(
	classList: DOMTokenList,
	oldType: StatusType,
	newType: StatusType
): StatusType {
	if (!version) {
		version = extractVersion(classList.value);
		console.log(`Found spectrum StatusLight version "${version}"`);
	}

	classList.replace(
		`_${baseClass}--${oldType}_${version}`,
		`_${baseClass}--${newType}_${version}`
	);
	return newType;
}

/**
 * Updates a `<div>` node returned from a Spectrum StatusLight in a Non-React way.
 * @param node - the `<div>` node
 * @param deltaTime - the time since the last update of the current value
 * @param oldType - the old status type
 * @param newType - the new status type
 */
export function updateStatusNode(
	node: HTMLDivElement,
	deltaTime: number,
	oldType: StatusType,
	newType: StatusType
): StatusType {
	node.innerText = formatDeltaTime(deltaTime);
	return updateCCSClasses(node.classList, oldType, newType);
}
