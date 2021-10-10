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
import { widget as freitextTcWidget } from './freitext-tc-widget';
import { widget as noteWidget } from './note-widget';
import { widget as stateWidget } from './state-widget';
import { widget as aGpsWidget } from './a-gps-widget';
import { widget as seedEcam } from './seed-ecam-widget';
import { widget as checklistWidget } from './checklist-widget';
import { widget as manualTimeSeriesRequestWidget } from './manual-time-series-request-widget';
// IMPORT_INSERT_MARK

export const projectWidgets: Widget[] = [
	// ARRAY_FIRST_ELEMENT_INSERT_MARK
	manualTimeSeriesRequestWidget,
	checklistWidget,
	seedEcam as Widget,
	aGpsWidget as Widget,
	noteWidget as Widget,
	freitextTcWidget as Widget,
	cameraWidget as Widget,
	placeholderWidget,
	pgraphWidget as Widget,
	sampleWidget,
	currentValuesWidget as Widget,
	stateWidget as Widget
];
