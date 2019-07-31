package com.cockloft.core.handle;

import com.cockloft.core.server.FormServiceData;
import com.cockloft.core.server.JsonServiceData;
import com.cockloft.core.server.ServiceData;
import com.cockloft.core.server.type.ServiceType;

public abstract class ServiceHandle {

    private String path;

    public ServiceHandle(String path){
        this.path = path;
    }

    public void run(ServiceData serviceData, Class<? extends ServiceData> type) {
        if (type == JsonServiceData.class) {
            this.run((JsonServiceData) serviceData);
        } else if (type == FormServiceData.class) {
            this.run((FormServiceData) serviceData);

        } else {
            this.run(serviceData);
        }
    }

    public abstract ServiceType type();

    public abstract void run(JsonServiceData serviceData);

    public abstract void run(FormServiceData serviceData);

    public abstract void run(ServiceData serviceData);

    public String getPath() {
        return path;
    }

    protected void setPath(String path) {
        this.path = path;
    }
}
