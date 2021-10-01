import { ReactNode, useRef } from 'react';

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

	const handleInput = () => {
		const file = inputRef.current?.files && inputRef.current.files[0];
		if (file) {
			const reader = new FileReader();
			reader.addEventListener('load', () => {
				if (reader.result) {
					onContent(reader.result.toString());
				}
			});
			reader.readAsText(file, 'utf-8');
		}
	};

	return (
		<input
			type="file"
			disabled={isDisabled}
			ref={inputRef}
			onInput={handleInput}
			style={{
				padding: '8px 0'
			}}
		>
			{children}
		</input>
	);
}
