package org.cockloft.vertx.router;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import org.cockloft.vertx.router.status.RouterHandlerStatus;

import java.util.ArrayList;
import java.util.List;

public class Route {
     static class RouteHandle{
        private RouterHandlerStatus status;
        private Handler<RouteContext> handler;

        private RouteHandle(RouterHandlerStatus status,Handler<RouteContext> handler){
            this.status = status;
            this.handler = handler;
        }

        public RouterHandlerStatus getStatus() {
            return status;
        }

        public Handler<RouteContext> getHandler() {
            return handler;
        }
    }

    private List<RouteHandle> handlers;
    private Router router;
    private HttpMethod httpMethod;
    public Route(Router router,HttpMethod httpMethod){
        this.httpMethod = httpMethod;
        this.router = router;
        this.handlers = new ArrayList<>();
    }
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public List<RouteHandle> getHandlers() {
        return handlers;
    }

    public Route handle(Handler<RouteContext> handler){
        Route.RouteHandle routeHandle = new RouteHandle(RouterHandlerStatus.NO_BLOCK,handler);
        this.handlers.add(routeHandle);
        return this;
    }

    public Route blockHandle(Handler<RouteContext> handler){
        Route.RouteHandle routeHandle = new RouteHandle(RouterHandlerStatus.BLOCK,handler);
        this.handlers.add(routeHandle);
        return this;
    }
}