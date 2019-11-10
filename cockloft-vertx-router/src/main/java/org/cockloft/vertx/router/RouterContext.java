package org.cockloft.vertx.router;

import org.cockloft.vertx.router.status.RouterContextStatus;

public class RouterContext {
    private ReqContext reqContext;
    private RouterContextStatus routerContextStatus;

    public ReqContext getReqContext() {
        return reqContext;
    }

    public void setReqContext(ReqContext reqContext) {
        this.reqContext = reqContext;
    }

    public RouterContextStatus getRouterContextStatus() {
        return routerContextStatus;
    }

    public void setRouterContextStatus(RouterContextStatus routerContextStatus) {
        this.routerContextStatus = routerContextStatus;
    }
}
