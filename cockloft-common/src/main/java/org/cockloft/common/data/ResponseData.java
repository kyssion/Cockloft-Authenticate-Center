package org.cockloft.common.data;


import org.cockloft.common.enums.StatusEnum;

public class ResponseData {
    public static class ResponseHeader{
        private String msg;
        private int code;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    private ResponseHeader header;
    private Object body;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public static ResponseData create(StatusEnum statusEnum, Object body){
        ResponseData responseData = new ResponseData();
        responseData.setBody(body);
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setCode(statusEnum.getCode());
        responseHeader.setMsg(statusEnum.getMsg());
        responseData.setHeader(responseHeader);
        return responseData;
    }

    public static ResponseData create(int code, String msg,Object body){
        ResponseData responseData = new ResponseData();
        responseData.setBody(body);
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setCode(code);
        responseHeader.setMsg(msg);
        responseData.setHeader(responseHeader);
        return responseData;
    }
}
