package com.cockloft.core.base.psql.script.conversion;

import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultTypeConversionFactory implements TypeConversionFactory {

    private ConcurrentHashMap<Class,TypeConversion> cache = new ConcurrentHashMap<>();

    @Override
    public <T, S> TypeConversion<T, S> create(T t, S s) {
        if(cache.contains(t.getClass())){
            return (TypeConversion<T, S>) cache.get(t.getClass());
        }
        if(s instanceof ResultSet){
            TypeConversion<T,S> conversion = new VertxTypeConversion<>();
            cache.put(t.getClass(),new VertxTypeConversion());
            return conversion;
        }

        return null;
    }
}
