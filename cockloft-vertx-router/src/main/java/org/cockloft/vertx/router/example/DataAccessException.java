package org.cockloft.vertx.router.example;


import org.cockloft.vertx.router.status.DataAccessStatus;

public class DataAccessException extends BaseException {
    public static String NO_INIT_VERTX = "异常,vertx没有初始化进运行环境";
    public static String NO_INIT_MYSQL_POOL = "错误,没有初始化数据库池化链接";
    public static String MYSQL_RES_ERROR = "错误,数据库搜索数据异常";
    public DataAccessException(String desc,DataAccessStatus status){
        this(status.getCode(),status.getMsg(),desc);
    }

    public DataAccessException(int code,String msg, String desc) {
        super(code, msg,desc);
    }

}
