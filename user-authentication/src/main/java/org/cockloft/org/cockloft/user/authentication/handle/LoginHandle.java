package org.cockloft.org.cockloft.user.authentication.handle;

import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
import org.cockloft.common.data.ResponseData;
import org.cockloft.common.data.UserData;
import org.cockloft.common.data.UserLoginData;
import org.cockloft.common.enums.StatusEnum;
import org.cockloft.common.example.UserLoginException;
import io.netty.util.internal.StringUtil;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import org.cockloft.vertx.router.RouteContext;
import org.cockloft.vertx.router.example.DataAccessException;

public class LoginHandle implements Handler<RouteContext> {
    @Override
    public void handle(RouteContext event) {
        HttpServerRequest request = event.getRequest();
        request.bodyHandler((msg)->{
            UserLoginData userLoginData = Json.decodeValue(msg,UserLoginData.class);
            //If the user is not logged in, return directly
            if(StringUtil.isNullOrEmpty(userLoginData.getName())||StringUtil.isNullOrEmpty(userLoginData.getPassword())){
                ResponseData responseData = ResponseData.create(StatusEnum.USER_LOGIN_DATA_ERROR, UserLoginException.NO_NEED_DATA);
                request.response().end(Json.encode(responseData));
            }
            try {
                MySQLPool pool = event.getMySQLPool();
                pool.preparedQuery(
                        "select * from `user` where user_id = ? and password = ?", Tuple.of(userLoginData.getName(),userLoginData.getPasswordMd5()),
                        (res)->{
                           if(res.succeeded()){
                               RowSet<Row> rowSet = res.result();
                               UserData userData = rowSet.property(() -> UserData.class);
                               request.response().end(Json.encode(
                                       ResponseData.create(StatusEnum.OK,userData)
                               ));
                           }else {
                               ResponseData responseData = ResponseData.create(StatusEnum.MYSQL_RES_ERROR, DataAccessException.MYSQL_RES_ERROR);
                               request.response().end(Json.encode(responseData));
                           }
                });
            } catch (DataAccessException dataAccessExample) {
                dataAccessExample.printStackTrace();
                ResponseData responseData = ResponseData.create(dataAccessExample.getCode(),dataAccessExample.getMsg(),dataAccessExample.getDesc());
                request.response().end(Json.encode(responseData));
            }
        });
    }
}