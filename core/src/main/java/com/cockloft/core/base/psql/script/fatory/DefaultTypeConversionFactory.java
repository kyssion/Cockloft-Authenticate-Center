package com.cockloft.core.base.psql.script.fatory;

import com.cockloft.core.base.psql.script.builder.DefaultTypeConversionBuilder;
import com.cockloft.core.base.psql.script.builder.TypeConversionBuilder;
import com.cockloft.core.base.psql.script.conversion.TypeConversion;

public class DefaultTypeConversionFactory implements TypeConversionFactory {

    private static TypeConversionBuilder builder =
            new DefaultTypeConversionBuilder();

    @Override
    public <T, S> TypeConversion getTypeConversion(T t, S s) {
        return builder.create(t,s);
    }
}
