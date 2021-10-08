import { get } from 'json-pointer';
import {
	Breadcrumbs,
	Button,
	Checkbox,
	Flex,
	Item
} from '@adobe/react-spectrum';
import { completedChildren } from '../lib/completed-children';
import React from 'react';

export function Checklist({
	setCurrentPointer,
	pointer,
	parentPointer,
	checklistState,
	dispatch
}: any): JSX.Element {
	const currentValue = get(checklistState, pointer);
	const currentKey = pointer.substr(parentPointer.length + 1);

	if (typeof currentValue === 'boolean')
		return (
			<Checkbox
				isSelected={currentValue}
				onChange={val => dispatch(val ? 'done' : 'reset', pointer)}
			>
				{currentKey}
			</Checkbox>
		);
	else {
		const keys = Object.keys(currentValue);

		return (
			<>
				<Flex direction="column" gap={'size-100'} flexGrow={1}>
					<Breadcrumbs onAction={a => setCurrentPointer(a)}>
						{pointer.split('/').map((item: string, index: number) => (
							<Item
								key={(pointer as string)
									.split('/')
									.slice(0, index + 1)
									.join('/')}
							>
								{item || 'Home'}
							</Item>
						))}
					</Breadcrumbs>
					<>
						{keys
							.map(key => ({ key, childPointer: pointer + `/${key}` }))
							.map(({ key, childPointer }) =>
								typeof currentValue[key] === 'boolean' ? (
									// @ts-ignore
									<Checklist
										setCurrentPointer={setCurrentPointer}
										pointer={childPointer}
										parentPointer={pointer}
										key={childPointer}
										dispatch={dispatch}
										checklistState={checklistState}
									/>
								) : (
									<Checkbox
										key={childPointer}
										onChange={() => setCurrentPointer(childPointer)}
										isSelected={completedChildren(currentValue[key])}
									>
										<i>(Category) {key}</i>
									</Checkbox>
								)
							)}
					</>
					<Button
						isHidden={pointer.trim() === parentPointer.trim()}
						variant={'cta'}
						alignSelf={'end'}
						isDisabled={!completedChildren(currentValue)}
						onPress={() => setCurrentPointer(parentPointer.trim())}
					>
						Complete and return&hellip;
					</Button>
				</Flex>
			</>
		);
	}
}
