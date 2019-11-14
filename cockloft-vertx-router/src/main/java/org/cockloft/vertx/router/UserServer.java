package org.cockloft.vertx.router;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;

public class UserServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        router.route("/test", HttpMethod.GET).handle((context)->{
            context.getResponse().setChunked(true);
            context.getResponse().write("hello test1");
            context.next();
        }).handle((context)->{
            context.getResponse().end("test111");
            int i = 1/0;
        }).errorHandle((ex)->{
            System.out.println(ex.toString());
        });
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router).listen(8080);

    }
}
