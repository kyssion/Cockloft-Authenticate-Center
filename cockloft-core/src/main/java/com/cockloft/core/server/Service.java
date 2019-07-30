package com.cockloft.core.server;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;

public abstract class Service {
    private String path;
    private MultiMap params;
    private MultiMap formParams;
    private MultiMap header;
    private String body;
    private Vertx vertx;
    private JsonObject json;
    private HttpServerRequest req;
    private HttpServerResponse res;

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

    public Service(String path) {
        this.path = path;
    }

    public MultiMap getParams() {
        return params;
    }

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    public void setParams(MultiMap params) {
        this.params = params;
    }

    public MultiMap getFormParams() {
        return formParams;
    }

    public void setFormParams(MultiMap formParams) {
        this.formParams = formParams;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public void setVertx(Vertx vertx) {
        this.vertx = vertx;
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

    public abstract boolean useForm();

    public abstract void handle();
}
