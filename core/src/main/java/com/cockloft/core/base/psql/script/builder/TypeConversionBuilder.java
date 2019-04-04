package com.cockloft.core.base.psql.script.builder;

import com.cockloft.core.base.psql.script.conversion.TypeConversion;

public interface TypeConversionBuilder {
    <T, S> TypeConversion<T, S> create(T t, S s);
}
