package com.cockloft.core.base.psql.script.conversion;

import com.cockloft.core.base.reflection.meta.MetaObject;
import com.fasterxml.jackson.core.type.TypeReference;
import io.vertx.ext.sql.ResultSet;


public class VertxTypeConversion<T,S> implements TypeConversion<T,S> {

    private T returnInfo;

    public T getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(T returnInfo) {
        this.returnInfo = returnInfo;
    }

    @Override
    public T concersion(S s) {
        if(s instanceof ResultSet){
            return null;
        }
        ResultSet item = (ResultSet) s;
        MetaObject metaObject = MetaObject.forObject(this.returnInfo);
        return returnInfo;
    }
}
