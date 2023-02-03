import { useState } from 'react';
import {
	ActionButton,
	Divider,
	Flex,
	Heading,
	View
} from '@adobe/react-spectrum';
import { LatLng, Map } from 'leaflet';
import {
	MapContainer,
	TileLayer,
	ScaleControl,
	CircleMarker
} from 'react-leaflet';
import { useCachedLatest } from '../hooks';
import { JsonSerializable } from '@wuespace/telestion-client-types';
import { formatDeltaTime } from '../lib/format-delta-time';

import 'leaflet/dist/leaflet.css';
import 'leaflet/dist/leaflet.js';

// Esrange Space Center
const mapCenter = new LatLng(67.887, 21.0851);

// see: https://spectrum.adobe.com/page/color-palette/
const SEED_A_COLOR = '#1379f3'; // Adobe Spectrum: blue-600-dark
const SEED_B_COLOR = '#fe9a2e'; // Adobe Spectrum: orange-900-dark

interface SeedPosition extends Record<string, JsonSerializable> {
	latitude: number;
	longitude: number;
	altitude: number;
}

export function Widget() {
	const [seedAPos, seedBPos] = useCachedLatest<SeedPosition[]>([
		'latest/seedA/iridium/payload',
		'latest/seedB/iridium/payload'
	]);
	const [seedATime, seedBTime] = useCachedLatest<number[]>([
		'latest-time/seedA/iridium/payload',
		'latest-time/seedB/iridium/payload'
	]);

	const [map, setMap] = useState<Map>();

	const panToSeedA = () => {
		if (!!map && !!seedAPos) {
			map.panTo([seedAPos.latitude, seedAPos.longitude]);
		}
	};

	const panToSeedB = () => {
		if (!!map && !!seedBPos) {
			map.panTo([seedBPos.latitude, seedBPos.longitude]);
		}
	};

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
					<Flex direction="row" alignItems="center" justifyContent="center">
						<ActionButton isDisabled={!seedAPos} onPress={panToSeedA}>
							Seed A
						</ActionButton>
						<ActionButton
							isDisabled={!seedBPos}
							onPress={panToSeedB}
							marginStart="size-100"
						>
							Seed B
						</ActionButton>
					</Flex>
					<code>
						{seedATime ? formatDeltaTime(Date.now() - seedATime) : 'Waiting'} /{' '}
						{seedBTime ? formatDeltaTime(Date.now() - seedBTime) : 'Waiting'}
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

					{seedAPos && (
						<CircleMarker
							center={[seedAPos.latitude, seedAPos.longitude]}
							radius={10}
							color={SEED_A_COLOR}
						/>
					)}
					{seedBPos && (
						<CircleMarker
							center={[seedBPos.latitude, seedBPos.longitude]}
							radius={10}
							color={SEED_B_COLOR}
						/>
					)}
				</MapContainer>
			</Flex>
		</View>
	);
}
