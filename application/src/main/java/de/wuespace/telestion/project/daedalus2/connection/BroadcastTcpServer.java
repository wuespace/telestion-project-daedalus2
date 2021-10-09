package de.wuespace.telestion.project.daedalus2.connection;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.services.connection.rework.ConnectionData;
import de.wuespace.telestion.services.connection.rework.SenderData;
import de.wuespace.telestion.services.connection.rework.tcp.TcpDetails;
import de.wuespace.telestion.services.connection.rework.tcp.TcpTimeouts;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class BroadcastTcpServer extends AbstractVerticle {
	private static final Logger logger = LoggerFactory.getLogger(BroadcastTcpServer.class);

	/**
	 * Configuration for the {@link BroadcastTcpServer} verticle.
	 *
	 * @param inAddress     the address the verticle listens to and sends messages to all connected clients
	 * @param outAddress    the address the verticle publishes messages received from a client
	 * @param host          the hostname of the server. Defaults to {@code "0.0.0.0"}.
	 *                      (which is equivalent to open)
	 * @param port          the port the server is listen
	 * @param clientTimeout the time until clients are kicked due to inactivity.
	 *                      Defaults to {@code 0}. ({@code 0} means no timeout)
	 */
	public record Configuration(@JsonProperty String inAddress, @JsonProperty String outAddress,
								@JsonProperty String host, @JsonProperty int port,
								@JsonProperty long clientTimeout) implements JsonMessage {
		public Configuration() {
			this(null, null, "0.0.0.0", 0, 0);
		}
	}

	private Configuration config;
	private NetServer server;
	private Set<NetSocket> connections;

	public BroadcastTcpServer() {
		this(null);
	}

	public BroadcastTcpServer(Configuration forced) {
		this.config = forced;
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		config = Config.get(config, new Configuration(), config(), Configuration.class);
		connections = new HashSet<>();

		// build server options based on configuration
		var options = new NetServerOptions();
		options.setHost(config.host());
		options.setPort(config.port());
		options.setIdleTimeout(
				config.clientTimeout() == TcpTimeouts.NO_RESPONSES
						? (int) TcpTimeouts.NO_TIMEOUT
						: (int) config.clientTimeout()
		);

		// setup server
		server = vertx.createNetServer(options);
		server.connectHandler(this::onConnected);
		server.exceptionHandler(handler ->
				logger.error("Unexpected error (Config: {})", config.json(), handler)
		);
		server.listen(handler ->
				complete(handler, startPromise, result ->
						logger.info("Successfully started. Running on {}:{}", config.host(), result.actualPort())
				)
		);

		// connect to eventbus
		vertx.eventBus().consumer(config.inAddress(), raw -> {
			if (!JsonMessage.on(SenderData.class, raw, this::handleBroadcast)) {
				logger.warn("Received invalid message type. Packet will be dropped.");
			}
		});

		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) {
		if (server != null) {
			// try to gracefully close connections with every client
			connections.forEach(socket -> socket.close(handler -> {
				if (handler.failed()) {
					stopPromise.fail(handler.cause());
				}
			}));

			String host = config.host();
			int port = config.port();
			logger.info("Closing now on {}:{}", host, port);
			server.close();
		}

		// Wait for all Tcp connections to be closed successfully or fail in the process
		vertx.setTimer(Duration.ofSeconds(2).toMillis(), handler -> stopPromise.tryComplete());
	}

	public Configuration getConfig() {
		return config;
	}

	private void onConnected(NetSocket socket) {
		var host = socket.remoteAddress().host();
		var port = socket.remoteAddress().port();

		logger.info("Connection established with {}:{}", host, port);
		connections.add(socket);

		// configure new socket connection
		var counter = new AtomicInteger(0);
		socket.handler(buffer -> {
			var packetId = counter.getAndIncrement();

			logger.debug("New message received from {}:{} with id={}", host, port, packetId);
			vertx.eventBus().publish(config.outAddress, new ConnectionData(
					buffer.getBytes(), new TcpDetails(host, port, packetId)).json()
			);

			// disconnect if no responses allowed
			if (config.clientTimeout() == TcpTimeouts.NO_RESPONSES) {
				socket.close();
			}
		});
		socket.exceptionHandler(error -> {
			logger.error("Unexpected error from {}:{}. Closing connection", host, port, error);
			connections.remove(socket);
		});
		socket.closeHandler(handler -> {
			logger.info("Closing connection with {}:{}", host, port);
			connections.remove(socket);
		});
	}

	private void handleBroadcast(SenderData data) {
		var message = data.rawData();

		connections.forEach(socket -> {
			var host = socket.remoteAddress().host();
			var port = socket.remoteAddress().port();

			logger.debug("Sending packet to {}:{}", host, port);
			if (socket.writeQueueFull()) {
				logger.error("Write queue full for {}:{}. Packet will be dropped.", host, port);
				return;
			}
			socket.write(Buffer.buffer(message));
		});
	}

	/**
	 * Completes a promise based on the success of a {@link AsyncResult}.
	 * If it was successful a handler will be called.
	 *
	 * @param result  the result which is observed
	 * @param promise the promise which will be completed
	 * @param handler the handler which will be executed on a successful result
	 * @param <T>     the type of the result
	 */
	private static <T> void complete(AsyncResult<T> result, Promise<?> promise, Handler<T> handler) {
		if (result.failed()) {
			promise.fail(result.cause());
			return;
		}
		handler.handle(result.result());
		promise.tryComplete();
	}
}
