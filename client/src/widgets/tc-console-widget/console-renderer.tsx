import { View } from '@adobe/react-spectrum';
import { useConsoleLog } from './use-console-log';
import { useRef } from 'react';
import { OverflowFix } from '@wuespace/telestion-client-common';

export interface ConsoleRendererProps {
	source: string;
	isScroll: boolean;
}

export function ConsoleRenderer({ source, isScroll }: ConsoleRendererProps) {
	const ref = useRef<HTMLPreElement>(null);
	useConsoleLog(source, ref, isScroll);

	return (
		<View height="100%" paddingY="size-100">
			<OverflowFix backgroundColor="gray-300">
				<code>
					<pre style={{ margin: 0 }} ref={ref} />
				</code>
			</OverflowFix>
		</View>
	);
}
