package com.vert.x.net;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

public class SocketServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        NetServer netServer = vertx.createNetServer();
        netServer.connectHandler(socket -> {
            socket.handler(b -> {
                System.out.println("I received some bytes: " + b.length());
                System.out.println(b.toString());
                Buffer buffer = Buffer.buffer();
                socket.write(buffer);
                // Write a string in UTF-8 encoding
                socket.write("some data");
                // Write a string using the specified encoding
                socket.write("some data", "UTF-8");
                socket.sendFile("main.properties");
            });
            socket.closeHandler(v -> {
                System.out.println("The socket has been closed");
            });
            System.out.println(socket.localAddress().host()+":"+socket.localAddress().path()+":"+socket.localAddress().port());
            System.out.println(socket.remoteAddress().host()+":"+socket.remoteAddress().path()+":"+socket.remoteAddress().port());

            socket.exceptionHandler(throwable->{
                System.out.println("open throwable");
            });


        });
        netServer.listen(33333,"localhost",res -> {
            if (res.succeeded()) {
                System.out.println("Server is now listening!");
            } else {
                System.out.println("Failed to bind!");
            }
        });
        netServer.close(res->{
            if (res.succeeded()) {
                System.out.println("Server is now closed");
            } else {
                System.out.println("close failed");
            }
        });
    }
}
