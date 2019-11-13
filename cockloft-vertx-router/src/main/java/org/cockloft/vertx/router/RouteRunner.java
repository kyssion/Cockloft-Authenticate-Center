package org.cockloft.vertx.router;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

import java.util.Iterator;

public class RouteRunner {
    private Iterator<Route.RouteHandle> iterator;
    private RouteContext routeContext;
    private Vertx vertx;
    public RouteRunner(Vertx vertx,Route route,HttpServerRequest httpServerRequest){
        RouteContext routeContext = new RouteContext();
        routeContext.setRequest(httpServerRequest);
        routeContext.setResponse(httpServerRequest.response());
        routeContext.setRoute(route);
        routeContext.setVertx(vertx);
        this.routeContext = routeContext;
        this.vertx = vertx;
        this.iterator = route.getHandlers().iterator();
    }

    public void run(){
        next();
    }

    public void next(){
        Route.RouteHandle routeHandle = iterator.next();
        if(routeHandle==null){
            return;
        }
        switch (routeHandle.getStatus()){
            case BLOCK:
                this.vertx.executeBlocking((promise)->{
                    routeHandle.getHandler().handle(this.routeContext);
                    promise.complete();
                },(async)->{
                    if (async.failed()) {

                    }
                });
                break;
            case NO_BLOCK:
                routeHandle.getHandler().handle(this.routeContext);
                break;
            default:
                break;
        }

    }
}
