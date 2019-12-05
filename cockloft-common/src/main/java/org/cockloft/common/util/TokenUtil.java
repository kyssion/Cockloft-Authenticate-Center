package org.cockloft.common.util;

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
}
