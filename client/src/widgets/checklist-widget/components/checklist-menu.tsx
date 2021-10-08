import { ChecklistType } from '../checklist-type';
import React from 'react';

export function ChecklistMenu({
	items,
	currentPointer, // currently selected menu item
	menuItemPointer // pointer for this very menu item
}: {
	items: ChecklistType;
	currentPointer: string;
	menuItemPointer: string;
}): JSX.Element {
	return (
		<>
			<sp-sidenav-item
				label={'(Overview)'}
				value={menuItemPointer || '  '}
				selected={currentPointer === menuItemPointer || undefined}
			/>

			{Object.entries(items)
				.filter(item => typeof item[1] === 'object')
				.map(([childKey, childItems]) => [
					childKey,
					childItems,
					Object.values(childItems).every(item => typeof item === 'boolean')
				])
				.map(([childKey, childItems, simple]) =>
					simple ? (
						<sp-sidenav-item
							label={childKey}
							value={menuItemPointer + `/${childKey}`}
							selected={
								currentPointer === menuItemPointer + `/${childKey}` || undefined
							}
						/>
					) : (
						<sp-sidenav-item
							label={childKey}
							expanded={
								currentPointer.startsWith(menuItemPointer + `/${childKey}`) ||
								undefined
							}
						>
							<ChecklistMenu
								items={childItems as ChecklistType}
								currentPointer={currentPointer}
								menuItemPointer={menuItemPointer + `/${childKey}`}
							/>
						</sp-sidenav-item>
					)
				)}
		</>
	);
}
