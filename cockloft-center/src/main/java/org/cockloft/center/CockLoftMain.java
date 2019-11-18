package org.cockloft.center;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.Json;
import io.vertx.mysqlclient.MySQLConnectOptions;
import org.cockloft.common.data.ResponseData;
import org.cockloft.common.data.ResponseRes;
import org.cockloft.common.enums.StatusEnum;
import org.cockloft.org.cockloft.user.authentication.handle.LoginHandle;
import org.cockloft.org.cockloft.user.authentication.handle.RegisterHandle;
import org.cockloft.vertx.router.RouteContext;
import org.cockloft.vertx.router.Router;
import org.cockloft.vertx.router.example.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CockLoftMain {
    private static Logger logger = LoggerFactory.getLogger(CockLoftMain.class);
    private static int port = 8080;
    public static void main(String[] args) throws DataAccessException {
        VertxOptions vertxOptions = new VertxOptions();
        Vertx vertx = Vertx.vertx(vertxOptions);
        Router router = Router.router(vertx);
        MySQLConnectOptions mySQLConnectOptions = new MySQLConnectOptions()
                .setHost("106.12.74.33").setDatabase("user_center").setPort(3306)
                .setUser("rd").setPassword("14159265jkl");

        router.initMysqlConnectionPool(mySQLConnectOptions);
        router.setErrorHandle((errorContext)->{
            Throwable throwable = errorContext.getThrowable();
            RouteContext routeContext = errorContext.getRouteContext();
            ResponseData responseData = ResponseData.create(StatusEnum.NOT_FIND_STATUS, ResponseRes.UNKNOW_ERROR);
            logger.error(throwable.getMessage());
            logger.error(throwable.getLocalizedMessage());
            routeContext.getResponse().end(Json.encode(responseData));
        });
        HttpServerOptions httpServerOptions = new HttpServerOptions();
        HttpServer server = vertx.createHttpServer(httpServerOptions);

        router.route("/login", HttpMethod.POST).handle(new LoginHandle());
        router.route("/register", HttpMethod.POST).handle(new RegisterHandle());
        server.requestHandler(router
        ).listen(port,(res)->{
            if(res.succeeded()){
                logger.info("CockLoft server is started! port : {}",port);
            }else{
                logger.info("CockLoft server is failed! msg : {}",res.cause().getMessage());
            }
        });
    }
}
