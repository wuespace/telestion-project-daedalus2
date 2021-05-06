const path = require('path');

module.exports = {
	plugins: [path.join(__dirname, 'src', 'plugins', 'mock-server-plugin.ts')]
};
