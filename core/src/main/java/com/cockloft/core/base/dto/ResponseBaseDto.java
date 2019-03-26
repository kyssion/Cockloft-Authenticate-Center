package com.cockloft.core.base.dto;

import com.cockloft.core.base.except.AuthenticateExcept;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBaseDto extends BaseDto {

    private ResultCode header;
    private Object body;

    public ResponseBaseDto() {
        super();
    }

    public ResponseBaseDto(ResultCode code, Object body) {
        this.body = body;
        this.header = code;
    }

    public ResultCode getHeader() {
        return header;
    }

    public void setHeader(ResultCode header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public static ResponseBaseDto exceptionRes(AuthenticateExcept authenticateExcept) {
        return new ResponseBaseDto(authenticateExcept.toCode(),authenticateExcept.getDef());
    }

    public static ResponseBaseDto toRes(ResultCode resultCode,Object body){
        return new ResponseBaseDto(resultCode,body);
    }
}
