package org.cockloft.vertx.router;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class UserServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        router.route("/test").method(HttpMethod.GET).handler((context)->{
           context.response().end("dsfsdfsdf");
        });
        router.route("/test2").method(HttpMethod.GET).handler((context)->{
            context.response().end("2222222");
        });
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router).listen(8080);

    }
}
