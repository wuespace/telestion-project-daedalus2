import { Dashboard } from '@wuespace/telestion-client-types';

export const notesDashboard: Dashboard = {
	title: 'Checklists/Notes',
	columns: 2,
	rows: 21,
	widgets: [
		{
			id: 'notes-0',
			widgetName: 'stateWidget',
			width: 1,
			height: 10,
			initialProps: {
				seedASource: 'SEED_HEARTBEAT',
				seedBSource: 'SEED_HEARTBEAT',
				ejectorSource: 'EJECTOR_HEARTBEAT'
			}
		},
		{
			id: 'notes-1',
			widgetName: 'checklistWidget',
			width: 1,
			height: 10,
			initialProps: {
				title: 'Note 2'
			}
		},
		{
			id: 'notes-2',
			widgetName: 'noteWidget',
			width: 1,
			height: 10,
			initialProps: {
				title: 'Your notes',
				content: 'Telestion is beautiful!'
			}
		},
		{
			id: 'notes-3',
			widgetName: 'noteWidget',
			width: 1,
			height: 10,
			initialProps: {
				title: 'More notes'
			}
		},
		{
			id: 'notes-4',
			widgetName: 'timelineWidget',
			width: 2,
			height: 1,
			initialProps: {}
		}
	]
};
