package org.cockloft.vertx.router;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;

import java.util.HashMap;
import java.util.Map;

public class Router implements Handler<HttpServerRequest> {
    private Vertx vertx;
    private WorkerExecutor workerExecutor;

    private Map<String, Route> routePathMap;

    private Router() {
        super();
    }

    private Router(Vertx vertx) {
        this.vertx = vertx;
        this.routePathMap = new HashMap<>();
        this.workerExecutor = vertx.createSharedWorkerExecutor("router-block-executor");
    }

    public static Router router(Vertx vertx) {
        return new Router(vertx);
    }

    public Route route(String path) {
        return route(path, null);
    }

    public Route route(String path, HttpMethod method) {
        Route route = new Route(this,method);
        this.routePathMap.put(path,route);
        return route;
    }

    @Override
    public void handle(HttpServerRequest request) {
        String path = request.path();
        Route route = this.routePathMap.get(path);
        if(route==null){
            request.response().end("NO DATA");
            return;
        }
        if(route.getHttpMethod()==request.method()){
            RouteRunner routeRunner = new RouteRunner(this.vertx,route,request);
            routeRunner.run();
        }else{
            request.response().setStatusCode(400);
            request.response().end();
        }
    }
}
