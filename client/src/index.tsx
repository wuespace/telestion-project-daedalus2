import { StrictMode } from 'react';
import ReactDOM from 'react-dom';

import { App } from './components/app';
import { HljsLoader } from './components/hljs-loader';
import './index.scss';

ReactDOM.render(
	<StrictMode>
		<HljsLoader />
		<App />
	</StrictMode>,
	document.getElementById('root')
);
