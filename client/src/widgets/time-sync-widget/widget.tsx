import {
	AlertDialog,
	Button,
	DialogTrigger,
	Divider,
	Heading,
	Radio,
	RadioGroup,
	View
} from '@adobe/react-spectrum';
import { useRequest } from '@wuespace/telestion-client-core';
import { useCallback, useMemo, useState } from 'react';
import { TimeSyncRequest } from './time-sync-request';

export function Widget() {
	const requestTimeSync = useRequest<string>('request-time-sync');

	const [syncResponse, setSyncResponse] = useState<string | undefined>(
		undefined
	);

	const [target, setTarget] = useState<TimeSyncRequest['target'] | 'all'>(
		'all'
	);

	const performTimeSync = useCallback(
		(
			command: TimeSyncRequest['command'],
			target: TimeSyncRequest['target']
		) => {
			requestTimeSync(
				{
					command,
					target,
					className:
						'de.wuespace.telestion.project.daedalus2.timesync.TimeSyncRequest'
				},
				msg => {
					setSyncResponse(msg);

					setTimeout(() => {
						setSyncResponse(undefined);
					}, 5000);
				}
			);
		},
		[setSyncResponse, requestTimeSync]
	);

	const [forceCommand, forceTarget] = useMemo<
		[TimeSyncRequest['command'], TimeSyncRequest['target']]
	>(() => {
		if (target === 'all') return ['time forcefw', 'ejector'];
		else return ['time force', target];
	}, [target]);

	return (
		<View padding={'size-200'}>
			<Heading level={3} marginTop={0}>
				Time Widget
			</Heading>
			<Divider size="S" />
			{syncResponse && (
				<View
					backgroundColor={'positive'}
					padding={'size-200'}
					borderRadius={'regular'}
					marginTop={'size-200'}
				>
					Message <code>"{syncResponse}"</code> sent.
				</View>
			)}
			<p>Via the button below, you can perform a time synchronizaztion.</p>
			<DialogTrigger>
				<Button isDisabled={!!syncResponse} variant={'cta'}>
					Perform Time Sync&hellip;
				</Button>
				<AlertDialog
					variant="confirmation"
					title="Confirm Time Sync"
					primaryActionLabel="Perform Time Sync"
					cancelLabel="Cancel"
					onPrimaryAction={() => performTimeSync('time setfw', 'ejector')}
				>
					<p>Are you sure you want to perform a time sync?</p>
					<p>
						This sends the <code>CON_CMD</code>{' '}
						<code>"time setfw [d2-timestamp]"</code> to the ejector.
					</p>
				</AlertDialog>
			</DialogTrigger>
			<Heading level={4}>Force (Debug Mode Only!)</Heading>
			<p>
				<RadioGroup
					orientation={'horizontal'}
					label={'Target(/-s)'}
					value={target}
					onChange={s => setTarget(s as any)}
				>
					<Radio value={'all'}>(all)</Radio>
					<Radio value={'seedA'}>Seed A</Radio>
					<Radio value={'seedB'}>Seed B</Radio>
					<Radio value={'ejector'}>Ejector</Radio>
				</RadioGroup>
			</p>
			<DialogTrigger>
				<Button isDisabled={!!syncResponse} variant={'cta'}>
					Force Time Sync&hellip;
				</Button>
				<AlertDialog
					variant="confirmation"
					title="Confirm Time Force"
					primaryActionLabel="Perform Time Force"
					cancelLabel="Cancel"
					onPrimaryAction={() => performTimeSync(forceCommand, forceTarget)}
				>
					<p>Are you sure you want to perform a time sync?</p>
					<p>
						This sends <code>"{forceCommand} [d2-timestamp]"</code> to{' '}
						<code>{forceTarget}</code>
					</p>
				</AlertDialog>
			</DialogTrigger>
		</View>
	);
}
