package com.cockloft.core.base.psql.script.fatory;

import com.cockloft.core.base.psql.script.bo.SqlData;
import com.cockloft.core.base.psql.script.builder.DataSqlBuilder;
import com.cockloft.core.base.psql.script.builder.DefaultDataSqlBuilder;

public class DefaultSqlDataFactory implements SqlDataFactory{

    private static final DataSqlBuilder dataBuilder = new DefaultDataSqlBuilder();

    @Override
    public SqlData getSqlData(Class<?> mapper) {
        return dataBuilder.createData(mapper);
    }
}
