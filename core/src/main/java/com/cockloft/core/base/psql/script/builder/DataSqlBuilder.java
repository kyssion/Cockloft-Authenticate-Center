package com.cockloft.core.base.psql.script.builder;

import com.cockloft.core.base.psql.script.bo.SqlData;

public interface DataSqlBuilder {
    SqlData createData(Class<?> item);
}
