import { GenericProps } from '@wuespace/telestion-client-types';

export interface WidgetProps extends GenericProps {
	/**
	 * The title of the note.
	 */
	title: string;

	/**
	 * The note of the note.
	 * Line breaks are reserved.
	 */
	content: string;
}
