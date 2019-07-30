package com.cockloft.server;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


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
            MultiMap multiMap = req.params();
            req.path();
            req.absoluteURI();
            req.uri();
            req.method();

            req.bodyHandler(buff -> {
                System.out.println("sdfsdf");
                System.out.println(buff.toString());
            });
            req.response().end("dfsdfsdf");
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
