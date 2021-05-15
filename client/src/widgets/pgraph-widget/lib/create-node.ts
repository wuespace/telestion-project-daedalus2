import * as d3 from 'd3';
import { DataSample } from '../../../model/data-sample';
import { ExtendedSVGSVGElement, GraphDimensions } from '../model';
import { ChartConnection } from '../model/chart-connection';

const margin = 20;

export function createNode(
	width: number,
	height: number,
	connections: ChartConnection[],
	strokeColors: string[],
	fillColors: string[]
): ExtendedSVGSVGElement {
	// build svg frame
	const chart = d3.create('svg').attr('width', width).attr('height', height);

	const backNodes: d3.Selection<SVGPathElement, undefined, null, undefined>[] =
		[];
	const lineNodes: d3.Selection<SVGPathElement, undefined, null, undefined>[] =
		[];

	for (let i = 0; i < connections.length; i++) {
		backNodes[i] = chart
			.append('path')
			.attr('fill', fillColors[i])
			.attr('opacity', 0.6);
	}

	for (let i = 0; i < connections.length; i++) {
		lineNodes[i] = chart
			.append('path')
			.attr('stroke', strokeColors[i])
			.attr('fill', 'rgba(0,0,0,0)')
			.attr('stroke-width', 2);
	}

	// x axis
	const xG = chart
		.append('g')
		.attr('transform', `translate(0, ${height - margin})`);

	// y axis
	const yG = chart.append('g');

	return Object.assign(chart.node(), {
		update(data: DataSample[][], dimensions: GraphDimensions) {
			// build axis maps
			const x = d3
				.scaleTime()
				.domain([dimensions.minX, dimensions.maxX])
				.range([margin * 2, width + margin]);

			const y = d3
				.scaleLinear()
				.domain([dimensions.minY, dimensions.maxY])
				.range([height - margin, margin * 0.5]);

			// build axis renderer
			const xAxis = (
				g: d3.Selection<SVGGElement, undefined, null, undefined>
			) =>
				g
					.attr('transform', `translate(0, ${y.range()[0]})`)
					.call(d3.axisBottom(x).tickSizeOuter(0));

			const yAxis = (
				g: d3.Selection<SVGGElement, undefined, null, undefined>
			) =>
				g
					.attr('transform', `translate(${x.range()[0]})`)
					.call(d3.axisLeft(y).ticks(3).tickSizeOuter(margin));

			// build graph renderer
			const backRender = d3
				.area<DataSample>()
				.curve(d3.curveLinear)
				.x(d => x(d.time))
				.y0(d => y(d.min))
				.y1(d => y(d.max));

			// average line renderer
			const lineRender = d3
				.line<DataSample>()
				.curve(d3.curveLinear)
				.x(d => x(d.time))
				.y(d => y(d.avg));

			// update all graph nodes with current data
			for (let i = 0; i < connections.length; i++) {
				// @ts-ignore
				backNodes[i].back = backNodes[i].datum(data[i]).attr('d', backRender);

				// @ts-ignore
				lineNodes[i].line = lineNodes[i].datum(data[i]).attr('d', lineRender);
			}

			yG.call(yAxis);
			xG.call(xAxis);
		}
	});
}
