package com.cockloft.core.base.psql.script.builder;

import com.cockloft.core.base.psql.script.bo.SqlData;

import java.util.concurrent.ConcurrentHashMap;

public class DefaultDataSqlBuilder implements DataSqlBuilder{

    private static ConcurrentHashMap<Class<?>,SqlData> cache =
            new ConcurrentHashMap<>();

    @Override
    public SqlData createData(Class<?> item) {
        if(cache.contains(item)){
            return cache.get(item);
        }

        //1. 解析sql
        //2. 创建数据映射
        //3. 创建返回值更新类

        return null;
    }
}
