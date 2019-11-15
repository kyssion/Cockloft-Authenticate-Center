package org.cockloft.vertx.router.status;

public enum  DataAccessStatus {
    NO_VERT_FOR_CONNECTION_POOL(5000,"no vertx for connection pool"),
    NO_INIT_CONNECTION_POOL(5001,"no vertx for connection pool");
    DataAccessStatus(int code,String msg){
        this.code =code;
        this.msg = msg;
    }
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
