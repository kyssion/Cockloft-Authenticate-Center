package org.cockloft.org.cockloft.user.authentication.handle;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import org.cockloft.vertx.router.RouteContext;

public class LoginHandle implements Handler<RouteContext> {
    @Override
    public void handle(RouteContext event) {
        HttpServerRequest request = event.getRequest();

    }
}
