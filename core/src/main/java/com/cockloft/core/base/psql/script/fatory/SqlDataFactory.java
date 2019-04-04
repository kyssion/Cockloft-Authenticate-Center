package com.cockloft.core.base.psql.script.fatory;

import com.cockloft.core.base.psql.script.bo.SqlData;

public interface SqlDataFactory {
    SqlData getSqlData(Class<?> mapper);
}

