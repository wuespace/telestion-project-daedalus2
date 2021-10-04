# Redis Connection

The classes in this package provide various options to connect to Redis.

## Supported/Required Redis Modules

- [`RedisTimeSeries`](https://oss.redis.com/redistimeseries/)

## Verticles in this package

### `de.wuespace.telestion.project.daedalus2.redis.RedisSaver`

Listens for data on `config.inAddress` and saves everything that "comes in" in
both a `latest/[eb-address]`key-value-field and numeric values (also if they're
children of objects) in a time series called`ts/[eb-address]`,
where `[eb-address]` is the `config.inAddress`.

#### Configuration

##### `conf.json` example

```json
{
	"name": "[REDIS] Redis Seed A SystemT Saver",
	"verticle": "de.wuespace.telestion.project.daedalus2.redis.RedisSaver",
	"magnitude": 1,
	"config": {
		"inAddress": "seed-a-systemt",
		"connectionString": "redis://redis",
		"reconnectAttempts": 10
	}
}
```

##### `config` fields

<dl>
<dt><code>inAddress: string</code></dt>
<dd>
The EventBus address the verticle listens to.
</dd>
<dt><code>connectionString: string</code></dt>
<dd>
	The connection string for the Redis server. 
	Cf. 
	<a href="https://vertx.io/docs/vertx-redis-client/java/#_connection_string">
		Vert.x Redis documentation
	</a>
</dd>
<dt><code>reconnectAttempts: number</code></dt>
<dd>
How often the Redis client attempts to reconnect when it gets disconnected.
</dd>
</dl>

### `de.wuespace.telestion.project.daedalus2.redis.RedisRequestHandler`

A handler for data requests (e.g., from a client).

Listens for requests on the channels specified in the configuration and then
answers with latest / time series data for requests on `requestLatestData` and
`requestTimeSeriesData`, respectively.

#### Latest Data Requests

The latest data requests must include a string array containing the requested
keys from the Redis DB.

The response format matches the request as an array containing the values for
the requested keys in their given order. If the verticle finds no data for a
given key, it puts `null` in the position of that key.

You can spawn multiple instances to handle several requests in parallel, if
necessary.

##### Example

###### Query

```json5
{
	fields: [
		"latest/systemT/batTemp",
		"latest/systemT"
	]
}
```

###### Response

```json5
[
	// "latest/systemT/batTemp"
	0.3,
	// "latest/systemT"
	{
		batTemp: 0.3,
		// [...]
	}
]
```

#### Time Series / Aggregation Data Requests

##### Example

###### Query

```json5
{
	fields: [
		{
			key: "ts/systemT/batTemp",
			// from the beginning
			from: "-",
			// to (including) the latest data
			to: "+",
			// 50s buckets
			bucketSize: 50000,
			aggregations: [
				"min",
				"max",
				"avg"
			]
		},
		{
			key: "ts/systemT/timeLocal",
			// from the beginning
			from: "-",
			// to (including) the latest data
			to: "+",
			// 50s buckets
			bucketSize: 50000,
			aggregations: [
				"count"
			]
		}
	]
}
```

###### Response

```json5
[
	// "ts/systemT/batTemp"
	[
		[
			// time
			1633271250000,
			// values
			{
				min: 0,
				avg: 3,
				max: 5.3
			}
		],
		[
			// time
			1633271350000,
			// values
			{
				min: 0,
				avg: 3.8,
				max: 5.6
			}
		],
		// [...]
	],
	// "ts/systemT/timeLocal"
	[
		[
			// time
			1633271250000,
			// values
			{
				count: 25
			}
		],
		// [...]
	],
]
```

#### Configuration

##### `conf.json` example

```json
{
	"name": "[REDIS] Redis Seed A SystemT Saver",
	"verticle": "de.wuespace.telestion.project.daedalus2.redis.RedisRequestHandler",
	"magnitude": 4,
	"config": {
		"requestTimeSeriesAddress": "request-time-series",
		"requestLatestAddress": "request-latest",
		"connectionString": "redis://redis",
		"reconnectAttempts": 10
	}
}
```

##### `config` fields

<dl>
<dt><code>requestLatestAddress: string</code></dt>
<dd>
The EventBus address the verticle accepts requests for latest (key/value 
based) data.
</dd>
<dt><code>requestTimeSeriesAddress: string</code></dt>
<dd>
The EventBus address the verticle accepts requests for aggregation (time 
series) data.
</dd>
<dt><code>connectionString: string</code></dt>
<dd>
	The connection string for the Redis server. 
	Cf. 
	<a href="https://vertx.io/docs/vertx-redis-client/java/#_connection_string">
		Vert.x Redis documentation
	</a>
</dd>
<dt><code>reconnectAttempts: number</code></dt>
<dd>
How often the Redis client attempts to reconnect when it gets disconnected.
</dd>
</dl>

## Q&A

### What's Redis?

Redis is an in-memory DB that provides very fast operations and is often used as
a caching layer.
