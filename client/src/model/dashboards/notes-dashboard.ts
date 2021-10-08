import { Dashboard } from '@wuespace/telestion-client-types';

export const notesDashboard: Dashboard = {
	title: 'Notes',
	columns: 12,
	rows: 12,
	widgets: [
		{
			id: 'notes-0',
			widgetName: 'noteWidget',
			width: 6,
			height: 6,
			initialProps: {
				title: 'Note 1'
			}
		},
		{
			id: 'notes-1',
			widgetName: 'checklistWidget',
			width: 6,
			height: 6,
			initialProps: {
				title: 'Note 2'
			}
		},
		{
			id: 'notes-2',
			widgetName: 'manualTimeSeriesRequestWidget',
			width: 6,
			height: 6,
			initialProps: {
				title: 'Note 3'
			}
		},
		{
			id: 'notes-3',
			widgetName: 'noteWidget',
			width: 6,
			height: 6,
			initialProps: {
				title: 'Note 4'
			}
		}
	]
};
