export interface ConsoleRendererProps {
	target: string;
}

export function ConsoleRenderer({ target }: ConsoleRendererProps) {
	return <div>Console Target: {target}</div>;
}
