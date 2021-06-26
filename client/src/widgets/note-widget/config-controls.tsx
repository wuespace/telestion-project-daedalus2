import { useCallback } from 'react';
import { Form, TextArea, TextField, View } from '@adobe/react-spectrum';
import { BaseConfigControlsProps } from '@wuespace/telestion-client-types';
import { WidgetProps } from './model';

export function ConfigControls({
	currentProps,
	onUpdate
}: BaseConfigControlsProps<WidgetProps>) {
	const { title, content } = currentProps;

	const updateTitle = useCallback(
		(value: string) => onUpdate({ title: value }),
		[onUpdate]
	);

	const updateContent = useCallback(
		(value: string) => onUpdate({ content: value }),
		[onUpdate]
	);

	return (
		<View paddingX="size-200" paddingBottom="size-200">
			<Form maxWidth="100%" isRequired>
				<TextField
					autoFocus={true}
					label="Title"
					placeholder="The widget title"
					value={title}
					onChange={updateTitle}
				/>
				<TextArea
					width="100%"
					label="Content"
					placeholder="The widget content"
					value={content}
					onChange={updateContent}
				/>
			</Form>
		</View>
	);
}
