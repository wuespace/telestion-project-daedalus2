import { ChannelAddress } from '@wuespace/telestion-client-types';

import { useD3 } from '../../hooks';
import { useDynamicData } from '../hooks';
import { Options } from '../model';
import { createNode } from './create-node';

export interface PGraphProps {
	width: number;
	height: number;
	address: ChannelAddress;
	options: Options;
}

export function PGraph({ width, height, address, options }: PGraphProps) {
	const [element, node] = useD3(createNode, [width, height, options]);
	useDynamicData(node, address, options);

	return <div ref={element} />;
}
