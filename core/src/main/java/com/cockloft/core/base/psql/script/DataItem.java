package com.cockloft.core.base.psql.script;

public class DataItem {
    private String sql;
    private Object data;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
