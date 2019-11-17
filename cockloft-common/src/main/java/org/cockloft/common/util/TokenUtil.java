package org.cockloft.common.util;

public class TokenUtil {
    private static String UserLoginAccessTokenSplit = "_";

    public static String getUserLoginAccessToken(String userId,String password){
        return userId + UserLoginAccessTokenSplit + password;
    }
}
