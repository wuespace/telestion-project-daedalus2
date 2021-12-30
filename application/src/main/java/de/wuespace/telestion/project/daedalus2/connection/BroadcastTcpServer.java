package de.wuespace.telestion.project.daedalus2.connection;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.services.connection.rework.ConnectionData;
import de.wuespace.telestion.services.connection.rework.SenderData;
import de.wuespace.telestion.services.connection.rework.tcp.TcpDetails;
import de.wuespace.telestion.services.connection.rework.tcp.TcpTimeouts;
import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
public class BroadcastTcpServer extends TelestionVerticle<BroadcastTcpServer.Configuration> implements WithEventBus {

	/**
	 * Configuration for the {@link BroadcastTcpServer} verticle.
	 *
	 * @param inAddress     the address the verticle listens to and sends messages to all connected clients
	 * @param outAddress    the address the verticle publishes messages received from a client
	 * @param host          the hostname of the server. Defaults to {@code "0.0.0.0"}.
	 *                      (which is equivalent to open)
	 * @param port          the port on which the server is listening
	 * @param clientTimeout the time until clients are kicked due to inactivity.
	 *                      Defaults to {@code 0}. ({@code 0} means no timeout)
	 */
	public record Configuration(
			@JsonProperty String inAddress,
			@JsonProperty String outAddress,
			@JsonProperty String host,
			@JsonProperty int port,
			@JsonProperty long clientTimeout
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, null, "0.0.0.0", 0, 0);
		}
	}

	public BroadcastTcpServer() {
		this.connections = new HashSet<>();
	}

	@Override
	public void onStart(Promise<Void> startPromise) {
		setDefaultConfig(new Configuration());
		register(getConfig().inAddress(), this::handleBroadcast, SenderData.class);
		setupServer().map(server -> (Void) null).onComplete(startPromise);
	}

	@Override
	public void onStop(Promise<Void> stopPromise) {
		if (server == null) {
			stopPromise.complete();
			return;
		}

		logger.info("Closing now on {}:{}", getConfig().host(), getConfig().port());
		server.close().onComplete(stopPromise);
	}

	private NetServer server;
	private final Set<NetSocket> connections;

	private Future<NetServer> setupServer() {
		// build server options based on configuration
		var options = new NetServerOptions();
		options.setHost(getConfig().host());
		options.setPort(getConfig().port());
		options.setIdleTimeout(
				getConfig().clientTimeout() == TcpTimeouts.NO_RESPONSES
						? (int) TcpTimeouts.NO_TIMEOUT
						: (int) getConfig().clientTimeout()
		);

		// setup server
		server = vertx.createNetServer(options);
		server.connectHandler(this::onConnected);
		server.exceptionHandler(handler ->
				logger.error("Unexpected error (Config: {})", getConfig(), handler.getCause())
		);
		return server.listen()
				.onSuccess(server -> logger.info("Successfully started. Running on {}:{}", getConfig().host(), server.actualPort()));
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
			publish(getConfig().outAddress(), new ConnectionData(buffer.getBytes(), new TcpDetails(host, port, packetId)));

			// disconnect if no responses allowed
			if (getConfig().clientTimeout() == TcpTimeouts.NO_RESPONSES) {
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
}
