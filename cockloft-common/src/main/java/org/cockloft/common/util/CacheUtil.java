package org.cockloft.common.util;

import org.cockloft.common.cache.RamCache;

import java.util.Map;

public class CacheUtil {
    private CacheUtil(){
        super();
    }

    public static boolean saveTokenToUser(String token, String userId, String passwdMD5) {
        return RamCache.getRam().addMap(token,userId,passwdMD5);
    }

    public static boolean saveAuthenticationRedirect(String token, Map<String,String> data){
        return RamCache.getRam().addMap(token,data);
    }
}