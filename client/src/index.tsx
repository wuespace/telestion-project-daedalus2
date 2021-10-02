import { StrictMode } from 'react';
import ReactDOM from 'react-dom';

import { App } from './components/app';
import { HljsLoader } from './components/hljs-loader';
import './index.scss';

// web components
import '@spectrum-web-components/field-label/sp-field-label.js';
import '@spectrum-web-components/theme/sp-theme.js';

ReactDOM.render(
	<StrictMode>
		<HljsLoader />
		<App />
	</StrictMode>,
	document.getElementById('root')
);
