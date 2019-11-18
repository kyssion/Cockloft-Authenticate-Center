package org.cockloft.org.cockloft.user.authentication.handle;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Tuple;
import org.cockloft.common.data.ResponseData;
import org.cockloft.common.data.ResponseRes;
import org.cockloft.common.data.params.UserRegisterData;
import org.cockloft.common.enums.StatusEnum;
import org.cockloft.common.util.CacheUtil;
import org.cockloft.common.util.MD5Util;
import org.cockloft.vertx.router.RouteContext;
import org.cockloft.vertx.router.example.DataAccessException;
import org.cockloft.vertx.router.handlers.RouterHandler;

public class RegisterHandle implements RouterHandler<RouteContext> {
    @Override
    public void handle(RouteContext context) {
    HttpServerRequest request = context.getRequest();
        UserRegisterData registerData = Json.decodeValue(context.getBody(),UserRegisterData.class);
        if(!registerData.getPasswd().equals(registerData.getConfirmPasswd())){
            ResponseData responseData = ResponseData.create(StatusEnum.USER_REGIEST_PASSWD_CONFIRM_ERROR, ResponseRes.USER_REGIEST_PASSWD_CONFIRM_ERROR);
            request.response().end(Json.encode(responseData));
        }
            MySQLPool pool = context.getMySQLPool();
            long registerTime = System.currentTimeMillis();
            String passwdMD5 = MD5Util.encrypByMD5(registerData.getPasswd());
            Tuple tuple = Tuple.of(registerData.getUserId(),passwdMD5 ,registerData.getEmail(),registerData.getTel(),registerTime);
            pool.preparedQuery("insert into user (`user_id`,`passwd`,`email`,`tel`,`register_time`) valuse (?,?,?,?,?)",tuple,(res)->{
                if (res.succeeded()) {
                    String token = CacheUtil.createLoginTokenAddSaveInCache(registerData.getUserId(),passwdMD5);
                    ResponseData responseData = ResponseData.create(StatusEnum.OK,token);
                    request.response().end(Json.encode(responseData));
                } else {
                    ResponseData responseData = ResponseData.create(StatusEnum.MYSQL_RES_ERROR, DataAccessException.MYSQL_RES_ERROR);
                    request.response().end(Json.encode(responseData));
                }
            });
    }
}
