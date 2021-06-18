/*
This file gets manipulated automatically using the tc-cli.

Please do not remove the // XXX_IMPORT_MARK comments or you will loose the ability to generate widgets automatically
using the tc-cli generate widget command.
 */

import { Widget } from '@wuespace/telestion-client-types';
import { widget as sampleWidget } from './sample-widget';
import { widget as pgraphWidget } from './pgraph-widget';
import { widget as currentValuesWidget } from './current-values-widget';
import { widget as placeholderWidget } from './placeholder-widget';
import { widget as cameraWidget } from './camera-widget';
import { widget as freitextTc } from './freitext-tc';
// IMPORT_INSERT_MARK

export const projectWidgets: Widget[] = [
	// ARRAY_FIRST_ELEMENT_INSERT_MARK
	freitextTc as Widget,
	cameraWidget,
	placeholderWidget,
	currentValuesWidget as Widget,
	pgraphWidget as Widget,
	sampleWidget
];
