type r400700 = 400 | 500 | 600 | 700;
type r300700 = 300 | r400700;
type r400800 = r400700 | 800;
type r200700 = 200 | r300700;
type r200800 = r200700 | 800;
type r50900 = 50 | 75 | 100 | r200800 | 900;

export type StaticSpectrumColor =
	| 'black'
	| 'white'
	| `static-${'gray'}-${r50900}`
	| `static-${'red' | 'orange' | 'green' | 'fuchsia'}-${r400700}`
	| `static-${'chartreuse'}-${r300700}`
	| `static-${'purple'}-${r400800}`
	| `static-${
			| 'celery'
			| 'yellow'
			| 'magenta'
			| 'indigo'
			| 'seafoam'}-${r200700}`
	| `static-${'blue'}-${r200800}`;

export type SpectrumColor =
	| StaticSpectrumColor
	| `${
			| 'celery'
			| 'chartreuse'
			| 'yellow'
			| 'magenta'
			| 'fuchsia'
			| 'purple'
			| 'indigo'
			| 'seafoam'
			| 'red'
			| 'orange'
			| 'green'
			| 'blue'}-${r400700}`
	| `${'gray'}-${r50900}`;
