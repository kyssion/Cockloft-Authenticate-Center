package org.cockloft.org.cockloft.user.authentication.handle;

import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
import org.cockloft.common.cache.RamCache;
import org.cockloft.common.data.ResponseData;
import org.cockloft.common.data.ResponseRes;
import org.cockloft.common.data.params.UserLoginData;
import org.cockloft.common.enums.StatusEnum;
import org.cockloft.common.example.UserLoginException;
import io.netty.util.internal.StringUtil;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import org.cockloft.common.util.CacheUtil;
import org.cockloft.common.util.TokenUtil;
import org.cockloft.vertx.router.RouteContext;
import org.cockloft.vertx.router.example.DataAccessException;

public class LoginHandle implements Handler<RouteContext> {
    @Override
    public void handle(RouteContext event) {
        HttpServerRequest request = event.getRequest();
        request.bodyHandler((msg) -> {
            UserLoginData userLoginData = Json.decodeValue(msg, UserLoginData.class);
            //If the user is not logged in, return directly
            if (StringUtil.isNullOrEmpty(userLoginData.getName()) || StringUtil.isNullOrEmpty(userLoginData.getPassword())) {
                ResponseData responseData = ResponseData.create(StatusEnum.USER_LOGIN_DATA_ERROR, UserLoginException.NO_NEED_DATA);
                request.response().end(Json.encode(responseData));
            }
            try {
                MySQLPool pool = event.getMySQLPool();
                pool.preparedQuery(
                        "select count(*) as num from `user` where user_id = ? and password = ?", Tuple.of(userLoginData.getName(), userLoginData.getPasswordMd5()),
                        (res) -> {
                            if (res.succeeded()) {
                                RowSet<Row> rowSet = res.result();
                                if (rowSet.size() == 1) {
                                    Row row = rowSet.iterator().next();
                                    int num = row.getInteger("num");
                                    //If the return num value is greater than 1, it means that this user exists.
                                    if (num == 0) {
                                        String token = CacheUtil.createLoginTokenAddSaveInCache(userLoginData.getName(),userLoginData.getPasswordMd5());
                                        ResponseData responseData = ResponseData.create(StatusEnum.OK,token);
                                        request.response().end(Json.encode(responseData));
                                    } else {
                                        ResponseData responseData = ResponseData.create(StatusEnum.OK, ResponseRes.USER_INFORMATION_NOT_FINDE);
                                        request.response().end(Json.encode(responseData));
                                    }
                                }
                                //If the return value is not equal to 1, it indicates that an unknown error has occurred.
                                ResponseData responseData = ResponseData.create(StatusEnum.MYSQL_RES_ERROR, DataAccessException.MYSQL_RES_ERROR);
                                request.response().end(Json.encode(responseData));
                            } else {
                                ResponseData responseData = ResponseData.create(StatusEnum.MYSQL_RES_ERROR, DataAccessException.MYSQL_RES_ERROR);
                                request.response().end(Json.encode(responseData));
                            }
                        });
            } catch (DataAccessException dataAccessExample) {
                dataAccessExample.printStackTrace();
                ResponseData responseData = ResponseData.create(dataAccessExample.getCode(), dataAccessExample.getMsg(), dataAccessExample.getDesc());
                request.response().end(Json.encode(responseData));
            }
        });
    }

}