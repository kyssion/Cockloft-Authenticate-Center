package org.cockloft.common.util;

import org.cockloft.common.enums.StatusEnum;
import org.cockloft.common.example.BackstageException;

public class TokenUtil {
    private static String UserLoginAccessTokenSplit = "_";

    private TokenUtil(){
        super();
    }

    /**
     * create user token
     * @param userId
     * @param password
     * @return
     */
    public static String getUserLoginAccessToken(String userId,String password){
        return userId + UserLoginAccessTokenSplit + password;
    }

    public static String createTokenMD5(String...strs) throws BackstageException {
        if(strs!=null){
            StringBuilder stringBuilder = new StringBuilder();
            for (String item : strs){
                stringBuilder.append(item).append(UserLoginAccessTokenSplit);
            }
            stringBuilder.append(Math.random()*10000);
            return MD5Util.encrypByMD5(stringBuilder.toString());
        }else{
            throw new BackstageException("md5 基础数据为空", StatusEnum.BACKSTAGE_NOT_FIND_MD5_BASE_TOKEN);
        }
    }
}
