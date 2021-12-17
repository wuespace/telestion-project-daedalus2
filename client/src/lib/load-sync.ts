export function loadSync(filePath: string, mimeType: string): string | null {
	const xmlhttp = new XMLHttpRequest();
	xmlhttp.open('GET', filePath, false);
	if (mimeType != null) {
		if (xmlhttp.overrideMimeType) {
			xmlhttp.overrideMimeType(mimeType);
		}
	}
	xmlhttp.send();
	if (xmlhttp.status === 200) {
		return xmlhttp.responseText;
	} else {
		return null;
	}
}
