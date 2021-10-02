import { ReactNode, useCallback, useRef } from 'react';

export interface FileInputProps {
	isDisabled?: boolean;
	onContent: (content: string) => void;
	children?: ReactNode;
}

export function FileInput({
	isDisabled = false,
	onContent,
	children
}: FileInputProps) {
	const inputRef = useRef<HTMLInputElement>(null);

	const handleInput = useCallback(() => {
		const file = inputRef.current?.files && inputRef.current.files[0];
		if (file) {
			const reader = new FileReader();
			reader.addEventListener('load', () => {
				if (reader.result) {
					onContent(reader.result.toString().split('\r\n').join('\n').trim());
					if (inputRef.current) inputRef.current.value = '';
				}
			});
			reader.readAsText(file, 'utf-8');
		}
	}, [onContent]);

	return (
		<input
			type="file"
			disabled={isDisabled}
			ref={inputRef}
			onChange={handleInput}
			style={{
				padding: '8px 0'
			}}
		>
			{children}
		</input>
	);
}
