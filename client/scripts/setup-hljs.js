#!/usr/bin/env node
/* eslint-disable strict */

// Copies the styles from the highlight.js package
// to the public folder to allow dynamic loading
// of light and dark styles for highlight.js

'use strict';

// Makes the script crash on unhandled rejections instead of silently
// ignoring them. In the future, promise rejections that are not handled will
// terminate the Node.js process with a non-zero exit code.
process.on('unhandledRejection', err => {
	console.error(err);
	throw err;
});

const fs = require('fs');
const path = require('path');

// change this for different themes
const lightTheme = 'github.css';
const darkTheme = 'github-dark.css';

// paths
const publicDir = path.join(process.cwd(), 'public');
const stylesDir = path.join(
	path.dirname(require.resolve('highlight.js')),
	'..',
	'styles'
);

const lightSrcPath = path.join(stylesDir, lightTheme);
const darkSrcPath = path.join(stylesDir, darkTheme);

const lightDestPath = path.join(publicDir, 'light.css');
const darkDestPath = path.join(publicDir, 'dark.css');

// final checks
if (!fs.existsSync(publicDir)) {
	console.error('The public directory does not exist.');
	process.exit(1);
}

if (!fs.existsSync(lightSrcPath)) {
	console.error(
		`Sorry, the style "${lightTheme}" does not exist in ${stylesDir}.`
	);
	process.exit(1);
}

if (!fs.existsSync(darkSrcPath)) {
	console.error(
		`Sorry, the style "${darkTheme}" does not exist in ${stylesDir}.`
	);
	process.exit(1);
}

fs.copyFileSync(lightSrcPath, lightDestPath);
fs.copyFileSync(darkSrcPath, darkDestPath);

console.log(`Highlight.js styles "${lightTheme}" and "${darkTheme}" applied`);
