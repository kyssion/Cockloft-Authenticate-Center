package org.cockloft.org.cockloft.user.authentication.handle;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerResponse;
import org.cockloft.common.example.BackstageException;
import org.cockloft.common.util.CacheUtil;
import org.cockloft.common.util.TokenUtil;
import org.cockloft.vertx.router.RouteContext;
import org.cockloft.vertx.router.handlers.RouterHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * autheen
 */
public class AuthenticationHandle implements RouterHandler<RouteContext> {

    @Override
    public void handle(RouteContext routeContext) {
        HttpServerResponse response = routeContext.getResponse();
        MultiMap urlParams = routeContext.getParams();
        String redirect = urlParams.get("redirect_url");
        String statusCode = urlParams.get("status_code");
        String clientId = urlParams.get("clientID");
        response.putHeader("redirect_url",redirect);
        response.putHeader("status_code",statusCode);
        //TODO this need return login html
        //TODO if find user callback url is true , return can login url
        try {
            String token = TokenUtil.createTokenMD5(clientId,statusCode,redirect);
            Map<String,String> value = new HashMap<>();
            value.put("redirect_url",redirect);
            value.put("status_code",statusCode);
            value.put("clientID",clientId);
            CacheUtil.saveAuthenticationRedirect(token,value);
            response.putHeader("authentication_token",token);
            response.end();
            return;
        } catch (BackstageException e) {
            e.printStackTrace();
        }
        //TODO else return error url
        response.end();
    }
}
