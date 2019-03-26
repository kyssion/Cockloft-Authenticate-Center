package com.cockloft.core.base.handle;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

public abstract class RouterHandle implements Handler<HttpServerRequest> {

    @Override
    public abstract void handle(HttpServerRequest event);
}
