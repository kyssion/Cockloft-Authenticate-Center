package org.cockloft.org.cockloft.user.authentication.handle;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.cockloft.vertx.router.RouteContext;
import org.cockloft.vertx.router.handlers.RouterHandler;

/**
 * autheen
 */
public class AuthenticationHandle implements RouterHandler<RouteContext> {
    @Override
    public void handle(RouteContext routeContext) {
        HttpServerRequest request = routeContext.getRequest();
        HttpServerResponse response = routeContext.getResponse();
        MultiMap urlParams = routeContext.getParams();
        String redirect = urlParams.get("redirect_url");
        String statusCode = urlParams.get("status_code");
        String clientId = urlParams.get("clientID");
        response.putHeader("redirect_url",redirect);
        response.putHeader("status_code",statusCode);

    }
}
