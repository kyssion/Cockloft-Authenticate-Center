package org.cockloft.vertx.router;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import org.cockloft.vertx.router.example.DataAccessException;
import org.cockloft.vertx.router.status.DataAccessStatus;

import java.util.HashMap;
import java.util.Map;

public class Router implements Handler<HttpServerRequest> {
    private Vertx vertx;
    private WorkerExecutor workerExecutor;
    private Map<String, Route> routePathMap;
    private MySQLPool mySQLPool;
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
            //设置全局异常捕获request.exceptionHandler(route.getThrowableHandle());
            routeRunner.run();
        }else{
            request.response().setStatusCode(400);
            request.response().end();
        }
    }

    public void initMysqlConnectionPool(String connectUri) throws DataAccessException {
        if(this.vertx!=null) {
            this.mySQLPool = MySQLPool.pool(this.vertx, connectUri);
        }
        throw new DataAccessException(DataAccessException.NO_INIT_VERTX,DataAccessStatus.NO_VERT_FOR_CONNECTION_POOL);
    }

    public void initMysqlConnectionPool(MySQLConnectOptions mySQLConnectOptions){
        if(this.vertx!=null){
            PoolOptions poolOptions = new PoolOptions()
                    .setMaxSize(5);
            this.mySQLPool = MySQLPool.pool(vertx,mySQLConnectOptions, poolOptions);

        }
    }

    public MySQLPool getMySQLPool() throws DataAccessException {
        if(this.mySQLPool!=null) {
            return mySQLPool;
        }
        throw new DataAccessException(DataAccessException.NO_INIT_MYSQL_POOL,DataAccessStatus.NO_INIT_CONNECTION_POOL);
    }
}
