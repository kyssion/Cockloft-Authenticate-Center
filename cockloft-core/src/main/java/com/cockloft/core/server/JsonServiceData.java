package com.cockloft.core.server;

import com.cockloft.core.server.type.ServiceType;
import io.vertx.core.json.JsonObject;

public class JsonServiceData extends ServiceData{
    private JsonObject json;

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    @Override
    public ServiceType type(){
        return ServiceType.JsonService;
    }
}
