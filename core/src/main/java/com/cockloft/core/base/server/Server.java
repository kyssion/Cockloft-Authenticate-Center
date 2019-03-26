package com.cockloft.core.base.server;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;

public abstract class Server{

    private String path = "";
    //defind url method
    private HttpMethod method;

    public Server(String path,HttpMethod method) throws Exception {
        if (path.startsWith("/")) {
            throw new Exception("路径匹配不能以/ 开始");
        }
        this.path = path;
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public abstract void doServer(HttpServerRequest httpServerRequest);
}
