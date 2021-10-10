export function formatTitle(title: string) {
	return title.substr(7).split('/').join(' -> ');
}
