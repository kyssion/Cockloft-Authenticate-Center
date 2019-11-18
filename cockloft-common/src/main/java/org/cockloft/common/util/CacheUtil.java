package org.cockloft.common.util;

import org.cockloft.common.cache.RamCache;

public class CacheUtil {
    public static String createLoginTokenAddSaveInCache(String userId,String password){
        String token = TokenUtil.getUserLoginAccessToken(userId,password);
        RamCache.getRam().addKV(userId,token);
        return token;
    }
}
