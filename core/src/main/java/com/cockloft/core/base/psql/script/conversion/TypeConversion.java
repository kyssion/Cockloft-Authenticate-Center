package com.cockloft.core.base.psql.script.conversion;

public interface TypeConversion<T,S> {
    T concersion(S s);
}
