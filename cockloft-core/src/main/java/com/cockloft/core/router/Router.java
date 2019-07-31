package com.cockloft.core.router;

import com.cockloft.core.handle.ServiceHandle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;

import java.util.HashMap;
import java.util.Map;

public class Router implements Handler<HttpServerRequest> {

    private HttpServer httpServer;
    private Vertx vertx;
    private Map<String, ServiceHandle> mapperMap;

    public Router(Vertx vertx, HttpServer httpServer) {
        this.vertx = vertx;
        this.httpServer = httpServer;
        this.mapperMap = new HashMap<>();
    }

    public void addRouter(ServiceHandle service) {
        this.mapperMap.put(service.getPath(), service);
    }

    @Override
    public void handle(HttpServerRequest req) {
        String path = req.path();
        ServiceHandle service = this.mapperMap.get(path);
        switch (service.type()){
            case FormService:

        }
    }
}
