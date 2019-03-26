package com.cockloft.authenticate.server;

import com.cockloft.authenticate.po.UserItem;
import com.cockloft.core.base.server.Server;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;

public class LoginServer extends Server {

    public LoginServer(String path, HttpMethod method) throws Exception {
        super(path, method);
    }

    @Override
    public void doServer(HttpServerRequest httpServerRequest) {
        httpServerRequest.bodyHandler(buf->{
            JsonObject userJson = buf.toJsonObject();
            UserItem user = userJson.mapTo(UserItem.class);
        });
    }
}
