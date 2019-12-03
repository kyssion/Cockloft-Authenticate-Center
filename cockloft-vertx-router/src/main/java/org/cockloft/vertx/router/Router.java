package org.cockloft.vertx.router;

import io.netty.util.internal.StringUtil;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import org.cockloft.vertx.router.example.DataAccessException;
import org.cockloft.vertx.router.handlers.RouterHandler;
import org.cockloft.vertx.router.handlers.StaticResouceRouter;
import org.cockloft.vertx.router.status.DataAccessStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router implements Handler<HttpServerRequest> {
    private Vertx vertx;
    private Map<String, Route> routePathMap;
    private MySQLPool mySQLPool;
    private Handler<Route.ErrorRouteContext> errorHandle;
    private Handler<HttpServerRequest> staticResourceRouter;

    private Router() {
        super();
    }

    private Router(Vertx vertx) {
        this.vertx = vertx;
        this.routePathMap = new HashMap<>();
    }

    public static Router router(Vertx vertx) {
        return new Router(vertx);
    }

    public Route route(String path) {
        return route(path, null);
    }

    public Route route(String path, HttpMethod method) {
        Route route = new Route(this, method);
        this.routePathMap.put(path, route);
        if (this.errorHandle != null) {
            route.errorHandle(this.errorHandle);
        }
        return route;
    }

    public void ResourceRoute(String path) {
        if (StringUtil.isNullOrEmpty(path)) {
            path = "";
        }
        this.staticResourceRouter = new StaticResouceRouter(path);
    }

    @Override
    public void handle(HttpServerRequest request) {
        String path = request.path();
        Route route = this.routePathMap.get(path);
        if (route == null || route.getHttpMethod() != request.method()) {
            if (this.staticResourceRouter != null) {
                this.staticResourceRouter.handle(request);
            } else {
                request.response().end("NO DATA");
            }
            return;
        }
        StringBuilder body = new StringBuilder();
        request.handler((buf) -> {
            body.append(buf.toString());
        });
        request.endHandler((end) -> {
            try {
                RouteRunner routeRunner = new RouteRunner(vertx, route);
                RouteContext context = new RouteContext(routeRunner);
                context.setRequest(request);
                context.setResponse(request.response());
                context.setBody(body.toString());
                context.setMultiMap(request.formAttributes());
                context.setParams(request.params());
                context.setMySQLPool(this.getMySQLPool());
                routeRunner.setRouteContext(context);
                routeRunner.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void initMysqlConnectionPool(String connectUri) throws DataAccessException {
        if (this.vertx != null) {
            this.mySQLPool = MySQLPool.pool(this.vertx, connectUri);
        }
        throw new DataAccessException(DataAccessException.NO_INIT_VERTX, DataAccessStatus.NO_VERT_FOR_CONNECTION_POOL);
    }

    public void initMysqlConnectionPool(MySQLConnectOptions mySQLConnectOptions) {
        if (this.vertx != null) {
            PoolOptions poolOptions = new PoolOptions()
                    .setMaxSize(5);
            this.mySQLPool = MySQLPool.pool(vertx, mySQLConnectOptions, poolOptions);

        }
    }

    public MySQLPool getMySQLPool() throws DataAccessException {
        if (this.mySQLPool != null) {
            return mySQLPool;
        }
        throw new DataAccessException(DataAccessException.NO_INIT_MYSQL_POOL, DataAccessStatus.NO_INIT_CONNECTION_POOL);
    }

    public void setErrorHandle(Handler<Route.ErrorRouteContext> errorHandle) {
        this.errorHandle = errorHandle;
    }
}
