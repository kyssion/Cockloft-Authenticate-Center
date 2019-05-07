package com.cockloft.core.server;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;

public class RootServer extends Server{

    public RootServer(String path, HttpMethod method) throws Exception {
        super(path, method);
    }

    @Override
    public void doServer(HttpServerRequest httpServerRequest) {

    }
}
