import L, { Map } from 'leaflet';
import { useEffect } from 'react';

import './style.css';

export interface LegendProps {
	map?: Map;
}

export function Legend({ map }: LegendProps) {
	useEffect(() => {
		// @ts-ignore
		const legend = L.control({ position: 'bottomright' });

		legend.onAdd = () => {
			const div = L.DomUtil.create('div', 'info legend');

			const labels: string[] = [
				`<i style="background: rgba(19,121,243,0.6); border-color: #1379f3; border-style: solid"></i> Seed A (valid)`,
				`<i style="background: rgba(19,121,243,0.3); border-color: rgba(19,121,243,0.6); border-style: dashed"></i> Seed A (latest)`,
				`<i style="background: rgba(254,154,46,0.6); border-color: #fe9a2e; border-style: solid"></i> Seed B (valid)`,
				`<i style="background: rgba(254,154,46,0.3); border-color: rgba(254,154,46,0.6); border-style: dashed"></i> Seed B (latest)`
			];

			div.innerHTML = labels.join('<br>');
			return div;
		};

		if (map) {
			legend.addTo(map);
			return () => legend.remove();
		}
	}, [map]);

	return null;
}
