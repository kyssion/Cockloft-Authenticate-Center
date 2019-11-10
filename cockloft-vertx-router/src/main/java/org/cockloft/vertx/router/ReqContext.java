package org.cockloft.vertx.router;

import io.vertx.core.http.HttpServerRequest;

public class ReqContext {
    private HttpServerRequest request;
    public HttpServerRequest getRequest() {
        return request;
    }
    public void setRequest(HttpServerRequest request) {
        this.request = request;
    }
}
