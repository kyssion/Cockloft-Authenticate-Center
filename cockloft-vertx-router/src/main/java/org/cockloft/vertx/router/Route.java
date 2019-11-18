package org.cockloft.vertx.router;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import org.cockloft.vertx.router.handlers.RouterHandler;
import org.cockloft.vertx.router.status.RouterHandlerStatus;

import java.util.ArrayList;
import java.util.List;

public class Route {
    static class RouteHandle{
        private RouterHandlerStatus status;
        private RouterHandler<RouteContext> handler;

        private RouteHandle(RouterHandlerStatus status,RouterHandler<RouteContext> handler){
            this.status = status;
            this.handler = handler;
        }

        RouterHandlerStatus getStatus() {
            return status;
        }
        RouterHandler<RouteContext> getHandler() {
            return handler;
        }
    }
    public static class ErrorRouteContext{
        private RouteContext routeContext;
        private Throwable throwable;
        public ErrorRouteContext(Throwable throwable,RouteContext routeContext){
            this.routeContext = routeContext;
            this.throwable = throwable;
        }
        public RouteContext getRouteContext() {
            return routeContext;
        }
        public Throwable getThrowable() {
            return throwable;
        }
    }
    private List<RouteHandle> handlers;
    private Handler<ErrorRouteContext> errorHandle;
    private Router router;
    private HttpMethod httpMethod;
    public Route(Router router,HttpMethod httpMethod){
        this.httpMethod = httpMethod;
        this.router = router;
        this.handlers = new ArrayList<>();
        this.errorHandle = (errorRoute)->{
            System.out.println(errorRoute.getThrowable().toString());
        };
    }
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public List<RouteHandle> getHandlers() {
        return handlers;
    }

    public Route handle(RouterHandler<RouteContext> handler){
        Route.RouteHandle routeHandle = new RouteHandle(RouterHandlerStatus.NO_BLOCK, handler);
        this.handlers.add(routeHandle);
        return this;
    }
    public Route blockHandle(RouterHandler<RouteContext> handler){
        Route.RouteHandle routeHandle = new RouteHandle(RouterHandlerStatus.BLOCK, handler);
        this.handlers.add(routeHandle);
        return this;
    }
    public Route errorHandle(Handler<ErrorRouteContext> errorHandle) {
        this.errorHandle= errorHandle;
        return this;
    }

    public Handler<ErrorRouteContext> getErrorHandle(){
        return this.errorHandle;
    }

    public Router getRouter() {
        return router;
    }
}