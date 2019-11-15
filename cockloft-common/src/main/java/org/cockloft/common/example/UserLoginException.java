package org.cockloft.common.example;

import org.cockloft.common.enums.StatusEnum;

public class UserLoginException extends EnumBaseException {
    public static String NO_NEED_DATA = "用户没有输入账号或者密码,请重试";
    public UserLoginException(String desc, StatusEnum errorEnum) {
        super(desc,errorEnum);
    }
}
