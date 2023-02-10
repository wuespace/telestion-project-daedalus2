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
import { widget as commonSeedTcsWidget } from './common-seed-tcs-widget';
import { widget as freitextTcWidget } from './freitext-tc-widget';
import { widget as noteWidget } from './note-widget';
import { widget as stateWidget } from './state-widget';
import { widget as aGpsWidget } from './a-gps-widget';
import { widget as seedEcam } from './seed-ecam-widget';
import { widget as checklistWidget } from './checklist-widget';
import { widget as manualTimeSeriesRequestWidget } from './manual-time-series-request-widget';
import { widget as simpleGraphWidget } from './simple-graph-widget';
import { widget as timeSyncWidget } from './time-sync-widget';
import { widget as batHeatingTcWidget } from './bat-heating-tc-widget';
import { widget as stateChangeWidget } from './state-change-widget';
import { widget as radioSilenceWidget } from './radio-silence-widget';
import { widget as ejectorCameraWidget } from './ejector-camera-widget';
import { widget as tcConsoleWidget } from './tc-console-widget';
import { widget as timelineWidget } from './timeline-widget';
import { widget as iridiumMapWidget } from './iridium-map-widget';
// IMPORT_INSERT_MARK

export const projectWidgets: Widget[] = [
	// ARRAY_FIRST_ELEMENT_INSERT_MARK
	iridiumMapWidget,
	timelineWidget,
	tcConsoleWidget,
	ejectorCameraWidget,
	radioSilenceWidget,
	stateChangeWidget as Widget,
	batHeatingTcWidget,
	timeSyncWidget,
	simpleGraphWidget as Widget,
	manualTimeSeriesRequestWidget,
	checklistWidget,
	seedEcam as Widget,
	aGpsWidget as Widget,
	noteWidget as Widget,
	freitextTcWidget as Widget,
	commonSeedTcsWidget as Widget,
	placeholderWidget,
	pgraphWidget as Widget,
	sampleWidget,
	currentValuesWidget as Widget,
	stateWidget as Widget
];
