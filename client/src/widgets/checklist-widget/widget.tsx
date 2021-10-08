import { Divider, Flex, Heading, View } from '@adobe/react-spectrum';
import { LoadingIndicator } from '@wuespace/telestion-client-common';
import { useChannelLatest, useEventBus } from '@wuespace/telestion-client-core';
import React, { useCallback, useEffect, useRef, useState } from 'react';
import { ChecklistType } from './checklist-type';
import { ChecklistMenu } from './components/checklist-menu';
import { Checklist } from './components/checklist';

export function Widget() {
	const eb = useEventBus();
	const checklistState = useChannelLatest('checklist-state');

	const dispatch = useCallback(
		(op: string, ...params: string[]) => {
			eb.eventBus?.publish('checklist-dispatch', {
				op,
				params,
				className:
					'de.wuespace.telestion.project.daedalus2.checklist.ChecklistCommand'
			});
		},
		[eb.eventBus]
	);

	useEffect(() => {
		dispatch('get');
	}, [dispatch]);

	const [pointer, setPointer] = useState('');

	const ref = useRef<HTMLElement>(null);

	useEffect(() => {
		if (ref.current) {
			ref.current.addEventListener('change', evt => {
				const value: string = (evt.target as any).value;
				setPointer(value.trim());
			});
		}
		// doesn't work without `ref.current` as dependency
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [ref.current, setPointer]);

	return (
		<View padding={'size-200'}>
			<Heading level={3} marginTop={0}>
				Checklists
			</Heading>

			<Divider size="S" />
			{/* @ts-ignore */}
			<LoadingIndicator dependencies={[checklistState]}>
				{checklistState => (
					<>
						<Flex marginTop={'size-200'} gap={'size-200'}>
							<View width={'200px'}>
								<sp-sidenav
									style={{ width: '100%' }}
									value={pointer}
									variant="multilevel"
									ref={ref}
								>
									<ChecklistMenu
										items={checklistState as ChecklistType}
										currentPointer={pointer}
										menuItemPointer={''}
									/>
								</sp-sidenav>
							</View>
							<Checklist
								checklistState={checklistState}
								pointer={pointer}
								parentPointer={
									pointer.split('/').slice(0, -1).join('/') || '  '
								}
								setCurrentPointer={setPointer}
								dispatch={dispatch}
							/>
						</Flex>
					</>
				)}
			</LoadingIndicator>
		</View>
	);
}
