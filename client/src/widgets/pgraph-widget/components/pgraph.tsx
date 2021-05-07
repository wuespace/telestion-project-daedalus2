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
	const [element, node] = useD3(createNode, [width, height, options]);
	useDynamicData(node, options);

	return <div ref={element} />;
}
