import { useMemo } from 'react';
import { useSpectrumColor } from '@wuespace/telestion-client-common';
import { useD3 } from '../../hooks';

import { useDynamicData } from '../hooks';
import { Options } from '../model';
import { createNode } from '../lib';

export interface PGraphProps {
	width: number;
	height: number;
	options: Options;
}

export function PGraph({ width, height, options }: PGraphProps) {
	// dynamic color management
	const strokeSpectrumColors = useMemo(
		() => options.connections.map(connection => connection.stroke),
		[options]
	);
	const fillSpectrumColors = useMemo(
		() => options.connections.map(connection => connection.fill),
		[options]
	);
	const strokeColors = useSpectrumColor(strokeSpectrumColors);
	const fillColors = useSpectrumColor(fillSpectrumColors);

	const [element, node] = useD3(createNode, [
		width,
		height,
		options.connections,
		strokeColors,
		fillColors
	]);
	useDynamicData(node, options);

	return <div ref={element} />;
}
