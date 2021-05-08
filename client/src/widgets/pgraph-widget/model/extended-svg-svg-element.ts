import { DataSample } from '../../../model/data-sample';
import { GraphDimensions } from './graph-dimensions';

export type ExtendedSVGSVGElement = SVGSVGElement & {
	update(data: DataSample[][], dimensions: GraphDimensions): void;
};
