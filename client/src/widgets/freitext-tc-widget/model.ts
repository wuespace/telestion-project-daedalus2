import { GenericProps } from '@wuespace/telestion-client-types';

export interface WidgetProps extends GenericProps {
	title: string;
	channel: string;
	targets: Record<string, string>;
}
