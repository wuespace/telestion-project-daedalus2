import ReactDOM from 'react-dom';

import { App } from './components/app';
import { HljsLoader } from './components/hljs-loader';
import './index.scss';

// web components
import '@spectrum-web-components/field-label/sp-field-label.js';
import '@spectrum-web-components/theme/sp-theme.js';
import '@spectrum-web-components/sidenav/sp-sidenav';
import '@spectrum-web-components/sidenav/sp-sidenav-heading';
import '@spectrum-web-components/sidenav/sp-sidenav-item';

ReactDOM.render(
	<>
		<HljsLoader />
		<App />
	</>,
	document.getElementById('root')
);
