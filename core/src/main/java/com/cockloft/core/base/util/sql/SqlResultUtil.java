package com.cockloft.core.base.util.sql;

import io.vertx.ext.sql.ResultSet;

import java.lang.reflect.InvocationTargetException;

public class SqlResultUtil {

    public static <T> T getResultUtil(ResultSet resultSet, Class<T> classItem) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) new Object();
    }
}
