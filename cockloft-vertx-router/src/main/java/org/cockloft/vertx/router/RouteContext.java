package org.cockloft.vertx.router;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.mysqlclient.MySQLPool;

public class RouteContext {
    private HttpServerRequest request;
    private HttpServerResponse response;
    private String body;
    private MultiMap multiMap;
    private String path;
    private MultiMap params;
    private MySQLPool mySQLPool;
    private final RouteRunner routeRunner;
    public RouteContext(RouteRunner routeRunner){
        this.routeRunner = routeRunner;
    }

    public MySQLPool getMySQLPool() {
        return mySQLPool;
    }

    public void setMySQLPool(MySQLPool mySQLPool) {
        this.mySQLPool = mySQLPool;
    }

    public MultiMap getParams() {
        return params;
    }

    public void setParams(MultiMap params) {
        this.params = params;
    }

    public HttpServerRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServerRequest request) {
        this.request = request;
    }

    public HttpServerResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServerResponse response) {
        this.response = response;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MultiMap getMultiMap() {
        return multiMap;
    }

    public void setMultiMap(MultiMap multiMap) {
        this.multiMap = multiMap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void next(){
        routeRunner.next();
    }
}
