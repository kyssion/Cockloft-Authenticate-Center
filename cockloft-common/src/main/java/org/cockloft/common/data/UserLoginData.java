package org.cockloft.common.data;

import io.netty.util.internal.StringUtil;
import org.cockloft.common.util.MD5Util;

public class UserLoginData {
    private String name;
    private String password;
    private String passwordMd5;

    public String getPasswordMd5() {
        if(StringUtil.isNullOrEmpty(passwordMd5)){
            this.passwordMd5 = MD5Util.encrypByMD5(password);
        }
        return passwordMd5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
