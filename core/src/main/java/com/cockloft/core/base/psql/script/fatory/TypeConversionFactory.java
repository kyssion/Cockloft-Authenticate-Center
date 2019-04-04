package com.cockloft.core.base.psql.script.fatory;

import com.cockloft.core.base.psql.script.conversion.TypeConversion;

public interface TypeConversionFactory {
    <T,S> TypeConversion getTypeConversion(T t,S s);
}
