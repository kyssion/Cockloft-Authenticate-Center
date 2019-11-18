package org.cockloft.vertx.router.handlers;

import io.vertx.core.Handler;
import org.cockloft.vertx.router.RouteContext;

public  interface RouterHandler<T extends RouteContext> extends Handler<T> {
}
