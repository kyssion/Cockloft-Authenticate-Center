package org.cockloft.common.example;

import org.cockloft.common.enums.StatusEnum;

public class RegisterException extends EnumBaseException {

    public RegisterException(String desc, StatusEnum statusEnum) {
        super(desc, statusEnum);
    }
}
