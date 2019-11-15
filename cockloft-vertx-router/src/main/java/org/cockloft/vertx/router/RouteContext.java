package org.cockloft.vertx.router;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.mysqlclient.MySQLPool;
import org.cockloft.vertx.router.example.DataAccessException;

public class RouteContext {
    private HttpServerRequest request;
    private HttpServerResponse response;
    private Vertx vertx;
    private Route route;
    private RouteRunner routeRunner;
    public HttpServerRequest getRequest() {
        return request;
    }

    public void setRouteRunner(RouteRunner routeRunner) {
        this.routeRunner = routeRunner;
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

    public Vertx getVertx() {
        return vertx;
    }

    public void setVertx(Vertx vertx) {
        this.vertx = vertx;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void next(){
        this.routeRunner.next();
    }

    public MySQLPool getMySQLPool() throws DataAccessException {
        return this.getRoute().getRouter().getMySQLPool();
    }
}
