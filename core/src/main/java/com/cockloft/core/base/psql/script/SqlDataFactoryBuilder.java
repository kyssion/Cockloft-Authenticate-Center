package com.cockloft.core.base.psql.script;


import com.cockloft.core.base.psql.script.bo.SqlData;
import com.cockloft.core.base.psql.script.fatory.DefaultSqlDataFactory;
import com.cockloft.core.base.psql.script.fatory.SqlDataFactory;

public class SqlDataFactoryBuilder {
    private static final SqlDataFactory sqlDataFactory =
            new DefaultSqlDataFactory();

    public static SqlData createAndGetBuild(Class<?> mapper){
        return sqlDataFactory.getSqlData(mapper);
    }
}
