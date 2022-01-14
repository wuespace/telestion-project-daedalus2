import { View } from '@adobe/react-spectrum';
import { useConsoleLog } from './use-console-log';
import { useRef } from 'react';
import { OverflowFix } from '@wuespace/telestion-client-common';

export interface ConsoleRendererProps {
	source: string;
	isScroll: boolean;
	showAllBoots: boolean;
}

export function ConsoleRenderer({
	source,
	isScroll,
	showAllBoots
}: ConsoleRendererProps) {
	const ref = useRef<HTMLPreElement>(null);
	useConsoleLog(source, ref, isScroll, showAllBoots);

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
