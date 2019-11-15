package org.cockloft.vertx.router.example;

public class BaseException extends Exception{
    private String msg;
    private int code;
    private String desc;
    public BaseException(int code ,String msg,String desc){
        this.msg = msg;
        this.code = code;
        this.desc = desc;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
