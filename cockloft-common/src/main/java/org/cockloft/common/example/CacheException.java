package org.cockloft.common.example;

import org.cockloft.common.enums.StatusEnum;

public class CacheException extends EnumBaseException {

    public static String CACHE_DISABLE="当前缓存功能不支持该功能";

    public CacheException(String desc, StatusEnum statusEnum) {
        super(desc, statusEnum);
    }
}
