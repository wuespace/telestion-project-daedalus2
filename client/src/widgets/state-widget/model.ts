import { GenericProps } from '@wuespace/telestion-client-types';

export interface WidgetProps extends GenericProps {
	seedASource: 'SEED_HEARTBEAT' | 'SEED_SYSTEM_T';
	seedBSource: 'SEED_HEARTBEAT' | 'SEED_SYSTEM_T';
	ejectorSource: 'EJECTOR_HEARTBEAT' | 'EJECTOR_SYSTEM_T';
}
