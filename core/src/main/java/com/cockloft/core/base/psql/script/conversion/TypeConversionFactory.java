package com.cockloft.core.base.psql.script.conversion;

public interface TypeConversionFactory {
    <T, S> TypeConversion<T, S> create(T t, S s);
}
