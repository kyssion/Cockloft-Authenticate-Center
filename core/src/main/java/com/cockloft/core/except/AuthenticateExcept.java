package com.cockloft.core.except;

import com.cockloft.core.dto.ResultCode;

public class AuthenticateExcept extends Exception {
    private String def;
    private int infoCode;

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public int getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(int infoCode) {
        this.infoCode = infoCode;
    }

    public ResultCode toCode(){
        return new ResultCode(this.def,this.infoCode);
    }
}
