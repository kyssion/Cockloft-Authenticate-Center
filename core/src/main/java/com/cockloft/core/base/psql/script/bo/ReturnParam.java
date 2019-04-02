package com.cockloft.core.base.psql.script.bo;

import com.cockloft.core.base.psql.script.conversion.TypeConversion;


public class ReturnParam<T,S> {
    private Class<T> returnParamType;
    private T returnParamInfo;
    private TypeConversion<T,S> tsTypeConversion;

    public TypeConversion<T, S> getTsTypeConversion() {
        return tsTypeConversion;
    }

    public void setTsTypeConversion(TypeConversion<T, S> tsTypeConversion) {
        this.tsTypeConversion = tsTypeConversion;
    }

    public Class<T> getReturnParamType() {
        return returnParamType;
    }

    public void setReturnParamType(Class<T> returnParamType) {
        this.returnParamType = returnParamType;
    }

    public T getReturnParamInfo() {
        return returnParamInfo;
    }

    public void setReturnParamInfo(T returnParamInfo) {
        this.returnParamInfo = returnParamInfo;
    }
}
