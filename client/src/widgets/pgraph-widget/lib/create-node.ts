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
	const { fill, period, minimum, maximum } = options;
	// build svg frame
	const chart = d3.create('svg').attr('width', width).attr('height', height);

	// x axis
	const xG = chart
		.append('g')
		.attr('transform', `translate(0, ${height - margin})`);

	let backPath = chart.append('path').attr('fill', '#5e5e5e');

	// minimum path
	// let minPath = chart
	// 	.append('path')
	// 	//.attr('shape-rendering', 'optimizeSpeed')
	// 	.attr('stroke', 'rgba(31,31,31,0)')
	// 	.attr('fill', '#00000000')
	// 	.attr('stroke-width', 0.5);

	// maximum path
	// let maxPath = chart
	// 	.append('path')
	// 	//.attr('shape-rendering', 'optimizeSpeed')
	// 	.attr('stroke', 'rgba(0,0,0,0)')
	// 	.attr('fill', '#00000000')
	// 	.attr('stroke-width', 0.5);

	// average path
	let avgPath = chart
		.append('path')
		//.attr('shape-rendering', 'optimizeSpeed')
		.attr('stroke', 'rgb(255,255,255)')
		.attr('fill', 'rgba(0,0,0,0)')
		.attr('stroke-width', 0.8);

	// y axis
	const yG = chart.append('g');

	return Object.assign(chart.node(), {
		update(data: DataSample[]) {
			const currentDate = Date.now();
			// determine new axis domains
			const xDomain = [currentDate - period, currentDate];
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
					.call(d3.axisLeft(y).ticks(3).tickSizeOuter(margin));
			// .call(g => g.selectAll('.tick > text').attr('dx', -margin * 0.2))
			// .call(g =>
			// 	g
			// 		.selectAll('.tick > line')
			// 		.attr('x1', -margin * 0.2)
			// 		.attr('x2', -margin * 0.4)
			// );

			const backRender = d3
				.area<DataSample>()
				.curve(d3.curveLinear)
				.x(d => x(d.timeStamp))
				.y0(d => y(d.min))
				.y1(d => y(d.max));

			// average line renderer
			const avgLineRender = d3
				.line<DataSample>()
				.curve(d3.curveLinear)
				.x(d => x(d.timeStamp))
				.y(d => y(d.avg));

			// minimum line renderer
			// const minLineRender = d3
			// 	.line<DataSample>()
			// 	.curve(d3.curveLinear)
			// 	.x(d => x(d.timeStamp))
			// 	.y(d => y(d.min));

			// maximum line renderer
			// const maxLineRender = d3
			// 	.line<DataSample>()
			// 	.curve(d3.curveLinear)
			// 	.x(d => x(d.timeStamp))
			// 	.y(d => y(d.max));

			// apply renderer to dom elements
			// @ts-ignore
			backPath = backPath.datum(data).attr('d', backRender);

			// @ts-ignore
			// minPath = minPath
			// 	.datum(data)
			// 	.attr('d', minLineRender);

			// @ts-ignore
			// maxPath = maxPath
			// 	.datum(data)
			// 	.attr('d', maxLineRender);

			// @ts-ignore
			avgPath = avgPath.datum(data).attr('d', avgLineRender);

			yG.call(yAxis);
			xG.call(xAxis);
		}
	});
}
