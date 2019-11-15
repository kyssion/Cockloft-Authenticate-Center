package org.cockloft.common.example;

import org.cockloft.common.enums.StatusEnum;

public class MysqlException extends EnumBaseException {
    public static String GET_MYSQL_POOL_ERROR = "获取数据库链接异常";
    public MysqlException(String desc, StatusEnum statusEnum) {
        super(desc,statusEnum);
    }

}
