package com.cockloft.core.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultCode {
    public final ResultCode OK = new ResultCode("ok",200);
    public final ResultCode DEFAULT = new ResultCode("",Integer.MAX_VALUE);

    private String title;
    private int code;

    public ResultCode(String title, int code) {
        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

