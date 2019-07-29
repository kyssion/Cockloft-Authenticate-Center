package com.cockloft.server;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMain {

    private static final Logger logger = LoggerFactory.getLogger(ServerMain.class);

    public static void main(String[] args) {
        int port = 8080;
        Vertx vertx = Vertx.vertx();
        HttpServerOptions options = new HttpServerOptions()
                .setMaxWebsocketFrameSize(1000000)
                .setLogActivity(true);
        HttpServer httpServer = vertx.createHttpServer(options);

        httpServer.requestHandler(req -> {
            
        });

        httpServer.listen(port, res -> {
            if (res.succeeded()) {
                logger.info("cockloft server starting , listen port : {}", port);
            } else {
                logger.error("run cockloft server failt , msg : {}", res.cause().getMessage());
            }
        });
    }
}
