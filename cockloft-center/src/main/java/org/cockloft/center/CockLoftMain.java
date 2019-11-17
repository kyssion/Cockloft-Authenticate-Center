package org.cockloft.center;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.mysqlclient.MySQLConnectOptions;
import org.cockloft.org.cockloft.user.authentication.handle.LoginHandle;
import org.cockloft.vertx.router.Router;
import org.cockloft.vertx.router.example.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CockLoftMain {
    private static Logger logger = LoggerFactory.getLogger(CockLoftMain.class);
    public static void main(String[] args) throws DataAccessException {
        VertxOptions vertxOptions = new VertxOptions();
        Vertx vertx = Vertx.vertx(vertxOptions);
        Router router = Router.router(vertx);

        MySQLConnectOptions mySQLConnectOptions = new MySQLConnectOptions()
                .setHost("").setDatabase("").setPort(3306).setUser("").setPassword("");

        router.initMysqlConnectionPool(mySQLConnectOptions);

        HttpServerOptions httpServerOptions = new HttpServerOptions();
        HttpServer server = vertx.createHttpServer(httpServerOptions);

        router.route("/login", HttpMethod.POST).handle(new LoginHandle());

        server.requestHandler(router).listen(8080,(res)->{

        });
    }
}
