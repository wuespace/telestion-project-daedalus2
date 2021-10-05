import { GenericProps } from '@wuespace/telestion-client-types';

export interface WidgetProps extends GenericProps {
	/**
	 * The title of the widget.
	 */
	title: string;

	/**
	 * The eventbus channel the widget uses to request data and changes
	 * from the verticle.
	 */
	requestChannel: string;

	/**
	 * The eventbus channel the widget receives state updates from the verticle.
	 */
	notifyChannel: string;
}
