package org.cockloft.center;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        VertxOptions vertxOptions = new VertxOptions();
        Vertx vertx = Vertx.vertx(vertxOptions);
        HttpServerOptions httpServerOptions= new HttpServerOptions();
        HttpServer httpServer = vertx.createHttpServer(httpServerOptions);
        WorkerExecutor workerExecutor = vertx.createSharedWorkerExecutor("sdfsdf");
        workerExecutor.executeBlocking((proms)->{

        },res->{

        });
        httpServer.requestHandler((res)->{
            logger.info("{}",res.path());
            res.response().end("hello world");

        });

        workerExecutor.executeBlocking(
                (ttt)->{

                },(res)->{
                    res.
                }
        );
        httpServer.listen(8080,(vo)->{
           if(vo.succeeded()){
               logger.info("cockloft starting succuess ! port : {}",8080);
           }else{
               if(vo.cause()!=null) {
                   logger.error("cockloft starting failed , error msg : {}", vo.cause().getCause().getMessage());
               }else{
                   logger.error("cockloft starting failed , unknow error");
               }
           }
        });
    }
}
