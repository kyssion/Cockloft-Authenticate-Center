package com.vert.x.net.http;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.*;

public class WebHttpClient {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpClientOptions options = new HttpClientOptions().setDefaultPort(8888)
                .setKeepAlive(false)//keepAlive头
                .setLogActivity(true)//开启netty 网络日志
                .setDefaultHost("127.0.0.1");//设置默认host地址
//        HttpClient client = vertx.createHttpClient(options);
//        client.request(HttpMethod.GET, "127.0.0.1", "dddd", res -> {
//            System.out.println(res.statusCode());
//            res.bodyHandler(item -> {
//                System.out.println(item.toString());
//            });
//        }).setTimeout(1000).end("test test");
//        HttpClientRequest request = client.request(HttpMethod.GET, 8888, "127.0.0.1", "sdf");
////设置创建链接的时候使用的回调函数
//        request.connectionHandler(con -> {
//            System.out.println("connection success!");
//        });
//        request.handler(res -> {
//            //获取相应头信息
//            MultiMap header = res.headers();
//            res.handler(buf -> { });
//            res.bodyHandler(buf -> { });
//            res.endHandler(buf -> { });
//        });
//        request.endHandler(vo -> { });
//        request.exceptionHandler(throwable -> { throwable.fillInStackTrace(); });
//        request.pushHandler(req -> {
//        });
//        request.continueHandler(vo -> {});
//        request.drainHandler(vo -> { });
//        client.redirectHandler(response -> {
//            // Only follow 301 code
//            if (response.statusCode() == 301 && response.getHeader("Location") != null) {
//                // Compute the redirect URI
//                String absoluteURI = response.request().absoluteURI();
//                // Create a new ready to use request that the client will use
//                return Future.succeededFuture(client.getAbs(absoluteURI));
//            }
//            // We don't redirect
//            return null;
//        });
    }
}
