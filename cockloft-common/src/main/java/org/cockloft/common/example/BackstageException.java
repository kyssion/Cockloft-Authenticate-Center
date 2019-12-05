package org.cockloft.common.example;

import org.cockloft.common.enums.StatusEnum;

public class BackstageException extends EnumBaseException {
    public BackstageException(String desc, StatusEnum statusEnum) {
        super(desc, statusEnum);
    }
}
