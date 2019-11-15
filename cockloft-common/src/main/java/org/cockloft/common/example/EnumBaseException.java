package org.cockloft.common.example;

import org.cockloft.common.enums.StatusEnum;
import org.cockloft.vertx.router.example.BaseException;

public class EnumBaseException extends BaseException {
    private StatusEnum statusEnum;

    public EnumBaseException(String desc, StatusEnum statusEnum){
        super(statusEnum.getCode(),statusEnum.getMsg(),desc);
    }
}
