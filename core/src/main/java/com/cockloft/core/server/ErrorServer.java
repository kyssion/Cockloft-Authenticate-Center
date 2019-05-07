package com.cockloft.core.server;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;

public class ErrorServer extends Server{
    public static ErrorServer errorServer = null;

    static {
        try {
            errorServer = new ErrorServer("error",HttpMethod.DELETE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ErrorServer(String path, HttpMethod method) throws Exception {
        super(path, method);
    }

    @Override
    public void doServer(HttpServerRequest httpServerRequest) {

    }

}
