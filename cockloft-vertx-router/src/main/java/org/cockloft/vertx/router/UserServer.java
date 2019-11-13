package org.cockloft.vertx.router;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;

public class UserServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        router.route("/test", HttpMethod.GET).handle((context)->{
           context.getResponse().end("hello test1");
        });
        router.route("/test2", HttpMethod.GET).handle((context)->{
            context.getResponse().end("hello test2");
        });
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router).listen(8080);

    }
}
