import { LatLng } from 'leaflet';
import { JsonSerializable } from '@wuespace/telestion-client-types';

// Esrange Space Center
export const mapCenter = new LatLng(67.887, 21.0851);

// see: https://spectrum.adobe.com/page/color-palette/
export const SEED_A_COLOR = '#1379f3'; // Adobe Spectrum: blue-600-dark
export const SEED_B_COLOR = '#fe9a2e'; // Adobe Spectrum: orange-900-dark

export interface SeedPosition extends Record<string, JsonSerializable> {
	latitude: number;
	longitude: number;
	altitude: number;
}
