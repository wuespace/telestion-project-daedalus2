import { DataSample } from '../../../model/data-sample';

export type ExtendedSVGSVGElement = SVGSVGElement & {
	update(data: DataSample[]): void;
};
