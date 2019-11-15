package org.cockloft.common.enums;

public enum  StatusEnum {
    OK(20000,"NOT FIND ERROR"),
    //ERROR CODE
    USER_LOGIN_DATA_ERROR(50001,"USER LOGIN DATA ERROR"),
    MYSQL_POOL_ERROR(50002,"MYSQL POOL ERROR"),
    MYSQL_RES_ERROR(50003,"MYSQL RES ERROR"),
    //NOT FIND
    NOT_FIND_STATUS(60000,"NOT FIND STATUS");

    private int code;
    private String msg;
    StatusEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static StatusEnum findEnume(int code){
        for(StatusEnum statusEnum: values()){
            if(statusEnum.code ==code){
                return statusEnum;
            }
        }
        return NOT_FIND_STATUS;
    }
}
