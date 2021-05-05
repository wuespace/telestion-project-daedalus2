import * as d3 from 'd3';
import { DataSample } from '../../../model/data-sample';
import { ExtendedSVGSVGElement, Options } from '../model';

export function createNode(
	width: number,
	height: number,
	options: Options
): ExtendedSVGSVGElement {
	// build svg frame
	const chart = d3.create('svg').attr('width', width).attr('height', height);

	return Object.assign(chart.node(), {
		update(data: DataSample[]) {
			return data;
		}
	});
}
