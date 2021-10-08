export function completedChildren(obj: Record<string, boolean | any>): boolean {
	return Object.values(obj).every(child =>
		typeof child === 'boolean' ? child : completedChildren(child)
	);
}
