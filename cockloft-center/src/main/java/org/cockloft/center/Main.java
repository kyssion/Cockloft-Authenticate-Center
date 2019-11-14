package org.cockloft.center;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import org.cockloft.vertx.router.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        VertxOptions vertxOptions = new VertxOptions();
        Vertx vertx = Vertx.vertx(vertxOptions);
        Router router = Router.router(vertx);

        HttpServerOptions httpServerOptions = new HttpServerOptions();
        HttpServer server = vertx.createHttpServer(httpServerOptions);
        server.requestHandler(router).listen(8080,(res)->{

        });
    }
}
