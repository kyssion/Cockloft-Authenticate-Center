package org.cockloft.vertx.router;

import io.vertx.core.Vertx;
import java.util.Iterator;

public class RouteRunner {
    private Iterator<Route.RouteHandle> iterator;
    private RouteContext routeContext;
    private Vertx vertx;
    private Route route;
    public RouteRunner(Vertx vertx,Route route){
        this.vertx = vertx;
        this.iterator = route.getHandlers().iterator();
        this.route = route;
    }

    public void setRouteContext(RouteContext routeContext) {
        this.routeContext = routeContext;
    }

    public void run(){
        try{
            next();
        }catch (Throwable throwable){
            Route.ErrorRouteContext errorRouteContext = new Route.ErrorRouteContext(throwable,this.routeContext);
            this.route.getErrorHandle().handle(errorRouteContext);
        }
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
                        Route.ErrorRouteContext errorRouteContext = new Route.ErrorRouteContext(async.cause(),this.routeContext);
                        this.route.getErrorHandle().handle(errorRouteContext);
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
