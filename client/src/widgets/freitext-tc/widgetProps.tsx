import { GenericProps } from '@wuespace/telestion-client-types';

export interface WidgetProps extends GenericProps {
	targetLabels: Record<string, string>;
}
