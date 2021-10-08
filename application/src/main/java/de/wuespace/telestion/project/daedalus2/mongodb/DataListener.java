package de.wuespace.telestion.project.daedalus2.mongodb;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.config.Config;
import de.wuespace.telestion.services.message.Address;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Listener that collects all incoming data configured in listeningAddresses and redirects them to be saved to the
 * MongoDatabaseService.
 */
public final class DataListener extends AbstractVerticle {
    private static record Configuration(@JsonProperty List<String> listeningAddresses) {
        private Configuration() {
            this(null);
        }
    }

    @Override
    public void start(Promise<Void> startPromise) {
        config = Config.get(forcedConfig, config(), Configuration.class);
        registerConsumers();
        startPromise.complete();
    }

    @Override
    public void stop(Promise<Void> stopPromise) {
        stopPromise.complete();
    }

    /**
     * This constructor supplies default options.
     *
     * @param listeningAddresses List of addresses that should be saved
     */
    public DataListener(List<String> listeningAddresses) {
        this.forcedConfig = new Configuration(listeningAddresses);
    }

    /**
     * If this constructor is used, settings have to be specified in the config file.
     */
    public DataListener() {
        this.forcedConfig = null;
    }

    private final Logger logger = LoggerFactory.getLogger(DataListener.class);
    private final Configuration forcedConfig;
    private Configuration config;

    /**
     * Mongo Database Service save address
     */
    private final String save = Address.incoming(MongoDatabaseSaver.class, "save");

    /**
     * Function to register consumers to the eventbus.
     */
    private void registerConsumers() {
        config.listeningAddresses().forEach(address -> {
            vertx.eventBus().consumer(address, document -> {
                    vertx.eventBus().publish(save, document.body());
            });
        });
    }
}
