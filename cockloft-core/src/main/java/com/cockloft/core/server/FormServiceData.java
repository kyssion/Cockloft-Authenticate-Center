package com.cockloft.core.server;

import com.cockloft.core.server.type.ServiceType;
import io.vertx.core.MultiMap;

public class FormServiceData extends ServiceData {

    private MultiMap formParams;

    public MultiMap getFormParams() {
        return formParams;
    }

    public void setFormParams(MultiMap formParams) {
        this.formParams = formParams;
    }
    @Override
    public ServiceType type(){
        return ServiceType.FormService;
    }
}
