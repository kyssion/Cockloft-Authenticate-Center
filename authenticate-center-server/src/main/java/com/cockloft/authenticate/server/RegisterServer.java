package com.cockloft.authenticate.server;

import com.cockloft.core.base.server.Server;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;

public class RegisterServer extends Server {

    public RegisterServer(String path, HttpMethod method) throws Exception {
        super(path, method);
    }

    @Override
    public void doServer(HttpServerRequest httpServerRequest) {

    }

}
