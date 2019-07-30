package com.vert.x.net.http;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

import java.util.Iterator;
import java.util.Map;

public class WebHttpServer {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServerOptions options = new HttpServerOptions()
                .setMaxWebsocketFrameSize(1000000)
                .setLogActivity(true);
        HttpServer httpServer = vertx.createHttpServer(options);
        httpServer.requestHandler(req -> {

        });
    }

    public static void main1(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServerOptions options = new HttpServerOptions()
                .setMaxWebsocketFrameSize(1000000)
                .setLogActivity(true);
        HttpServer server = vertx.createHttpServer(options);
        server.requestHandler(res -> {

            res.bodyHandler(r -> {
                System.out.println(r.toString());
            });

            System.out.println(res.host());
            res.response().end("hello world");
        });

        server.listen(8888, "localhost", res -> {
            if (res.succeeded()) {
                System.out.println("Server is now listening!");
            } else {
                System.out.println("Failed to bind!");
            }
        });
    }

    public static void showMap(String title, Iterator<Map.Entry<String, String>> iterator) {
        System.out.println("-------------<" + title + ">-------------");
        while (iterator.hasNext()) {
            Map.Entry<String, String> item = iterator.next();
            System.out.println(item.getKey() + "  " + item.getValue());
        }
        System.out.println("----------------------------------");
    }

}
