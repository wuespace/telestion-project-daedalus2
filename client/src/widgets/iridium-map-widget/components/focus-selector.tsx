import { Key, useEffect, useState } from 'react';
import { Map } from 'leaflet';
import { Selection } from '@react-types/shared';
import { ActionGroup, Item } from '@adobe/react-spectrum';
import Target from '@spectrum-icons/workflow/Target';

import { MapData } from '../hooks';

export interface FocusSelectorProps {
	map?: Map;
	mapData: MapData;
}

const emptySet = new Set<Key>([]);

export function FocusSelector({ map, mapData }: FocusSelectorProps) {
	const [focusSel, setFocusSel] = useState<Selection>(emptySet);

	useEffect(() => {
		if (map) {
			const handler = () => setFocusSel(emptySet);
			map.on('dragstart', handler);
			return () => map.off('dragstart', handler);
		}
		return () => {};
	}, [map]);

	useEffect(() => {
		if (typeof focusSel === 'object') {
			if (focusSel.has('seedALatest') && mapData.seedALatestPos) {
				map?.panTo(mapData.seedALatestPos);
			} else if (focusSel.has('seedBLatest') && mapData.seedBLatestPos) {
				map?.panTo(mapData.seedBLatestPos);
			} else if (focusSel.has('seedAValid') && mapData.seedAValidPos) {
				map?.panTo(mapData.seedAValidPos);
			} else if (focusSel.has('seedBValid') && mapData.seedBValidPos) {
				map?.panTo(mapData.seedBValidPos);
			}
		}
	}, [focusSel, mapData, map]);

	return (
		<ActionGroup
			density="compact"
			selectionMode="single"
			overflowMode="collapse"
			summaryIcon={<Target />}
			onSelectionChange={setFocusSel}
			selectedKeys={focusSel}
			isEmphasized
			isQuiet
		>
			<Item key="seedALatest">Seed A (latest)</Item>
			<Item key="seedAValid">Seed A (valid)</Item>
			<Item key="seedBLatest">Seed B (latest)</Item>
			<Item key="seedBValid">Seed B (valid)</Item>
		</ActionGroup>
	);
}
