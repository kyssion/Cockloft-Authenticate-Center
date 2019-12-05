package org.cockloft.common.util;

import org.cockloft.common.cache.RamCache;

public class CacheUtil {
    private CacheUtil(){
        super();
    }
    public static boolean saveTokenToUser(String token, String userId, String passwdMD5) {
        return RamCache.getRam().addMap(token,userId,passwdMD5);
    }
}