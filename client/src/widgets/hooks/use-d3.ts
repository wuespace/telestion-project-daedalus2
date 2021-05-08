import { MutableRefObject, RefObject, useEffect, useRef } from 'react';
import * as d3 from 'd3';

type ReturnType<T> = readonly [
	/**
	 * The reference which should be attached to the actual `div` element.
	 */
	divElement: RefObject<HTMLDivElement>,
	/**
	 * Optional reference to the d3 node to further interact with it.
	 */
	d3Node: MutableRefObject<T | undefined>
];

/**
 * Creates a d3 node and attaches it to the registered `div` element.
 * @param createNode - the function that generates a d3 node
 * @param createArgs - additional arguments for the generator function (e.g. `width`, `height`)
 * @returns a tuple with the `div` element reference and the generated d3 node
 */
export function useD3<
	D3Node extends SVGSVGElement,
	Args extends readonly any[]
>(createNode: (...args: Args) => D3Node, createArgs: Args): ReturnType<D3Node> {
	const element = useRef<HTMLDivElement>(null);
	const store = useRef<D3Node>();

	useEffect(() => {
		// create and store
		const node = createNode(...createArgs);
		store.current = node;

		// attach node to body dom
		d3.select(element.current).append(() => node);
		// detach/remove created node from dom on re-render
		return () => node.remove();
	}, [createNode, createArgs]);

	return [element, store];
}
