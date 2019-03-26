package com.vert.x.net;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;

    public class SockerClient {
        public static void main(String[] args) {
            Vertx vertx = Vertx.vertx();
            NetClientOptions options = new NetClientOptions()
                    .setConnectTimeout(10000)
                    .setReconnectInterval(10)
                    .setReconnectAttempts(500)
                    .setLogActivity(true);
            NetClient client = vertx.createNetClient(options);
            client.connect(33333, "localhost", res -> {
                if (res.succeeded()) {
                    System.out.println("Connected!");
                    NetSocket socket = res.result();
                    Buffer buffer = Buffer.buffer().appendBytes("this is test".getBytes());
                    socket.write(buffer);
                    // Write a string in UTF-8 encoding
                    socket.write("some data");
                    // Write a string using the specified encoding
                    socket.write("some data", "UTF-8");
                    System.out.println(socket.localAddress().host()+":"+socket.localAddress().path()+":"+socket.localAddress().port());
                    System.out.println(socket.remoteAddress().host()+":"+socket.remoteAddress().path()+":"+socket.remoteAddress().port());
                    socket.handler(b->{
                        System.out.println("I received some bytes: " + b.length());
                        System.out.println(b.toString());
                    });
                } else {
                    System.out.println("Failed to connect: " + res.cause().getMessage());
                }

            });
            client.close();

        }
    }
