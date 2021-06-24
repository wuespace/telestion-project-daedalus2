import { useEffect } from 'react';
import hljs from 'highlight.js/lib/core';
import json from 'highlight.js/lib/languages/json';

/**
 * Imports and set up highlight.js for `<pre><code>Code</code></pre>` elements.
 */
export function useHljs(): void {
	useEffect(() => {
		hljs.registerLanguage('json', json);
	}, []);

	useEffect(() => {
		hljs.highlightAll();
	});
}
