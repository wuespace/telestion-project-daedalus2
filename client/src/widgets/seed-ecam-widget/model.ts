import { GenericProps } from '@wuespace/telestion-client-types';

export interface WidgetProps extends GenericProps {
	/**
	 * The threshold of voltage that gets counted as "non-zero"
	 */
	voltThreshold: number;

	/**
	 * The threshold of Ampere that gets counted as "non-zero"
	 */
	ampsThreshold: number;

	seed: 'seedA' | 'seedB';
}
