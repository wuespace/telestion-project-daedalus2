import { useState } from 'react';
import { Divider, Flex, Heading, View } from '@adobe/react-spectrum';
import { LatLng, Map } from 'leaflet';
import {
	MapContainer,
	TileLayer,
	ScaleControl,
	CircleMarker
} from 'react-leaflet';

import { formatDeltaTime } from '../lib/format-delta-time';
import { Legend, FocusSelector } from './components';
import { useMapData } from './hooks';

import 'leaflet/dist/leaflet.css';
import 'leaflet/dist/leaflet.js';

// Esrange Space Center
export const mapCenter = new LatLng(67.887, 21.0851);

export function Widget() {
	const mapData = useMapData();
	const [map, setMap] = useState<Map>();

	return (
		<View width="100%" height="100%">
			<Flex direction="column" width="100%" height="100%">
				<Flex
					direction="row"
					alignItems="center"
					justifyContent="space-between"
					marginX="size-200"
				>
					<Heading level={3}>Map</Heading>
					<FocusSelector map={map} mapData={mapData} />
					<code>
						{formatDeltaTime(mapData.seedALastTime)} /{' '}
						{formatDeltaTime(mapData.seedBLastTime)}
					</code>
				</Flex>
				<Divider size="S" />

				<MapContainer
					style={{ flexGrow: 1 }}
					center={mapCenter}
					zoom={10}
					whenCreated={setMap}
				>
					<TileLayer
						attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
						url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
					/>
					<ScaleControl />

					{mapData.seedALatestPos && (
						<CircleMarker
							center={mapData.seedALatestPos}
							radius={10}
							color="#1379f3"
							dashArray="4"
							opacity={0.5}
						/>
					)}
					{mapData.seedBLatestPos && (
						<CircleMarker
							center={mapData.seedBLatestPos}
							radius={10}
							color="#fe9a2e"
							dashArray="4"
							opacity={0.5}
						/>
					)}

					{mapData.seedAValidPos && (
						<CircleMarker
							center={mapData.seedAValidPos}
							radius={10}
							color="#1379f3"
							fillOpacity={0.7}
						/>
					)}
					{mapData.seedBValidPos && (
						<CircleMarker
							center={mapData.seedBValidPos}
							radius={10}
							color="#fe9a2e"
							fillOpacity={0.7}
						/>
					)}
					<Legend map={map} />
				</MapContainer>
			</Flex>
		</View>
	);
}
