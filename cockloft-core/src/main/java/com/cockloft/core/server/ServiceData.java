package com.cockloft.core.server;

import com.cockloft.core.server.type.ServiceType;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

public class ServiceData {
    private String path;
    private MultiMap params;
    private MultiMap header;
    private String body;
    private HttpServerRequest req;
    private HttpServerResponse res;

    public ServiceType type(){
        return ServiceType.Service;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MultiMap getHeader() {
        return header;
    }

    public void setHeader(MultiMap header) {
        this.header = header;
    }

    public MultiMap getParams() {
        return params;
    }


    public void setParams(MultiMap params) {
        this.params = params;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public HttpServerRequest getReq() {
        return req;
    }

    public void setReq(HttpServerRequest req) {
        this.req = req;
    }

    public HttpServerResponse getRes() {
        return res;
    }

    public void setRes(HttpServerResponse res) {
        this.res = res;
    }
}
