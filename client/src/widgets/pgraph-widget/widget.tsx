import { Heading } from '@adobe/react-spectrum';
import { BaseRendererProps } from '@wuespace/telestion-client-types';
import { WidgetProps } from './model';

export function Widget(options: BaseRendererProps<WidgetProps>) {
	return <Heading level={2}>pgraphWidget widget</Heading>;
}
