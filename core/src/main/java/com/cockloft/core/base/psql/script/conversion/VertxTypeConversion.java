package com.cockloft.core.base.psql.script.conversion;

import com.cockloft.core.base.reflection.meta.MetaObject;
import io.vertx.ext.sql.ResultSet;

public class VertxTypeConversion<T,S extends ResultSet> implements TypeConversion<T,S> {

    private T returnInfo;

    public VertxTypeConversion(T t){
        this.returnInfo = t;
    }

    @Override
    public T concersion(ResultSet s) {
        MetaObject metaObject = MetaObject.forObject(this.returnInfo);
        return returnInfo;
    }
}
