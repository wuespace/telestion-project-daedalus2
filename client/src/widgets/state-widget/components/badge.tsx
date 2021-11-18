import { ReactNode } from 'react';
import { View } from '@adobe/react-spectrum';

export interface BadgeProps {
	isSpecial?: boolean;
	children: ReactNode;
}

export function Badge({ isSpecial = false, children }: BadgeProps) {
	return (
		<View
			borderWidth={'thick'}
			borderColor={isSpecial ? 'red-500' : 'gray-400'}
			borderRadius={'regular'}
			paddingX={'size-100'}
			paddingY={'size-50'}
		>
			{children}
		</View>
	);
}
