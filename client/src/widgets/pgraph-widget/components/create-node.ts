import * as d3 from 'd3';
import { DataSample } from '../../../model/data-sample';
import { ExtendedSVGSVGElement, Options } from '../model';

const margin = 25;
//const tickInterval = 500;

export function createNode(
	width: number,
	height: number,
	options: Options
): ExtendedSVGSVGElement {
	const { fill, minimum, maximum } = options;
	// build svg frame
	const chart = d3.create('svg').attr('width', width).attr('height', height);

	// x axis
	const xG = chart
		.append('g')
		.attr('transform', `translate(0, ${height - margin})`);

	// average path
	let avgPath = chart.append('path').attr('stroke', fill).attr('fill', fill);

	// y axis
	const yG = chart.append('g');

	return Object.assign(chart.node(), {
		update(data: DataSample[]) {
			// determine new axis domains
			const xDomain = d3.extent(data.map(d => d.timeStamp)) as [number, number];
			const yDomain = [minimum, maximum];

			// build axis maps
			const x = d3
				.scaleTime()
				.domain(xDomain)
				.range([margin * 2, width + margin]);

			const y = d3
				.scaleLinear()
				.domain(yDomain)
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
					.call(d3.axisLeft(y).ticks(3).tickSizeOuter(margin))
					.call(g => g.style('fill', 'white'))
					.call(g => g.select('.domain').style('stroke', 'white'))
					.call(g => g.selectAll('.tick > text').attr('dx', -margin * 0.2))
					.call(g =>
						g
							.selectAll('.tick > line')
							.attr('x1', -margin * 0.2)
							.attr('x2', -margin * 0.4)
					);

			// average line renderer
			const avgLine = d3
				.line<DataSample>()
				.curve(d3.curveLinear)
				.x(d => x(d.timeStamp))
				.y(d => y(d.avg));

			// apply renderer to dom elements
			// @ts-ignore
			avgPath = avgPath
				.datum(data)
				.attr('transform', 'translate(0)')
				.attr('d', avgLine);

			yG.call(yAxis);
			avgPath.attr(
				'transform',
				`translate(${x(data[0].timeStamp) - 2 * margin})`
			);
			xG.call(xAxis);
		}
	});
}
