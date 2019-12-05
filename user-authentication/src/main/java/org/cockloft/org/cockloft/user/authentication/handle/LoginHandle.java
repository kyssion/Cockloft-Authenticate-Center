package org.cockloft.org.cockloft.user.authentication.handle;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
import org.cockloft.common.data.ResponseData;
import org.cockloft.common.data.ResponseRes;
import org.cockloft.common.data.params.UserLoginData;
import org.cockloft.common.enums.StatusEnum;
import org.cockloft.common.example.UserLoginException;
import io.netty.util.internal.StringUtil;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import org.cockloft.common.util.CacheUtil;
import org.cockloft.common.util.TokenUtil;
import org.cockloft.vertx.router.RouteContext;
import org.cockloft.vertx.router.example.DataAccessException;
import org.cockloft.vertx.router.handlers.RouterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginHandle implements RouterHandler<RouteContext> {
    private static final Logger logger = LoggerFactory.getLogger(LoginHandle.class);
    @Override
    public void handle(RouteContext event) {
        HttpServerRequest request = event.getRequest();
        HttpServerResponse response = event.getResponse();
        UserLoginData userLoginData = Json.decodeValue(event.getBody(), UserLoginData.class);
        //If the user is not logged in, return directly
        if (StringUtil.isNullOrEmpty(userLoginData.getName()) || StringUtil.isNullOrEmpty(userLoginData.getPassword())) {
            //ResponseData responseData = ResponseData.create(StatusEnum.USER_LOGIN_DATA_ERROR, UserLoginException.NO_NEED_DATA);
            //request.response().end(Json.encode(responseData));
            //TODO return login message not find html
            request.response().end("error");
        }
        MySQLPool pool = event.getMySQLPool();
        pool.preparedQuery(
                "select count(*) as num from `user` where user_id = ? and passwd = ?", Tuple.of(userLoginData.getName(), userLoginData.getPasswordMd5()),
                (res) -> {
                    if (res.succeeded()) {
                        RowSet<Row> rowSet = res.result();
                        if (rowSet.size() == 1) {
                            Row row = rowSet.iterator().next();
                            int userNum = row.getInteger("num");
                            //If the return num value is greater than 1, it means that this user exists.
                            if (userNum == 1) {
//                                String token = CacheUtil.createLoginTokenAddSaveInCache(userLoginData.getName(), userLoginData.getPasswordMd5());
                                String token  = TokenUtil.getUserLoginAccessToken(userLoginData.getName(), userLoginData.getPasswordMd5());
                                CacheUtil.saveTokenToUser(token,userLoginData.getName(),userLoginData.getPasswordMd5());

                                //TODO 获取用户的i的信息

                                response.putHeader("user_token",token);
                                ResponseData responseData = ResponseData.create(StatusEnum.OK, token);
                                request.response().end(Json.encode(responseData));
                            } else {
                                ResponseData responseData = ResponseData.create(StatusEnum.OK, ResponseRes.USER_INFORMATION_NOT_FINDE);
                                request.response().end(Json.encode(responseData));
                            }
                        }else {
                            //If the return value is not equal to 1, it indicates that an unknown error has occurred.
                            ResponseData responseData = ResponseData.create(StatusEnum.MYSQL_RES_ERROR, DataAccessException.MYSQL_RES_ERROR);
                            request.response().end(Json.encode(responseData));
                        }
                    } else {
                        ResponseData responseData = ResponseData.create(StatusEnum.MYSQL_RES_ERROR, DataAccessException.MYSQL_RES_ERROR);
                        request.response().end(Json.encode(responseData));
                    }
                });
    }
}