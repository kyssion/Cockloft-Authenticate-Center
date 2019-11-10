package org.cockloft.vertx.router;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.http.HttpServerRequest;

public class Router implements Handler<HttpServerRequest> {

    private Vertx vertx;
    private WorkerExecutor workerExecutor;
    private Router(){
        super();
    }
    private Router(Vertx vertx){
        this.vertx = vertx;
        this.workerExecutor = vertx.createSharedWorkerExecutor("router-block-executor");
    }
    public static Router router(Vertx vertx){
        return new Router(vertx);
    }
    @Override
    public void handle(HttpServerRequest req) {

    }
}
